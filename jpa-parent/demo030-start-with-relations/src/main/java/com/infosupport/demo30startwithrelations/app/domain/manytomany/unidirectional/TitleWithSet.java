package com.infosupport.demo30startwithrelations.app.domain.manytomany.unidirectional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TitleWithSet {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "title_author_mm_uni_set",
            joinColumns = {@JoinColumn(name = "title_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")}
    )
    private Set<Author> authors = new HashSet<>();

    public TitleWithSet() {
    }

    public TitleWithSet(String name) {
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

    public Set<Author> getAuthors() {
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
