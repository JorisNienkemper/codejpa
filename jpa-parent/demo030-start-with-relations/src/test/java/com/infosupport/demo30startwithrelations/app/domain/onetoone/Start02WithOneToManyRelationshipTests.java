package com.infosupport.demo30startwithrelations.app.domain.onetoone;

import com.infosupport.demo10startwithjpa.app.domain.onetomany.manytoone.EmployeeManyToOne;
import com.infosupport.demo10startwithjpa.app.domain.onetomany.manytoone.EmployeeManyToOneCascadeRemove;
import com.infosupport.demo10startwithjpa.app.domain.onetomany.manytoone.PublisherOneToMany;
import com.infosupport.demo10startwithjpa.app.domain.onetomany.manytoone.PublisherOneToManyCascadeRemove;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Start02WithOneToManyRelationshipTests extends Start02WithOneToManyRelationshipTestsHelper {


    @DisplayName("Persist a Employee and Publisher entity where Employee has an uni-directional(or bi-directional) ManyToOne relationship with Publisher")
    @Nested
    class BidirectionalOneToManyManyToOneChallenges {

        @Test
        @DisplayName("Persist the relationship on the employee side")
        void persistRelationOnTheEmployeeSide() {

            Date freshStart = Date.valueOf(LocalDate.of(1970, 1, 1));
            EmployeeManyToOne emp = new EmployeeManyToOne("Boris", "J", "Johnson", freshStart);

            addOnePublisherToTheDatabaseInOneTx();

            executeInTransaction(em -> {
                PublisherOneToMany pub = em.find(PublisherOneToMany.class, 1L);
                emp.setPublisher(pub);
                em.persist(emp);
            });

            executeInTransaction((em) -> {
                PublisherOneToMany pub = em.find(PublisherOneToMany.class, 1L);
                Collection<EmployeeManyToOne> employees = pub.getEmployees();
                assertThat(employees.size()).isEqualTo(1);
            });
        }

        @Test
        @DisplayName("Persist the relationship on the publisher side")
        void persistRelationOnThePublisherSide() {

            Date freshStart = Date.valueOf(LocalDate.of(1970, 1, 1));
            var emp = new EmployeeManyToOne("Boris", "J", "Johnson", freshStart);

            addOnePublisherToTheDatabaseInOneTx();

            executeInTransaction(em -> {
                PublisherOneToMany pub = em.find(PublisherOneToMany.class, 1L);
                pub.getEmployees().add(emp);
                em.persist(emp);
            });

            executeInTransaction((em) -> {
                PublisherOneToMany pub = em.find(PublisherOneToMany.class, 1L);
                Collection<EmployeeManyToOne> employees = pub.getEmployees();
                assertThat(employees.size()).isEqualTo(0);
            });
        }
    }

    @Nested
    @DisplayName("The (N+1) select problem, you think that you issue only 1 query but in reality that number becomes N+1")
    class NplusOneQueryProblem {

        @Test
        @DisplayName("Create 4 employees and each with it's own publisher")
        void tryToCreateAnNplus1QueryProblem() {

            add4PublisherAnd4EmployeesInOneTx();

            executeInTransaction(em -> {
                TypedQuery<EmployeeManyToOne> employeesQuery = em.createQuery("SELECT e FROM EmployeeManyToOne e", EmployeeManyToOne.class);
                List<EmployeeManyToOne> employees = employeesQuery.getResultList();
                assertThat(employees.size()).isEqualTo(4);
            });
        }


        @Test
        @DisplayName("Show that without using the oneToMany association you can retrieve the employees and it's publisher with only one query")
        void useQueryInsteadOfAssociations() {
            add4PublisherAnd4EmployeesInOneTx();
            executeInTransaction(em -> {
                TypedQuery<EmployeeManyToOne> jpqlQuery = em.createQuery("SELECT e FROM EmployeeManyToOne e JOIN FETCH e.publisher p WHERE p.id=:id", EmployeeManyToOne.class);
                jpqlQuery.setParameter("id", 1L);
                List<EmployeeManyToOne> employees = jpqlQuery.getResultList();
                for (EmployeeManyToOne e : employees) {
                    logger.info("naam {}, afdeling {} ", e.getFirstname(), e.getPublisher().getName());
                }
            });
        }

        @Test
        void lazyOneToManyIfAtAll() {
            add4PublisherAnd4EmployeesInOneTx();
            executeInTransaction(em -> {
                TypedQuery<EmployeeManyToOne> jpqlQuery = em.createQuery("SELECT e FROM PublisherOneToMany p INNER JOIN p.employees e WHERE p.id=:id", EmployeeManyToOne.class);
                jpqlQuery.setParameter("id", 3L);
                List<EmployeeManyToOne> employees = jpqlQuery.getResultList();
                for (EmployeeManyToOne e : employees) {
                    logger.info("What is this? {} {} {}", e.getFirstname(), e.getMiddleInitial(), e.getLastname());
                }
            });
        }

        private void add4PublisherAnd4EmployeesInOneTx() {
            Date freshStart = Date.valueOf(LocalDate.of(1970, 1, 1));
            List<EmployeeManyToOne> employees = List.of(
                    new EmployeeManyToOne("Boris", "J", "Johnson", freshStart),
                    new EmployeeManyToOne("Floris", "J", "Johnson", freshStart),
                    new EmployeeManyToOne("Goris", "J", "Johnson", freshStart),
                    new EmployeeManyToOne("Doris", "J", "Johnson", freshStart)
            );

            List<PublisherOneToMany> publishers = List.of(
                    new PublisherOneToMany("Scootney Books", "New York", "NY", "USA"),
                    new PublisherOneToMany("Gulliver Books", "Australia", "SW", "UK"),
                    new PublisherOneToMany("Bramble Books", "Canada", "NY", "CA"),
                    new PublisherOneToMany("Dupak Books", "New Zealand", "AU", "NZ")
            );
            executeInTransaction((em) -> {
                for (int i = 0; i < employees.size(); i++) {
                    em.persist(publishers.get(i));
                    employees.get(i).setPublisher(publishers.get(i));
                    em.persist(employees.get(i));
                }
            });
        }
    }

    @Nested
    @DisplayName("When expecting a lot of employees with one publisher use pagination")
    class PaginationExamples {


        @Test
        @DisplayName("the oneToMany assocation can be costly and expensive when it involves 100ths to 1000nds of entities")
        void count8Employees() {
            executeInTransaction(em -> {
                add1PublisherAnd8EmployeesInOneTx();
                PublisherOneToMany publisher = em.find(PublisherOneToMany.class, 1L);
                assertThat(publisher.getEmployees().size()).isEqualTo(8);
            });
        }

        @Test
        @DisplayName("Use pagination to fetch the number of rows you can deal with in your app")
        void usePagination() {
            executeInTransaction(em -> {
                TypedQuery<EmployeeManyToOne> jpqlQuery = em.createQuery("SELECT e FROM PublisherOneToMany p JOIN p.employees e WHERE p.id=:id ORDER BY e.id", EmployeeManyToOne.class);
                jpqlQuery.setParameter("id", 1L);
                jpqlQuery.setFirstResult(0);
                jpqlQuery.setMaxResults(4);
                List<EmployeeManyToOne> partialResult = jpqlQuery.getResultList();
                for (EmployeeManyToOne e : partialResult) {
                    logger.info("{} {} has id = {}", e.getFirstname(), e.getLastname(), e.getId());
                }
            });
        }

        @Test
        @DisplayName("Use pagination to fetch the number of rows you can deal with in your app")
        void usePaginationToTheEnd() {
            add1PublisherAnd8EmployeesInOneTx();
            executeInTransaction(em -> {
                TypedQuery<EmployeeManyToOne> jpqlQuery = em.createQuery("SELECT e FROM PublisherOneToMany p JOIN p.employees e WHERE p.id=:id ORDER BY e.id", EmployeeManyToOne.class);
                jpqlQuery.setParameter("id", 1L);
                int maxRowsPerPage = 2;
                int startRow = 0;
                List<EmployeeManyToOne> partialResult;
                do {
                    jpqlQuery.setFirstResult(startRow);
                    jpqlQuery.setMaxResults(maxRowsPerPage);
                    partialResult = jpqlQuery.getResultList();
                    for (EmployeeManyToOne e : partialResult) {
                        logger.info("{} {} has id = {}", e.getFirstname(), e.getLastname(), e.getId());
                    }
                    startRow += maxRowsPerPage;
                } while (partialResult.size() == maxRowsPerPage);
            });
        }

        private void add1PublisherAnd8EmployeesInOneTx() {
            Date freshStart = Date.valueOf(LocalDate.of(1970, 1, 1));
            List<EmployeeManyToOne> employees = List.of(
                    new EmployeeManyToOne("Boris", "J", "Johnson", freshStart),
                    new EmployeeManyToOne("Floris", "J", "Johnson", freshStart),
                    new EmployeeManyToOne("Goris", "J", "Johnson", freshStart),
                    new EmployeeManyToOne("Doris", "J", "Johnson", freshStart),
                    new EmployeeManyToOne("Moris", "J", "Johnson", freshStart),
                    new EmployeeManyToOne("Toris", "J", "Johnson", freshStart),
                    new EmployeeManyToOne("Zoris", "J", "Johnson", freshStart),
                    new EmployeeManyToOne("Noris", "J", "Johnson", freshStart)
            );

            List<PublisherOneToMany> publishers = List.of(
                    new PublisherOneToMany("Dupak Books", "New Zealand", "AU", "NZ")
            );
            executeInTransaction((em) -> {
                em.persist(publishers.get(0));
                for (int i = 0; i < employees.size(); i++) {
                    employees.get(i).setPublisher(publishers.get(0));
                    em.persist(employees.get(i));
                }
            });
        }

    }

    @Nested
    @DisplayName("Removing entities can entail a lot of perhaps `unexpected` work")
    class RemovingEntitiesExamples {

        @Test
        @DisplayName("How many delete statements do you expect?")
        void removeThePubliserWith8Employees() {
            add1PublisherAnd8EmployeesInOneTx();
            executeInTransaction(em -> {
                em.remove(em.find(PublisherOneToManyCascadeRemove.class, 1L));
            });
        }

        @Test
        @DisplayName("How many select and deleted statements are issued when the Cascade.REMOVE is specified?")
        void countTheNumberOfSelectAndDeleteStatements() {
            add1PublisherAnd8EmployeesInOneTx();
            executeInTransaction(em -> {
                PublisherOneToManyCascadeRemove publisher = em.find(PublisherOneToManyCascadeRemove.class, 1L);
                for (EmployeeManyToOneCascadeRemove employee : publisher.getEmployees()) {
                    em.remove(employee);
                }
            });
            //One query to Select all the employees
            //Delete for each remove on employee
        }


        @Test
        @DisplayName("How many delete statements generates Hibernate?")
        void removeTheEmployeesWithADeleteQuery() {
            add1PublisherAnd8EmployeesInOneTx();
            executeInTransaction(em -> {
                Query delete = em.createQuery("DELETE FROM EmployeeManyToOneCascadeRemove e " +
                        "WHERE e.publisher.id =:publisher_id");
                delete.setParameter("publisher_id", 1L);
                delete.executeUpdate();
            });
        }

        @Test
        @DisplayName("How many delete statements generates Hibernate?")
        void removeTheEmployeesWithADeleteQueryWhichIsSlightlyDifferent() {
            add1PublisherAnd8EmployeesInOneTx();
            executeInTransaction(em -> {
                PublisherOneToManyCascadeRemove p = em.find(PublisherOneToManyCascadeRemove.class, 1L);
                Query delete = em.createQuery("DELETE FROM EmployeeManyToOneCascadeRemove e " +
                        "WHERE e.publisher =:publisher");
                delete.setParameter("publisher", p);
                delete.executeUpdate();
            });
        }

        @Test
        @DisplayName("How many delete statements generates Hibernate?")
        void removeTheEmployeesWithTheCorrectDeleteQueryButThereRemainsAManagedEntityInTheTx() {
            add1PublisherAnd8EmployeesInOneTx();
            executeInTransaction(em -> {
                var oneOfTheEmployeesThatShouldBeRemovedByTheDelete = em.find(EmployeeManyToOneCascadeRemove.class, 2L);
                Query delete = em.createQuery("DELETE FROM EmployeeManyToOneCascadeRemove e " +
                        "WHERE e.publisher.id =:id");
                delete.setParameter("id", 1L);
                delete.executeUpdate();
                var whoAreYou = em.find(EmployeeManyToOneCascadeRemove.class, 2L);
                //The managed entity is eligible for garbage collection because as a part of executing
                // the Tx also the em is closed.
            });
            executeInTransaction(em -> {
                var oneOfTheEmployeesThatShouldBeRemovedByTheDelete = em.find(EmployeeManyToOneCascadeRemove.class, 2L);
                assertThat(oneOfTheEmployeesThatShouldBeRemovedByTheDelete).isNull();
            });
        }
        private void add1PublisherAnd8EmployeesInOneTx() {
            Date freshStart = Date.valueOf(LocalDate.of(1970, 1, 1));
            List<EmployeeManyToOneCascadeRemove> employees = List.of(
                    new EmployeeManyToOneCascadeRemove("Boris", "J", "Johnson", freshStart),
                    new EmployeeManyToOneCascadeRemove("Floris", "J", "Johnson", freshStart),
                    new EmployeeManyToOneCascadeRemove("Goris", "J", "Johnson", freshStart),
                    new EmployeeManyToOneCascadeRemove("Doris", "J", "Johnson", freshStart),
                    new EmployeeManyToOneCascadeRemove("Moris", "J", "Johnson", freshStart),
                    new EmployeeManyToOneCascadeRemove("Toris", "J", "Johnson", freshStart),
                    new EmployeeManyToOneCascadeRemove("Zoris", "J", "Johnson", freshStart),
                    new EmployeeManyToOneCascadeRemove("Noris", "J", "Johnson", freshStart)
            );

            List<PublisherOneToManyCascadeRemove> publishers = List.of(
                    new PublisherOneToManyCascadeRemove("Dupak Books", "New Zealand", "AU", "NZ")
            );
            executeInTransaction((em) -> {
                var publisher = publishers.get(0);
                em.persist(publisher);
                for (int i = 0; i < employees.size(); i++) {
                    employees.get(i).setPublisher(publisher);
                    em.persist(employees.get(i));
                }
            });
        }

        //TODO Statistics object works different than I expect.
        //When debugging You can see that some of the many counters are non-zero
        //but the logging shows clearly a select statement and that one is not shown in the statistics!?
        //Actually I want to use the statistics object to get a handle of the number of queries executed but that
        //seems not that simple
        //See
        @Nested
        @DisplayName("How to get and hold grip on the number of queries issued by Hibernate?")
        class GettingToKnowAndUseHibernateStatisticsInTests {

            @Test
            void startASimplePersist() {
                executeInTransaction(em -> {
                    add1PublisherAnd8EmployeesInOneTx();
                });
                Session session = (Session) em;
                SessionFactory ssf = session.getSessionFactory();
                em.getTransaction().begin();
                getStatisticsInformationAboutTheGeneratedSql("Start test", ssf);
                var publisher = em.find(PublisherOneToManyCascadeRemove.class, 1L);

                getStatisticsInformationAboutTheGeneratedSql("After persist", ssf);
                em.flush();
                getStatisticsInformationAboutTheGeneratedSql("After flush", ssf);
                getEntityStatistics(PublisherOneToManyCascadeRemove.class, ssf);
                getEntityStatistics(EmployeeManyToOneCascadeRemove.class, ssf);
                em.getTransaction().commit();
                getStatisticsInformationAboutTheGeneratedSql("After commit", ssf);
                getEntityStatistics(PublisherOneToManyCascadeRemove.class, ssf);
                getEntityStatistics(EmployeeManyToOneCascadeRemove.class, ssf);
                //The queryCount is consistently zero what I do not expect
                // See https://thorben-janssen.com/hibernate-tips-count-executed-queries-session/
            }

            private void add1PublisherAnd8EmployeesInOneTx() {
                Date freshStart = Date.valueOf(LocalDate.of(1970, 1, 1));
                List<EmployeeManyToOneCascadeRemove> employees = List.of(
                        new EmployeeManyToOneCascadeRemove("Boris", "J", "Johnson", freshStart),
                        new EmployeeManyToOneCascadeRemove("Floris", "J", "Johnson", freshStart),
                        new EmployeeManyToOneCascadeRemove("Goris", "J", "Johnson", freshStart),
                        new EmployeeManyToOneCascadeRemove("Doris", "J", "Johnson", freshStart),
                        new EmployeeManyToOneCascadeRemove("Moris", "J", "Johnson", freshStart),
                        new EmployeeManyToOneCascadeRemove("Toris", "J", "Johnson", freshStart),
                        new EmployeeManyToOneCascadeRemove("Zoris", "J", "Johnson", freshStart),
                        new EmployeeManyToOneCascadeRemove("Noris", "J", "Johnson", freshStart)
                );

                List<PublisherOneToManyCascadeRemove> publishers = List.of(
                        new PublisherOneToManyCascadeRemove("Dupak Books", "New Zealand", "AU", "NZ")
                );
                executeInTransaction((em) -> {
                    var publisher = publishers.get(0);
                    em.persist(publisher);
                    for (int i = 0; i < employees.size(); i++) {
                        employees.get(i).setPublisher(publisher);
                        em.persist(employees.get(i));
                    }
                });
            }

            private void getStatisticsInformationAboutTheGeneratedSql(String timing, SessionFactory emf) {
                logger.info("Statistics is {} enabled", emf.getStatistics().isStatisticsEnabled() ? "" : "not");
                Statistics statistics = emf.getStatistics();
                long queryExecutionCount = statistics.getQueryExecutionCount();
                String[] queries = statistics.getQueries();
                logger.info(timing);
                logger.info("query Execution Count = {}", queryExecutionCount);
                logger.info(" Executed queries are: ");
                for (String query : queries) {
                    logger.info("Available key {}", query);
                }
                logger.info("\n");
            }

            <T> void getEntityStatistics(Class<T> clazz, SessionFactory sf) {
                Statistics stats = sf.getStatistics();
                EntityStatistics entityStats =
                        stats.getEntityStatistics(clazz.getName());
                long changes =
                        entityStats.getInsertCount()
                                + entityStats.getUpdateCount()
                                + entityStats.getDeleteCount();
                logger.info(clazz.getName() + " changed " + changes + "times");
            }
        }
    }

    @Nested
    @DisplayName("Adding the attribute OrphanRemoval=true to the @OneToMany association. \n" +
    "This implies that an employee without a publisher can't exist!")
    class OrphanRemovalExample{

        @Test
        @DisplayName("We remove 1 employee from the Collection, with OrphanRemoval is true it should be automatically deleted from the db")
        void removeOneEmployee() {

            add1PublisherAnd8EmployeesInOneTx();

            executeInTransaction(em -> {
                //Added specifically orphanRemoval=true for this test
                var publisher = em.find(PublisherOneToManyCascadeRemove.class, 1L);
                var employees = publisher.getEmployees();
                employees.removeIf(e -> e.getId() == 2);

            });
        }

        private void add1PublisherAnd8EmployeesInOneTx() {
            Date freshStart = Date.valueOf(LocalDate.of(1970, 1, 1));
            List<EmployeeManyToOneCascadeRemove> employees = List.of(
                    new EmployeeManyToOneCascadeRemove("Boris", "J", "Johnson", freshStart),
                    new EmployeeManyToOneCascadeRemove("Floris", "J", "Johnson", freshStart),
                    new EmployeeManyToOneCascadeRemove("Goris", "J", "Johnson", freshStart),
                    new EmployeeManyToOneCascadeRemove("Doris", "J", "Johnson", freshStart),
                    new EmployeeManyToOneCascadeRemove("Moris", "J", "Johnson", freshStart),
                    new EmployeeManyToOneCascadeRemove("Toris", "J", "Johnson", freshStart),
                    new EmployeeManyToOneCascadeRemove("Zoris", "J", "Johnson", freshStart),
                    new EmployeeManyToOneCascadeRemove("Noris", "J", "Johnson", freshStart)
            );

            List<PublisherOneToManyCascadeRemove> publishers = List.of(
                    new PublisherOneToManyCascadeRemove("Dupak Books", "New Zealand", "AU", "NZ")
            );
            executeInTransaction((em) -> {
                var publisher = publishers.get(0);
                em.persist(publisher);
                for (int i = 0; i < employees.size(); i++) {
                    employees.get(i).setPublisher(publisher);
                    em.persist(employees.get(i));
                }
            });
        }
    }


}
