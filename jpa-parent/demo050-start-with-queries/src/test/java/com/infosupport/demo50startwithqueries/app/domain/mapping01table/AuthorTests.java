package com.infosupport.demo50startwithqueries.app.domain.mapping01table;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

class AuthorTests {

    EntityManagerFactory emf;
    EntityManager em;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("mapping01table");
        em = emf.createEntityManager();
    }

    @Test
    void selectAllAuthors() {
        String jpqlString = "SELECT a FROM Author a";
        TypedQuery<Author> allAuthorsQuery = em.createQuery(jpqlString, Author.class);
        List<Author> authors = allAuthorsQuery.getResultList();
        authors.forEach(System.out::println);
    }

    @Test
    void getNamesAuthors() {
        String jpqlString = "SELECT new com.infosupport.demo50startwithqueries.app.domain.mapping01table.FamilyName(a.firstname,a.lastname) FROM Author a";
        TypedQuery<FamilyName> familyNameTypedQuery = em.createQuery(jpqlString, FamilyName.class);
        List<FamilyName> familyNames = familyNameTypedQuery.getResultList();
        familyNames.forEach(System.out::println);
    }


}

class FamilyName {
    private final String firstname;
    private final String lastname;
    //Constructor must be public
    public FamilyName(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "FamilyName{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
