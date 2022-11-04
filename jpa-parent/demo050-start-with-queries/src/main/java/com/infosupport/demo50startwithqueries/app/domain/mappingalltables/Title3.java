package com.infosupport.demo50startwithqueries.app.domain.mappingalltables;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "title", schema = "public", catalog = "cursistdb")
public class Title3 {
    private Long id;
    private Collection<Sale> sales;

    @Id
    @GeneratedValue
    @Column(name = "title_id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Title3 title3 = (Title3) o;

        if (id != null ? !id.equals(title3.id) : title3.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @OneToMany(mappedBy = "title")
    public Collection<Sale> getSales() {
        return sales;
    }

    public void setSales(Collection<Sale> sales) {
        this.sales = sales;
    }
}
