package com.infosupport.demo50startwithqueries.app.domain.mappingalltables;

import javax.persistence.*;
import java.util.Collection;


@Entity
public class Publisher {
    private Long id;
    private String city;
    private String country;
    private String name;
    private String state;
    private PublisherInfo publisherInfo;
    private Collection<Title> titles;
    private Collection<Employee> employees;

    @Id
    @Column(name = "pub_id", nullable = false)
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
    @Column(name = "country", nullable = true, length = 255)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "pub_name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "state", nullable = true, length = 2)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publisher publisher = (Publisher) o;

        if (id != null ? !id.equals(publisher.id) : publisher.id != null) return false;
        if (city != null ? !city.equals(publisher.city) : publisher.city != null) return false;
        if (country != null ? !country.equals(publisher.country) : publisher.country != null) return false;
        if (name != null ? !name.equals(publisher.name) : publisher.name != null) return false;
        if (state != null ? !state.equals(publisher.state) : publisher.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @OneToOne(mappedBy = "publisher")
    public PublisherInfo getPublisherInfo() {
        return publisherInfo;
    }

    public void setPublisherInfo(PublisherInfo publisherInfo) {
        this.publisherInfo = publisherInfo;
    }

    @OneToMany(mappedBy = "publisher")
    public Collection<Title> getTitles() {
        return titles;
    }

    public void setTitles(Collection<Title> titles) {
        this.titles = titles;
    }

    @OneToMany(mappedBy = "publisher")
    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }

}
