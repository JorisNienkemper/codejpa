package com.infosupport.demo50startwithqueries.app.domain.mapping04tables;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class RoyaltyPK implements Serializable {
    private Long authorId;
    private Long titleId;

    @Column(name = "au_id", nullable = false)
    @Id
    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Column(name = "title_id", nullable = false)
    @Id
    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoyaltyPK royaltyPK = (RoyaltyPK) o;

        if (authorId != null ? !authorId.equals(royaltyPK.authorId) : royaltyPK.authorId != null) return false;
        if (titleId != null ? !titleId.equals(royaltyPK.titleId) : royaltyPK.titleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authorId != null ? authorId.hashCode() : 0;
        result = 31 * result + (titleId != null ? titleId.hashCode() : 0);
        return result;
    }
}
