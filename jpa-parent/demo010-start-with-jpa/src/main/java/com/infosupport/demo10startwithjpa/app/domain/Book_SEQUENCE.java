package com.infosupport.demo10startwithjpa.app.domain;

import javax.persistence.*;

@Entity
public class Book_SEQUENCE {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_sequence_generator")
    @SequenceGenerator(name = "book_id_sequence_generator", sequenceName = "book_id_sequence")
    private int id;
    private String name;
    private String title;

    public Book_SEQUENCE() {
    }

    public Book_SEQUENCE(String name, String title) {
        setName(name);
        setTitle(title);
    }

    public Book_SEQUENCE(int id, String name, String title) {
        setId(id);
        setName(name);
        setTitle(title);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book_SEQUENCE{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
