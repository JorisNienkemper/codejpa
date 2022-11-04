package com.infosupport.demo100startwithjpa22.app.domain;

import javax.persistence.Entity;
import java.time.LocalTime;

@Entity
public class LocalTimeEntity extends CommonProperties {

    //Note: field name localTime gives hiccups in Postgres SQL
    private LocalTime time24h;

    public LocalTimeEntity() {
    }

    public LocalTimeEntity(LocalTime time24h) {
        this.time24h = time24h;
    }

    public LocalTimeEntity(Long id, LocalTime time24h) {
        this.id = id;
        this.time24h = time24h;
    }

    public LocalTime getTime24h() {
        return time24h;
    }

    public void setTime24h(LocalTime localTime) {
        this.time24h = localTime;
    }

    @Override
    public String toString() {
        return "LocalTimeEntity{" +
                "id=" + id +
                ", version=" + version +
                ", time24h=" + time24h +
                '}';
    }
}
