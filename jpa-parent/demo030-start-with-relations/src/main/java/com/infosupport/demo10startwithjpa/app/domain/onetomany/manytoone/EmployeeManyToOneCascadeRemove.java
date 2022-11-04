package com.infosupport.demo10startwithjpa.app.domain.onetomany.manytoone;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class EmployeeManyToOneCascadeRemove {
    @Id
    @GeneratedValue
    private long id;
    private String firstname;
    private String middleInitial;
    private String lastname;
    private Date hireDate;
    private BigDecimal jobLevel;
    @ManyToOne(fetch = FetchType.LAZY)
    private PublisherOneToManyCascadeRemove publisher;

    public EmployeeManyToOneCascadeRemove() {
    }

    public EmployeeManyToOneCascadeRemove(String firstname, String middleInitial, String lastname, Date hireDate) {
        setFirstname(firstname);
        setMiddleInitial(middleInitial);
        setLastname(lastname);
        setHireDate(hireDate);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public BigDecimal getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(BigDecimal jobLevel) {
        this.jobLevel = jobLevel;
    }

    public PublisherOneToManyCascadeRemove getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherOneToManyCascadeRemove publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeManyToOneCascadeRemove employee = (EmployeeManyToOneCascadeRemove) o;

        if (id != employee.id) return false;
        if (firstname != null ? !firstname.equals(employee.firstname) : employee.firstname != null) return false;
        if (hireDate != null ? !hireDate.equals(employee.hireDate) : employee.hireDate != null) return false;
        if (jobLevel != null ? !jobLevel.equals(employee.jobLevel) : employee.jobLevel != null) return false;
        if (lastname != null ? !lastname.equals(employee.lastname) : employee.lastname != null) return false;
        if (middleInitial != null ? !middleInitial.equals(employee.middleInitial) : employee.middleInitial != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (hireDate != null ? hireDate.hashCode() : 0);
        result = 31 * result + (jobLevel != null ? jobLevel.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (middleInitial != null ? middleInitial.hashCode() : 0);
        return result;
    }

}
