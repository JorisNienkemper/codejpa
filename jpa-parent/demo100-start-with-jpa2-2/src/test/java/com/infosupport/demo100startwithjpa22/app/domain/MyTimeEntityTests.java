package com.infosupport.demo100startwithjpa22.app.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.infosupport.demo100startwithjpa22.app.domain.LocalDateEntity;
import com.infosupport.demo100startwithjpa22.app.domain.LocalTimeEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.Consumer;


public class MyTimeEntityTests {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("jpa-hiber-postgres-pu");
        em = emf.createEntityManager();
    }

    @Test
    void startWithLocalDate() {

        LocalDateEntity localDateEntity = new LocalDateEntity(LocalDate.now());

        executeInsideTx(em -> {
            em.persist(localDateEntity);
        });

    }

    @Test
    void startWithLocalTime() {

        LocalTimeEntity localTimeEntity = new LocalTimeEntity(LocalTime.now());

        executeInsideTx(em -> {
            em.persist(localTimeEntity);
        });

        executeInsideTx(em -> {
            LocalTimeEntity time24h = em.find(LocalTimeEntity.class, 1L);
            System.out.println("The time = " + time24h.getTime24h());
        });

    }

    void executeInsideTx(Consumer<EntityManager> jpaCode) {
        em.getTransaction().begin();
        jpaCode.accept(em);
        em.getTransaction().commit();
    }
}
