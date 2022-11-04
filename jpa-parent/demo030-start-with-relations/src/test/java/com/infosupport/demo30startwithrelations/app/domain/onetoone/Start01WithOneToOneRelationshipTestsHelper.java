package com.infosupport.demo30startwithrelations.app.domain.onetoone;

import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.function.Consumer;

public class Start01WithOneToOneRelationshipTestsHelper {
    static final Logger logger = LoggerFactory.getLogger(Start01WithOneToOneRelationshipTests.class);
    /* The common player we need for every test. */
    protected EntityManagerFactory emf;
    protected EntityManager em;
    EntityTransaction tx;

    @BeforeEach
    void setUp() {
        String persistenceUnitName = "jpa-hiber-postgres-pu-one-to-one";
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em;
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
            System.out.println("finishing try");
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            System.out.println("finishing catch");
            throw new RuntimeException("Something unexpected went wrong in the test", e);
        } finally {
            if (em != null) {
                em.close();
            }
            System.out.println("finishing finally");
        }
    }
}
