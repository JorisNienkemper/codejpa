package org.infosupport.app.domain;

import javax.persistence.*;

@Entity
@Cacheable
public class Title {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;
    private String name;

    public Title() {
    }

    public Title(String name) {
        this.name = name;
    }

    public Title(Long id, String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Title{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                '}';
    }
}
