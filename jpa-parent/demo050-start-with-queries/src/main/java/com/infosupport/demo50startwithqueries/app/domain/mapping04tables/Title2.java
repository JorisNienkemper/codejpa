package com.infosupport.demo50startwithqueries.app.domain.mapping04tables;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "title", schema = "public", catalog = "cursistdb")
public class Title2 {
    private Long id;
    private BigDecimal advance;
    private String notes;
    private BigDecimal price;
    private Date publishingDate;
    private BigDecimal royalty;
    private String title;
    private String type;
    private BigDecimal ytdSale;
    private Collection<ScheduledRoyalty> scheduledRoyalties;


    @Id
    @GeneratedValue
    @Column(name = "title_id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "advance", nullable = true, precision = 2)
    public BigDecimal getAdvance() {
        return advance;
    }

    public void setAdvance(BigDecimal advance) {
        this.advance = advance;
    }

    @Basic
    @Column(name = "notes", nullable = true, length = 255)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "pubdate", nullable = true)
    public Date getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(Date publishingDate) {
        this.publishingDate = publishingDate;
    }

    @Basic
    @Column(name = "royalty", nullable = true, precision = 2)
    public BigDecimal getRoyalty() {
        return royalty;
    }

    public void setRoyalty(BigDecimal royalty) {
        this.royalty = royalty;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 12)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "ytd_sale", nullable = true, precision = 2)
    public BigDecimal getYtdSale() {
        return ytdSale;
    }

    public void setYtdSale(BigDecimal ytdSale) {
        this.ytdSale = ytdSale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Title2 title2 = (Title2) o;

        if (id != null ? !id.equals(title2.id) : title2.id != null) return false;
        if (advance != null ? !advance.equals(title2.advance) : title2.advance != null) return false;
        if (notes != null ? !notes.equals(title2.notes) : title2.notes != null) return false;
        if (price != null ? !price.equals(title2.price) : title2.price != null) return false;
        if (publishingDate != null ? !publishingDate.equals(title2.publishingDate) : title2.publishingDate != null)
            return false;
        if (royalty != null ? !royalty.equals(title2.royalty) : title2.royalty != null) return false;
        if (title != null ? !title.equals(title2.title) : title2.title != null) return false;
        if (type != null ? !type.equals(title2.type) : title2.type != null) return false;
        if (ytdSale != null ? !ytdSale.equals(title2.ytdSale) : title2.ytdSale != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (advance != null ? advance.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (publishingDate != null ? publishingDate.hashCode() : 0);
        result = 31 * result + (royalty != null ? royalty.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (ytdSale != null ? ytdSale.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "title")
    public Collection<ScheduledRoyalty> getScheduledRoyalties() {
        return scheduledRoyalties;
    }

    public void setScheduledRoyalties(Collection<ScheduledRoyalty> scheduledRoyalties) {
        this.scheduledRoyalties = scheduledRoyalties;
    }

}
