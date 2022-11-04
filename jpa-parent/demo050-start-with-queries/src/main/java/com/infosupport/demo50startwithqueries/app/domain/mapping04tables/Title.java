package com.infosupport.demo50startwithqueries.app.domain.mapping04tables;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Title {
    private Long id;
    private BigDecimal advance;
    private String notes;
    private BigDecimal price;
    private Date publicationDate;
    private BigDecimal royalty;
    private String title;
    private String type;
    private BigDecimal ytdSale;
    private Collection<Royalty> royaltiesPerAuthor;
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
    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationdate) {
        this.publicationDate = publicationdate;
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

        Title title1 = (Title) o;

        if (id != null ? !id.equals(title1.id) : title1.id != null) return false;
        if (advance != null ? !advance.equals(title1.advance) : title1.advance != null) return false;
        if (notes != null ? !notes.equals(title1.notes) : title1.notes != null) return false;
        if (price != null ? !price.equals(title1.price) : title1.price != null) return false;
        if (publicationDate != null ? !publicationDate.equals(title1.publicationDate) : title1.publicationDate != null)
            return false;
        if (royalty != null ? !royalty.equals(title1.royalty) : title1.royalty != null) return false;
        if (title != null ? !title.equals(title1.title) : title1.title != null) return false;
        if (type != null ? !type.equals(title1.type) : title1.type != null) return false;
        if (ytdSale != null ? !ytdSale.equals(title1.ytdSale) : title1.ytdSale != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (advance != null ? advance.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        result = 31 * result + (royalty != null ? royalty.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (ytdSale != null ? ytdSale.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "title")
    public Collection<Royalty> getRoyaltiesPerAuthor() {
        return royaltiesPerAuthor;
    }

    public void setRoyaltiesPerAuthor(Collection<Royalty> royaltiesPerAuthor) {
        this.royaltiesPerAuthor = royaltiesPerAuthor;
    }

    @OneToMany(mappedBy = "title")
    public Collection<ScheduledRoyalty> getScheduledRoyalties() {
        return scheduledRoyalties;
    }

    public void setScheduledRoyalties(Collection<ScheduledRoyalty> scheduledRoyalties) {
        this.scheduledRoyalties = scheduledRoyalties;
    }
}
