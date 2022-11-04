package org.infosupport.app.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;
import java.util.function.Consumer;

class TitleTest {

    EntityManagerFactory emf;
    EntityManager em;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("jpa-hiber-postgres-pu");
        em = emf.createEntityManager();
    }

    @Test
    void persistingEntities() {
        wordCreator(str -> {
            em.getTransaction().begin();
            em.persist(new Title(str));
            em.getTransaction().commit();
        }, 100);
    }

    @Test
    void readingFromCache() {
        //load the 2nd level cache
        exectuteInTx(em -> {
            wordCreator(str -> {

                em.persist(new Title(str));

            }, 10);
        });

        //Uncomment when testing manually
        //userDeterminedBreak();
        //read from the 2nd level cache
        exectuteInTx(em -> {
            for (Long id = 1L; id < 11; id++) {
                Title title = em.find(Title.class, id);
                System.out.println(title);
            }
        });
        //Uncomment when testing manually
        //userDeterminedBreak();
    }

    private void userDeterminedBreak() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hit RETURN to continue...");
        scanner.nextLine();
    }

    private void exectuteInTx(Consumer<EntityManager> consumer) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        consumer.accept(em);
        em.getTransaction().commit();
        em.close();
    }

    private void wordCreator(Consumer<String> consumer, int breakOut) {
        int wordcount = 0;
        int charValue1 = 0;
        while (charValue1 < 38) {
            int charValue2 = 0;
            //charValue-n runs from 0 to 38 minus 7 => 32 characters
            //"charValue1 charValue2" = 32 * 32 => 1024 words
            while (charValue2 < 38) {
                char[] chars = {(char) ('A' + charValue2), (char) ('A' + charValue1)};
                consumer.accept(new String(chars));
                wordcount++;
                if (breakOut == wordcount) {
                    return;
                }
                //Between 'A' and 'a' are 7 non-letter characters
                charValue2 += charValue2 != 25 ? 1 : 7;
            }
            charValue1 += charValue1 != 25 ? 1 : 7;
        }
        System.out.println("Strings created: " + wordcount);
    }

    @Test
    void asciTable() {
        for (int i = 0; i < 100; i++) {
            System.out.println((char) (' ' + i));
        }
    }
}
