package com.infosupport.demo070startwithcc.app.domain;

import com.infosupport.demo070startwithcc.app.domain.app.domain.Account;
import org.junit.jupiter.api.BeforeEach;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

class AccountTestsHelper {
    EntityManagerFactory emf;
    EntityManager em;

    @BeforeEach
    void setUp() {
        emf = Persistence.createEntityManagerFactory("jpa-cc");
        em = emf.createEntityManager();
    }

    protected void transferAmount(Long accountId1, Long accountId2, BigDecimal transferAmount, EntityManager em) {
        Account account1 = em.find(Account.class, accountId1);
        Account account2 = em.find(Account.class, accountId2);
        BigDecimal balance1 = account1.getBalance();
        BigDecimal newBalance1 = balance1.add(transferAmount);
        BigDecimal balance2 = account2.getBalance();
        BigDecimal newBalance2 = balance2.subtract(transferAmount);
        account1.setBalance(newBalance1);
        account2.setBalance(newBalance2);
    }

    protected void showAccounts(Long accountId1, Long accountId2) {
        String jpqlString =
                """
                SELECT a 
                FROM Account a
                WHERE a.id IN (:id1,:id2)
                """;
        TypedQuery<Account> query = em.createQuery(jpqlString, Account.class);
        query.setParameter("id1",accountId1);
        query.setParameter("id2",accountId2);
        List<Account> accounts = query.getResultList();
        accounts.forEach(System.out::println);
    }

    void executeInTransaction(Consumer<EntityManager> jpaCode) {
        EntityManager em = emf.createEntityManager();
        this.em.getTransaction().begin();
        try {
            jpaCode.accept(this.em);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            if (this.em != null) {
                this.em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        }finally {
            if(em != null){
                em.close();
            }
        }
    }

    void pause(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hit Return twice to continue...");
        scanner.nextLine();
    }

    void addTwoAccounts() {
        executeInTransaction(em -> {
            em.persist(new Account("Jan", new BigDecimal("1000.00")));
            em.persist(new Account("Piet", new BigDecimal("1000.00")));
        });
    }
}
