package com.infosupport.demo50startwithqueries.app.domain.mappingalltables;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "roysched", schema = "public", catalog = "cursistdb")
public class ScheduledRoyalty {
    private Long id;
    private BigDecimal highRange;
    private BigDecimal lowRange;
    private BigDecimal royalty;
    private Title title;

    @Id
    @GeneratedValue
    @Column(name = "roy_id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "hirange", nullable = true, precision = 2)
    public BigDecimal getHighRange() {
        return highRange;
    }

    public void setHighRange(BigDecimal highRange) {
        this.highRange = highRange;
    }

    @Basic
    @Column(name = "lorange", nullable = true, precision = 2)
    public BigDecimal getLowRange() {
        return lowRange;
    }

    public void setLowRange(BigDecimal lowRange) {
        this.lowRange = lowRange;
    }

    @Basic
    @Column(name = "royalty", nullable = true, precision = 2)
    public BigDecimal getRoyalty() {
        return royalty;
    }

    public void setRoyalty(BigDecimal royalty) {
        this.royalty = royalty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduledRoyalty that = (ScheduledRoyalty) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (highRange != null ? !highRange.equals(that.highRange) : that.highRange != null) return false;
        if (lowRange != null ? !lowRange.equals(that.lowRange) : that.lowRange != null) return false;
        if (royalty != null ? !royalty.equals(that.royalty) : that.royalty != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (highRange != null ? highRange.hashCode() : 0);
        result = 31 * result + (lowRange != null ? lowRange.hashCode() : 0);
        result = 31 * result + (royalty != null ? royalty.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "title_id", referencedColumnName = "title_id")
    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
