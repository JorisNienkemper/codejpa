package com.infosupport.demo070startwithcc.app.domain.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.math.BigDecimal;

@Entity
public class Account {

    @Id @GeneratedValue
    private Long id;
    @Version
    private Long version;
    private String name;
    private BigDecimal balance;

    public Account() {
    }

    public Account(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    public Account(Long id, String name, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (!id.equals(account.id)) return false;
        if (!version.equals(account.version)) return false;
        if (!name.equals(account.name)) return false;
        return balance.equals(account.balance);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + version.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + balance.hashCode();
        return result;
    }

    public void add(BigDecimal payment) {
        this.setBalance(balance.add(payment));
    }
}
