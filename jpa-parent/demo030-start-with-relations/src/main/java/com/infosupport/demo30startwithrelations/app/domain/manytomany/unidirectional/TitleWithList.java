package com.infosupport.demo30startwithrelations.app.domain.manytomany.unidirectional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TitleWithList {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "title_author_mm_uni",
            joinColumns = {@JoinColumn(name = "title_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")}
    )
    private List<Author> authors = new ArrayList<>();

    public TitleWithList() {
    }

    public TitleWithList(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public List<Author> getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        return "Title{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
