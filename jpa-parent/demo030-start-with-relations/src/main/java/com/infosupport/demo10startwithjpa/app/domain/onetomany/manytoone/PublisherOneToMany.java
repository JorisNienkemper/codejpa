package com.infosupport.demo10startwithjpa.app.domain.onetomany.manytoone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class PublisherOneToMany {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String city;
    private String state;
    private String country;
    @OneToMany(mappedBy = "publisher")
    private Collection<EmployeeManyToOne> employees = new ArrayList<>();

    public PublisherOneToMany() {
    }

    public PublisherOneToMany(String name, String city, String state, String country) {
        setName(name);
        setCity(city);
        setState(state);
        setCountry(country);
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }


    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getState() {
        return state;
    }


    public void setState(String state) {
        this.state = state;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublisherOneToMany publisher = (PublisherOneToMany) o;

        if (id != publisher.id) return false;
        if (city != null ? !city.equals(publisher.city) : publisher.city != null) return false;
        if (country != null ? !country.equals(publisher.country) : publisher.country != null) return false;
        if (name != null ? !name.equals(publisher.name) : publisher.name != null) return false;
        return state != null ? state.equals(publisher.state) : publisher.state == null;
    }


    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }


    public Collection<EmployeeManyToOne> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<EmployeeManyToOne> employees) {
        this.employees = employees;
    }
}
