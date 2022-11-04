package com.infosupport.demo50startwithqueries.app.domain.mappingalltables;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "titleauthor", schema = "public", catalog = "cursistdb")
@IdClass(RoyaltyPK.class)
public class Royalty {

//    private EmbeddedRoyaltyPK embeddedRoyaltyPK;
    private Long authorId;
    private Long titleId;
    private BigDecimal authorOrder;
    private BigDecimal royaltyPercentage;
    private Author author;
    private Title title;

//    @EmbeddedId
//    public EmbeddedRoyaltyPK getEmbeddedRoyaltyPK() {
//        return embeddedRoyaltyPK;
//    }
//
//    public void setEmbeddedRoyaltyPK(EmbeddedRoyaltyPK embeddedRoyaltyPK) {
//        this.embeddedRoyaltyPK = embeddedRoyaltyPK;
//    }
    @Id
    @Column(name = "au_id", nullable = false)
    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Id
    @Column(name = "title_id", nullable = false)
    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    @Basic
    @Column(name = "au_ord", nullable = true, precision = 2)
    public BigDecimal getAuthorOrder() {
        return authorOrder;
    }

    public void setAuthorOrder(BigDecimal auOrd) {
        this.authorOrder = auOrd;
    }

    @Basic
    @Column(name = "royaltyper", nullable = true, precision = 2)
    public BigDecimal getRoyaltyPercentage() {
        return royaltyPercentage;
    }

    public void setRoyaltyPercentage(BigDecimal royaltypercentage) {
        this.royaltyPercentage = royaltypercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Royalty royalty = (Royalty) o;

//        if (authorId != null ? !authorId.equals(royalty.authorId) : royalty.authorId != null) return false;
//        if (titleId != null ? !titleId.equals(royalty.titleId) : royalty.titleId != null) return false;
        if (authorOrder != null ? !authorOrder.equals(royalty.authorOrder) : royalty.authorOrder != null) return false;
        if (royaltyPercentage != null ? !royaltyPercentage.equals(royalty.royaltyPercentage) : royalty.royaltyPercentage != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
//        int result = authorId != null ? authorId.hashCode() : 0;
//        result = 31 * result + (titleId != null ? titleId.hashCode() : 0);
//        result = 31 * result + (authorOrder != null ? authorOrder.hashCode() : 0);
        int result = authorOrder != null ? authorOrder.hashCode() : 0;
        result = 31 * result + (royaltyPercentage != null ? royaltyPercentage.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "au_id", referencedColumnName = "au_id", nullable = false)
    @MapsId("au_id")
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_id", referencedColumnName = "title_id", nullable = false)
    @MapsId("title_id")
    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}
