package com.infosupport.demo50startwithqueries.app.domain.mappingalltables;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
public class Job {
    private Long id;
    private String description;
    private BigDecimal maximumLevel;
    private BigDecimal minimumLevel;
    private Collection<Employee> employees;

    @Id
    @GeneratedValue
    @Column(name = "job_id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "job_desc", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "max_lvl", nullable = true, precision = 2)
    public BigDecimal getMaximumLevel() {
        return maximumLevel;
    }

    public void setMaximumLevel(BigDecimal maximumLevel) {
        this.maximumLevel = maximumLevel;
    }

    @Basic
    @Column(name = "min_lvl", nullable = true, precision = 2)
    public BigDecimal getMinimumLevel() {
        return minimumLevel;
    }

    public void setMinimumLevel(BigDecimal minimumLevel) {
        this.minimumLevel = minimumLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (id != null ? !id.equals(job.id) : job.id != null) return false;
        if (description != null ? !description.equals(job.description) : job.description != null) return false;
        if (maximumLevel != null ? !maximumLevel.equals(job.maximumLevel) : job.maximumLevel != null) return false;
        if (minimumLevel != null ? !minimumLevel.equals(job.minimumLevel) : job.minimumLevel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (maximumLevel != null ? maximumLevel.hashCode() : 0);
        result = 31 * result + (minimumLevel != null ? minimumLevel.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "job")
    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }
}
