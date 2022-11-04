package com.infosupport.demo50startwithqueries.app.domain.mapping01table;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.function.Consumer;

public class JPQLTests {
    EntityManagerFactory emf;
    EntityManager em;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("mapping03tables");
        em = emf.createEntityManager();
    }

    void executeInTransaction(Consumer<EntityManager> jpaCode) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            jpaCode.accept(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
                throw new RuntimeException("jammer dan , niet gelukt", e);
            }

        } finally {
            em.close();
        }
    }

    @Test
    void jpqlQueries1() {
        String jpqlString = "SELECT r.author.firstname FROM Royalty r";
        TypedQuery<String> query = em.createQuery(jpqlString, String.class);
        List<String> namen = query.getResultList();
        for(String naam: namen){
            System.out.println(naam);
        }
    }

    @Test
    void getFullNamesOfAutors() {
        String jpqlString ="SELECT new com.infosupport.demo50startwithqueries.app.domain.mapping01table.Fullname(a.firstname, a.lastname) FROM Author a";
        TypedQuery<Fullname> query = em.createQuery(jpqlString, Fullname.class);
        List<Fullname> fullnames = query.getResultList();
        for(Fullname fullname: fullnames){
            System.out.println(fullname);
        }
    }
}

class Fullname{
    private String firstname;
    private String lastname;

    public Fullname(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Fullname{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
