package com.infosupport.demo30startwithrelations.app.domain.onetoone;

import com.infosupport.demo30startwithrelations.app.domain.manytomany.unidirectional.Author;
import com.infosupport.demo30startwithrelations.app.domain.manytomany.unidirectional.TitleWithSet;
import com.infosupport.demo30startwithrelations.app.domain.manytomany.unidirectional.TitleWithList;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

public class Start03WithManyToManyAssociationsUniDirectionalTestsHelper {
    static final Logger logger = LoggerFactory.getLogger(Start02WithOneToManyRelationshipTests.class);
    EntityTransaction tx;
    /* The common player we need for every test. */
    EntityManagerFactory emf;
    EntityManager em;
    TxUtility txUtility;

    @BeforeEach
    void setUp() {
        String persistenceUnitName = "many-to-many-unidirectional";
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        em = emf.createEntityManager();
        tx = em.getTransaction();
        txUtility = new TxUtility(emf, logger);
    }

    <T> T createTitleLike(Class<T> clazz, String name) {
        try {
            Constructor<T> constructor = clazz.getConstructor(String.class);
            return constructor.newInstance(name);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    void addTwoAuthorsWithEachThreeTitlesWithList() {
        var john = new Author("John");
        var joppe = new Author("Joppe");
        List<TitleWithList> titlesOfJohn = List.of(new TitleWithList("Ground High"), new TitleWithList("Summer Low"), new TitleWithList("Happy all!"));
        List<TitleWithList> titlesOfJoppe = List.of(new TitleWithList("Dark white"), new TitleWithList("Metals"), new TitleWithList("Restless"));

        addAuthorToTitlesWithList(john, titlesOfJohn);
        addAuthorToTitlesWithList(joppe, titlesOfJoppe);
    }

    void addAuthorToTitlesWithList(Author author, List<TitleWithList> titles) {
        txUtility.executeInTransaction(em -> {
            em.persist(author);
            titles.forEach(title -> {
                title.addAuthor(author);
                em.persist(title);
            });
        });
    }

    void addTwoAuthorsWithEachThreeTitlesWithSet() {
        var john = new Author("John");
        var joppe = new Author("Joppe");
        Set<TitleWithSet> titlesOfJohn = Set.of(new TitleWithSet("Ground High"), new TitleWithSet("Summer Low"), new TitleWithSet("Happy all!"));
        Set<TitleWithSet> titlesOfJoppe = Set.of(new TitleWithSet("Dark white"), new TitleWithSet("Metals"), new TitleWithSet("Restless"));

        addAuthorToTitles(john, titlesOfJohn);
        addAuthorToTitles(joppe, titlesOfJoppe);
    }

    void addAuthorToTitles(Author author, Set<TitleWithSet> titles) {
        txUtility.executeInTransaction(em -> {
            em.persist(author);
            titles.forEach(title -> {
                title.addAuthor(author);
                em.persist(title);
            });
        });
    }
}
