package com.infosupport.demo30startwithrelations.app.domain.onetoone;

import org.slf4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.function.Consumer;

public class TxUtility {

    private EntityManagerFactory emf;
    private Logger logger;

    public TxUtility(EntityManagerFactory emf, Logger logger) {
        this.emf = emf;
        this.logger = logger;
    }

    public void executeInTransaction(Consumer<EntityManager> consumer) {
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
