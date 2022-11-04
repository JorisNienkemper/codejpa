package com.infosupport.demo30startwithrelations.app.domain.onetoone;


import com.infosupport.demo30startwithrelations.app.domain.manytomany.unidirectional.Author;
import com.infosupport.demo30startwithrelations.app.domain.manytomany.unidirectional.TitleWithList;
import com.infosupport.demo30startwithrelations.app.domain.manytomany.unidirectional.TitleWithSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Start03WithManyToManyAssociationsUniDirectionalTests extends Start03WithManyToManyAssociationsUniDirectionalTestsHelper {
    @Nested
    @DisplayName("Tests with an uni-directional many to many association with Title and a List of authors")
    class ManyToManyUnidirectionalWithListTests {

        @Test
        @DisplayName("An author has written on or more titles. Titles are written by one or more authors.")
        void fetchAuthors() {
            addTwoAuthorsWithEachThreeTitlesWithList();
            txUtility.executeInTransaction(em -> {
                TitleWithList title = em.find(TitleWithList.class, 3L);
                List<Author> authors = title.getAuthors();
                Author author = authors.get(0);
                logger.info("first author {}", author.getName());
            });
        }

        @Test
        @DisplayName("Remove an author as author from a title")
        void removeAnAuthor() {
            addTwoAuthorsWithEachThreeTitlesWithList();
            txUtility.executeInTransaction(em -> {
                TitleWithList title = em.find(TitleWithList.class, 3L);
                List<Author> authors = title.getAuthors();
                Author author = authors.get(0);
                title.getAuthors().remove(author);
            });
        }

        @Test
        @DisplayName("Authors have cooperated to the same titles. Remove one author as a contributor")
        void removeATitle() {
            addTwoAuthorsWithEachThreeTitlesWithList();
            txUtility.executeInTransaction(em -> {
                TitleWithList title1 = em.find(TitleWithList.class, 3L);
                Author author1 = title1.getAuthors().get(0);
                TitleWithList title2 = em.find(TitleWithList.class, 7L);
                Author author2 = title2.getAuthors().get(0);
                title1.getAuthors().add(author2);
                title2.getAuthors().add(author1);
                assertThat(title1.getAuthors().size()).isEqualTo(2);
                assertThat(title2.getAuthors().size()).isEqualTo(2);
            });
            txUtility.executeInTransaction(em -> {
                TitleWithList title = em.find(TitleWithList.class, 3L);
                Author author = title.getAuthors().get(0);
                title.getAuthors().remove(author);
            });
        }
    }

    @Nested
    @DisplayName("Tests with an uni-directional many to many association with Title and a List of authors")
    class ManyToManyUnidirectionalWithSetTests {

        @Test
        @DisplayName("Authors have cooperated to the same titles. Remove one author as a contributor")
        void removeATitle() {
            addTwoAuthorsWithEachThreeTitlesWithSet();
            txUtility.executeInTransaction(em -> {
                TitleWithSet title1 = em.find(TitleWithSet.class, 3L);
                Author author1 = title1.getAuthors().iterator().next();
                TitleWithSet title2 = em.find(TitleWithSet.class, 7L);
                Author author2 = title2.getAuthors().iterator().next();
                title1.getAuthors().add(author2);
                title2.getAuthors().add(author1);
                assertThat(title1.getAuthors().size()).isEqualTo(2);
                assertThat(title2.getAuthors().size()).isEqualTo(2);
            });
            txUtility.executeInTransaction(em -> {
                TitleWithSet title = em.find(TitleWithSet.class, 3L);
                Author author = title.getAuthors().iterator().next();
                title.getAuthors().remove(author);
            });
        }

    }
}
