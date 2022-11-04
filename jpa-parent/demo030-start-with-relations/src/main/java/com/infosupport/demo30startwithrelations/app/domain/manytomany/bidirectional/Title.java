package com.infosupport.demo30startwithrelations.app.domain.manytomany.bidirectional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Title {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany(fetch=FetchType.LAZY)
    private List<Author> authors = new ArrayList<>();

    public Title() {
    }

    public Title(String name) {
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
        if(author.getTitles()!= null && !author.getTitles().contains(this)){
            author.getTitles().add(this);
        };
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
