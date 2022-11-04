package com.infosupport.demo30startwithrelations.app.domain.onetoone;

import javax.persistence.*;

@Entity
public class PublisherOneToOne {
    @Id
    @GeneratedValue
    private long id;
    private String city;
    private String country;
    private String name;
    private String state;
    @OneToOne(mappedBy = "publisher")
    private PublisherInfoOneToOne info;

    public PublisherOneToOne() {
    }

    public PublisherOneToOne(String city, String country, String name) {
        setCity(city);
        setCountry(country);
        setName(name);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublisherOneToOne publisher = (PublisherOneToOne) o;

        if (id != publisher.id) return false;
        if (city != null ? !city.equals(publisher.city) : publisher.city != null) return false;
        if (country != null ? !country.equals(publisher.country) : publisher.country != null) return false;
        if (name != null ? !name.equals(publisher.name) : publisher.name != null) return false;
        if (state != null ? !state.equals(publisher.state) : publisher.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

}
