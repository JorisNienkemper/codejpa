package com.infosupport.demo30startwithrelations.app.domain.onetoone;

import com.infosupport.demo30startwithrelations.app.domain.manytomany.bidirectional.Author;
import com.infosupport.demo30startwithrelations.app.domain.manytomany.bidirectional.Title;
import com.infosupport.demo30startwithrelations.app.domain.manytomany.bidirectional.builder.TitleBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Start04WithManyToManyBiDirectionalTests {

    private static final Logger logger = LoggerFactory.getLogger(Start02WithOneToManyRelationshipTests.class);
    EntityTransaction tx;
    /* The common player we need for every test. */
    private EntityManagerFactory emf;
    private EntityManager em;
    private TxUtility txUtility;

    @Nested
    @DisplayName("Tests with an bi-directional many to many association")
    class ManyToManyBidirectionalTests {

        @BeforeEach
        void setUp() {
            String persistenceUnitName = "many-to-many-bidirectional";
            emf = Persistence.createEntityManagerFactory(persistenceUnitName);
            em = emf.createEntityManager();
            tx = em.getTransaction();
            txUtility = new TxUtility(emf, logger);
        }

        @Test
        @DisplayName("An author has written on or more titles. Titles are written by one or more authors.")
        void fetchAuthors() {
            addTwoAuthorsWithEachThreeTitles();
            txUtility.executeInTransaction(em -> {
                Title title = em.find(Title.class, 3L);
                List<Author> authors = title.getAuthors();
                Author author = authors.get(0);
                boolean isRegistered = author.getTitles().contains(title);
                logger.info("Title {} with first author {} is registerd ", title.getName(), author.getName(), isRegistered);
            });
        }

        private void addTwoAuthorsWithEachThreeTitles() {
            var john = new Author("John");
            var joppe = new Author("Joppe");
            List<Title> titlesOfJohn = List.of(new Title("Ground High"), new Title("Summer Low"), new Title("Happy all!"));
            List<Title> titlesOfJoppe = List.of(new Title("Dark white"), new Title("Metals"), new Title("Restless"));

            addAuthorToTitles(john, titlesOfJohn);
            addAuthorToTitles(joppe, titlesOfJoppe);
        }

        private void addAuthorToTitles(Author author, List<Title> titles) {
            txUtility.executeInTransaction(em -> {
                em.persist(author);
                titles.forEach(title -> {
                    title.addAuthor(author);
                    em.persist(title);
                });
            });
        }

        private void addTwoAuthorsWithEachThreeTitlesWithBuilderPattern() {
            TitleBuilder.addTitle()
                    .withName("Hope")
                    .addAuthors();
        }

    }
}
