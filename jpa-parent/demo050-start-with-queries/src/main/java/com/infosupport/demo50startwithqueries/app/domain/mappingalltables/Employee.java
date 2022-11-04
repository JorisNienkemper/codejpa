package com.infosupport.demo50startwithqueries.app.domain.mappingalltables;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class Employee {
    private Long id;
    private String firstname;
    private Date hireDate;
    private BigDecimal jobLevel;
    private String lastname;
    private String middleInitial;
    private Job job;
    private Publisher publisher;

    @Id
    @GeneratedValue
    @Column(name = "emp_id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fname", nullable = true, length = 255)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "hire_date", nullable = true)
    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Basic
    @Column(name = "job_lvl", nullable = true, precision = 2)
    public BigDecimal getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(BigDecimal jobLevel) {
        this.jobLevel = jobLevel;
    }

    @Basic
    @Column(name = "lname", nullable = true, length = 255)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "minit", nullable = true, length = 1)
    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String minimumit) {
        this.middleInitial = minimumit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != null ? !id.equals(employee.id) : employee.id != null) return false;
        if (firstname != null ? !firstname.equals(employee.firstname) : employee.firstname != null) return false;
        if (hireDate != null ? !hireDate.equals(employee.hireDate) : employee.hireDate != null) return false;
        if (jobLevel != null ? !jobLevel.equals(employee.jobLevel) : employee.jobLevel != null) return false;
        if (lastname != null ? !lastname.equals(employee.lastname) : employee.lastname != null) return false;
        if (middleInitial != null ? !middleInitial.equals(employee.middleInitial) : employee.middleInitial != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (hireDate != null ? hireDate.hashCode() : 0);
        result = 31 * result + (jobLevel != null ? jobLevel.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (middleInitial != null ? middleInitial.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName = "job_id")
    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @ManyToOne
    @JoinColumn(name = "pub_id", referencedColumnName = "pub_id")
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
