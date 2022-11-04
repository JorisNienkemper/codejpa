package com.infosupport.demo10startwithjpa.app;

import com.infosupport.demo10startwithjpa.app.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;

public class StartWithJPATests {

    private static final Logger logger = LoggerFactory.getLogger(StartWithJPATests.class);
    EntityTransaction tx;
    /* The common player we need for every test. */
    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        String persistenceUnitName = "jpa-hiber-postgres-pu";
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @Test
    void persistBookWithoutAtGeneratedValue() {
        logger.info("start persistBookWithoutAtGeneratedValue");
        tx.begin();
        em.persist(new Book("John", "Surviving in the Java Logging Hell"));
        tx.commit();
        logger.info("finish persistBookWithoutAtGeneratedValue");
    }

    @Nested
    @DisplayName("Experiment where we use the @GeneratedValue annotation in combination with different strategies")
    class ExperimentWithGeneratedValueAndDifferentStrategiesTests {
        @Test
        @DisplayName("BOOK_AUTO: When will the id be available AND what sort of database work is involved")
        void persistBookAuto() {
            logger.info("start...persistBookAuto...");
            Book_AUTO book_auto = new Book_AUTO("John", "Surviving in the Java Logging Hell");
            tx.begin();
            em.persist(book_auto);
            logger.info("Break debugger here: " + book_auto.getClass().getSimpleName() + " value=" + book_auto);
            tx.commit();
            logger.info("finish...persistBookAuto...");
        }

        @Test
        @DisplayName("BOOK_TABLE: When will the id be available AND what sort of database work is involved")
        void persistBookTable() {
            logger.info("start...persistBookTable...");
            Book_TABLE book_table = new Book_TABLE("John", "Surviving in the Java Logging Hell");
            tx.begin();
            em.persist(book_table);
            logger.info("Break debugger here: " + book_table.getClass().getSimpleName() + " value=" + book_table);
            tx.commit();
            logger.info("finish...persistBookTable...");
        }

        @Test
        @DisplayName("BOOK_IDENTITY: When will the id be available AND what sort of database work is involved")
        void persistBookIdentity() {
            logger.info("start...persistBookTable...");
            Book_IDENTITY book_identity = new Book_IDENTITY("John", "Surviving in the Java Logging Hell");
            tx.begin();
            em.persist(book_identity);
            logger.info("Break debugger here: " + book_identity.getClass().getSimpleName() + " value=" + book_identity);
            tx.commit();
            logger.info("finish...persistBookTable...");
        }

        @Test
        @DisplayName("BOOK_SEQUENCE: When will the id be available AND what sort of database work is involved")
        void persistBookSequence() {
            logger.info("start...persistBookTable...");
            Book_SEQUENCE book_sequence = new Book_SEQUENCE("John", "Surviving in the Java Logging Hell");
            tx.begin();
            em.persist(book_sequence);
            logger.info("Break debugger here: " + book_sequence.getClass().getSimpleName() + " value=" + book_sequence);
            tx.commit();
            logger.info("finish...persistBookTable...");
        }
    }

    @Nested
    @DisplayName("Experiment where we look at the @GeneratedValue ranges annotation in combination with different strategies")
    class ExperimentGeneratedValueRangesOfDifferentStrategiesTests {
        @Test
        @DisplayName("BOOK_AUTO: When will the id be available AND what sort of database work is involved")
        void persistBookAuto() {
            logger.info("start...persistBookAuto...");
            tryToPersist3BookInstances(Book_AUTO.class);
            List<Integer> persistedIds = getIdsFrom(Book_AUTO.class);
            logger.info("id's used in Book_AUTO are: " + persistedIds);
            logger.info("finish...persistBookAuto...");
        }

        private <T> List<Integer> getIdsFrom(Class<T> clazz) {
            TypedQuery<Integer> query = em.createQuery("SELECT b.id FROM " + clazz.getSimpleName() + " b", Integer.class);
            List<Integer> usedIds = query.getResultList();
            return usedIds;
        }

        private <T> void tryToPersist3BookInstances(Class<T> bookType) {
            T book = null;
            for (int i = 0; i < 3; i++) {
                try {
                    book = bookType.getDeclaredConstructor(String.class, String.class)
                            .newInstance("John", "Surviving in the Java Logging Hell");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tx.begin();
                em.persist(book);
                logger.info("Break debugger here: " + book.getClass().getSimpleName() + " value=" + book);
                if (i % 2 == 0) {
                    tx.commit();
                } else {
                    tx.rollback();
                }
            }
        }

        @Test
        @DisplayName("BOOK_TABLE: When will the id be available AND what sort of database work is involved")
        void persistBookTable() {
            logger.info("start...persistBookTable...");
            tryToPersist3BookInstances(Book_TABLE.class);
            List<Integer> persistedIds = getIdsFrom(Book_TABLE.class);
            logger.info("id's used in Book_TABLE are: " + persistedIds);
            logger.info("finish...persistBookTable...");
        }

        @Test
        @DisplayName("BOOK_IDENTITY: When will the id be available AND what sort of database work is involved")
        void persistBookIdentity() {
            logger.info("start...persistBookIdentity...");
            tryToPersist3BookInstances(Book_IDENTITY.class);
            List<Integer> persistedIds = getIdsFrom(Book_IDENTITY.class);
            logger.info("id's used in Book_IDENTITY are: " + persistedIds);
            logger.info("finish...persistBookIdentity...");
        }

        @Test
        @DisplayName("BOOK_SEQUENCE: When will the id be available AND what sort of database work is involved")
        void persistBookSequence() {
            logger.info("start...persistBookSequence...");
            tryToPersist3BookInstances(Book_SEQUENCE.class);
            List<Integer> persistedIds = getIdsFrom(Book_SEQUENCE.class);
            logger.info("id's used in Book_SEQUENCE are: " + persistedIds);
            logger.info("finish...persistBookSequence...");
        }
    }
}
