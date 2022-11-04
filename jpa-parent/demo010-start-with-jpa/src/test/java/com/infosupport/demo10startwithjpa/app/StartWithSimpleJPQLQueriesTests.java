package com.infosupport.demo10startwithjpa.app;

import com.infosupport.demo10startwithjpa.app.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StartWithSimpleJPQLQueriesTests {
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
    @DisplayName("Select all the entities of type Book")
    void createJPQLQueryOnEntityBook() {
        var numberOfBooksSaved = 2;
        addBooksToTable(numberOfBooksSaved);
        var queryString = "SELECT b FROM Book b";
        var jpqlQuery = em.createQuery(queryString, Book.class);
        var books = jpqlQuery.getResultList();
        var numberOfBooksRetrieved = books.size();
        assertThat(numberOfBooksRetrieved).isEqualTo(numberOfBooksSaved);
    }

    private void addBooksToTable(int numberOfBooks) {
        tx.begin();
        for (int bookNumber = 1; bookNumber <= numberOfBooks; bookNumber++) {
            em.persist(new Book("bookName" + bookNumber, "bookTitle" + bookNumber));
        }
        tx.commit();
    }

    @Test
    @DisplayName("Select all the entities of type Book")
    void selectBookWithBookName1() {
        var numberOfBooksSaved = 2;
        addBooksToTable(numberOfBooksSaved);
        var queryString = "SELECT b FROM Book b WHERE b.name = 'bookName1'";
        var jpqlQuery = em.createQuery(queryString, Book.class);
        var books = jpqlQuery.getResultList();
        var numberOfBooksRetrieved = books.size();
        assertThat(numberOfBooksRetrieved).isEqualTo(1);
    }
}
