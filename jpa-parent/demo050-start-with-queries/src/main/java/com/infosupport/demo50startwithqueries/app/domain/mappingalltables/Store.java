package com.infosupport.demo50startwithqueries.app.domain.mappingalltables;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Store {
    private Long id;
    private String city;
    private String state;
    private String storeAddress;
    private String name;
    private String zip;
    private Collection<Discount> discounts;
    private Collection<Sale> sales;

    @Id
    @GeneratedValue
    @Column(name = "stor_id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "city", nullable = true, length = 255)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "state", nullable = true, length = 2)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "stor_address", nullable = true, length = 255)
    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    @Basic
    @Column(name = "stor_name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "zip", nullable = true, length = 5)
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Store store = (Store) o;

        if (id != null ? !id.equals(store.id) : store.id != null) return false;
        if (city != null ? !city.equals(store.city) : store.city != null) return false;
        if (state != null ? !state.equals(store.state) : store.state != null) return false;
        if (storeAddress != null ? !storeAddress.equals(store.storeAddress) : store.storeAddress != null) return false;
        if (name != null ? !name.equals(store.name) : store.name != null) return false;
        if (zip != null ? !zip.equals(store.zip) : store.zip != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (storeAddress != null ? storeAddress.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "store")
    public Collection<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Collection<Discount> discounts) {
        this.discounts = discounts;
    }

    @OneToMany(mappedBy = "store")
    public Collection<Sale> getSales() {
        return sales;
    }

    public void setSales(Collection<Sale> sales) {
        this.sales = sales;
    }
}
