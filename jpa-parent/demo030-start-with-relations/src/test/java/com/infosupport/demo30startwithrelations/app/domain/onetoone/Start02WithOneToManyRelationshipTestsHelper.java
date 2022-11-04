package com.infosupport.demo30startwithrelations.app.domain.onetoone;

import com.infosupport.demo10startwithjpa.app.domain.onetomany.manytoone.EmployeeManyToOne;
import com.infosupport.demo10startwithjpa.app.domain.onetomany.manytoone.PublisherOneToMany;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Date;
import java.time.LocalDate;
import java.util.function.Consumer;

public class Start02WithOneToManyRelationshipTestsHelper {
    static final Logger logger = LoggerFactory.getLogger(Start02WithOneToManyRelationshipTests.class);
    EntityTransaction tx;
    /* The common player we need for every test. */
    EntityManagerFactory emf;
    EntityManager em;

    @BeforeEach
    void setUp() {
        String persistenceUnitName = "jpa-hiber-postgres-pu-one-to-many-many-to-one";
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @Test
    @DisplayName("Persist a Publisher and Employee entity where Publisher has an uni-directional oneToMany relationship with Employee")
    void persistAPublisherAndEmployee() {

        Date freshStart = Date.valueOf(LocalDate.of(1970, 1, 1));
        EmployeeManyToOne emp = new EmployeeManyToOne("Boris", "J", "Johnson", freshStart);

        addOnePublisherToTheDatabaseInOneTx();

        executeInTransaction(em -> {
            PublisherOneToMany pub = em.find(PublisherOneToMany.class, 1L);
            //pub.getEmployees().add(emp);
            em.persist(emp);
            em.merge(pub);
        });
    }

    PublisherOneToMany addOnePublisherToTheDatabaseInOneTx() {
        var pub = new PublisherOneToMany("Scootney Books", "New York", "NY", "USA");
        executeInTransaction((em) -> {
            em.persist(pub);
        });
        return pub;
    }

    void getStatisticsInformationAboutTheGeneratedSql(String timing, SessionFactory emf) {
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

    void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em;
        em = emf.createEntityManager();
        try {
            logger.info("starting Tx");
            em.getTransaction().begin();
            consumer.accept(em);
            em.flush(); //Normally part of the commit, just to clarify
            em.getTransaction().commit();
            logger.info("committed Tx");

        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
                logger.info("rollbacked Tx");
            }
            throw new RuntimeException("Something unexpected went wrong in the test", e);
        } finally {
            if (em != null) {
                em.close();
            }
            logger.info("finishing finally");
        }
    }
}
