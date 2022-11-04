package com.infosupport.demo100startwithjpa22.app.domain;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class LocalDateEntity extends CommonProperties {

    private LocalDate localDate;

    public LocalDateEntity() {
    }

    public LocalDateEntity(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDateEntity(Long id, LocalDate localDate) {
        this.id = id;
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return "LocalDateEntity{" +
                "id=" + id +
                ", version=" + version +
                ", localDate=" + localDate +
                '}';
    }
}
