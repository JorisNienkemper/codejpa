package com.infosupport.demo10startwithjpa.app.domain;

import javax.persistence.*;

@Entity
public class Book_TABLE {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "book_id_table_generator")
    @TableGenerator(name = "book_id_table_generator", table = "book_id_table")
    private int id;
    private String name;
    private String title;

    public Book_TABLE() {
    }

    public Book_TABLE(String name, String title) {
        setName(name);
        setTitle(title);
    }

    public Book_TABLE(int id, String name, String title) {
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
        return "Book_TABLE{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
