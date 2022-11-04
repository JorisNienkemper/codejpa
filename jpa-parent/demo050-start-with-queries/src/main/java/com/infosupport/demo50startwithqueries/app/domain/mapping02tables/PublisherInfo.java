package com.infosupport.demo50startwithqueries.app.domain.mapping02tables;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "pub_info", schema = "public", catalog = "cursistdb")
public class PublisherInfo {

    private Long publisherId;
    private byte[] logo;
    private String info;
    private Publisher publisher;

    @Id
    @Column(name = "pub_id", nullable = false)
    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    @Basic
    @Column(name = "logo", nullable = true)
    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    @Basic
    @Column(name = "pr_info", nullable = true, length = -1)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublisherInfo that = (PublisherInfo) o;

        if (publisherId != null ? !publisherId.equals(that.publisherId) : that.publisherId != null) return false;
        if (!Arrays.equals(logo, that.logo)) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = publisherId != null ? publisherId.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(logo);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "pub_id", referencedColumnName = "pub_id", nullable = false)
    @MapsId
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
