package com.infosupport.demo50startwithqueries.app.domain.mapping03tables;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
public class Author {
    private Long id;
    private String address;
    private String firstname;
    private String lastname;
    private String city;
    private BigDecimal contract;
    private String phone;
    private String state;
    private String zip;
    private Collection<Royalty> royaltiesPerTitle;

    @Id
    @GeneratedValue
    @Column(name = "au_id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "au_fname", nullable = true, length = 255)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "au_lname", nullable = true, length = 255)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
    @Column(name = "contract", nullable = true, precision = 2)
    public BigDecimal getContract() {
        return contract;
    }

    public void setContract(BigDecimal contract) {
        this.contract = contract;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 16)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
    @Column(name = "zip", nullable = true, length = 16)
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

        Author author = (Author) o;

        if (id != null ? !id.equals(author.id) : author.id != null) return false;
        if (address != null ? !address.equals(author.address) : author.address != null) return false;
        if (firstname != null ? !firstname.equals(author.firstname) : author.firstname != null) return false;
        if (lastname != null ? !lastname.equals(author.lastname) : author.lastname != null) return false;
        if (city != null ? !city.equals(author.city) : author.city != null) return false;
        if (contract != null ? !contract.equals(author.contract) : author.contract != null) return false;
        if (phone != null ? !phone.equals(author.phone) : author.phone != null) return false;
        if (state != null ? !state.equals(author.state) : author.state != null) return false;
        if (zip != null ? !zip.equals(author.zip) : author.zip != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (contract != null ? contract.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "author")
    public Collection<Royalty> getRoyaltiesPerAuthor() {
        return royaltiesPerTitle;
    }

    public void setRoyaltiesPerAuthor(Collection<Royalty> royaltiesPerTitle) {
        this.royaltiesPerTitle = royaltiesPerTitle;
    }
}
