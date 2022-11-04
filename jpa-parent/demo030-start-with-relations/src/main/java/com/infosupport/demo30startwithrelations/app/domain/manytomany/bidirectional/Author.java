package com.infosupport.demo30startwithrelations.app.domain.manytomany.bidirectional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "authors")
    private List<Title> titles;

    public Author() {
    }

    public Author(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Title> getTitles() {
        return titles;
    }

    public void addTitle(Title title) {
        this.titles.add(title);
        if (title.getAuthors() != null && !title.getAuthors().contains(this)) {
            title.getAuthors().add(this);
        }
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
