package com.infosupport.demo30startwithrelations.app.domain.onetoone;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "publisherinfo")
public class PublisherInfoOneToOne {
    @Id
    private long id;
    private byte[] logo;
    private String info;

    @OneToOne()
    @JoinColumn(name = "id")
    @MapsId
    private PublisherOneToOne publisher;

    public PublisherInfoOneToOne() {
    }

    public PublisherInfoOneToOne(String info) {
        setInfo(info);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

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

        PublisherInfoOneToOne that = (PublisherInfoOneToOne) o;

        if (id != that.id) return false;
        if (!Arrays.equals(logo, that.logo)) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + Arrays.hashCode(logo);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    public PublisherOneToOne getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherOneToOne publisher) {
        this.publisher = publisher;
    }
}
