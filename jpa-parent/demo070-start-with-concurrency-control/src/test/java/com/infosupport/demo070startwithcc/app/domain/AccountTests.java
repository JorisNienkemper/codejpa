package com.infosupport.demo070startwithcc.app.domain;

import com.infosupport.demo070startwithcc.app.domain.app.domain.Account;
import org.junit.jupiter.api.Test;

import javax.persistence.LockModeType;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AccountTests extends AccountTestsHelper {

    @Test
    void transferAmount() {
        addTwoAccounts();
        showAccounts(1L, 2L);
        //pause(); //UnComment when running manually

        executeInTransaction(em -> {
            BigDecimal transferAmount = new BigDecimal("500.99");
            transferAmount(1L, 2L, transferAmount, em);
        });

        showAccounts(1L, 2L);
        //pause(); //UnComment when running manually
    }

    @Test
    void optimisticConcurrencyControl() {
        addTwoAccounts();
        //User 1 reads data
        Account accountAsSeenByUser1 = em.find(Account.class, 1L);
        //Mimick the behaviour that entity makes roundtrip browser and back  and is resurrected
        em.detach(accountAsSeenByUser1); //=> the entity is now detached == not managed
        //User 2 reads data (same data, same em, L1 cache
        Account accountAsSeenByUser2 = em.find(Account.class, 1L);
        //Mimick the behaviour that entity makes roundtrip browser and back  and is resurrected
        em.detach(accountAsSeenByUser2);

        showAccounts(1L, 2L);
        //pause(); //UnComment when running manually

        //User 1 changes the balance
        executeInTransaction(em -> {
            BigDecimal balance = accountAsSeenByUser1.getBalance();
            BigDecimal payment = new BigDecimal("111.11");
            accountAsSeenByUser1.add(payment);
            em.merge(accountAsSeenByUser1);
        });
        showAccounts(1L, 2L);
        //pause(); //UnComment when running manually
        //User 2 changes the balance
        assertThatThrownBy(() -> executeInTransaction(em -> {
            BigDecimal balance = accountAsSeenByUser2.getBalance();
            BigDecimal payment = new BigDecimal("222.22");
            accountAsSeenByUser2.add(payment);
            em.merge(accountAsSeenByUser2);
        })).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("javax.persistence.OptimisticLockException: Row was updated or deleted by another transaction");
        showAccounts(1L, 2L);
        //pause(); //UnComment when running manually
    }

    @Test
    void pessimisticConcurrencyControl() {
        addTwoAccounts();
        executeInTransaction(em -> {
            Account accountAsSeenByUser1 = em.find(Account.class, 1L, LockModeType.PESSIMISTIC_WRITE);
            //pause(); //UnComment when running manually
            //When pausing try to change this row via the console window
        });
        //pause(); //UnComment when running manually
    }
}
