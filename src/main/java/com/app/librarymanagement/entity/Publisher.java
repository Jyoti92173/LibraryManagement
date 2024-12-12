package com.app.librarymanagement.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "publisher")
public class Publisher {
    @Id                                //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id", length = 11)
    private int publisherId;

    @Column(name = "name", length = 45)
    private String name;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books;


    public Publisher() {

    }
    public Publisher(String name) {
        this.name = name;
    }

    public Publisher(int publisherId, String name, Set<Book> books) {
        this.publisherId = publisherId;
        this.name = name;
        this.books = books;
    }

    public Publisher(String name, Set<Book> books) {
        this.name = name;
        this.books = books;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "publisherId=" + publisherId +
                ", name='" + name + '\'' +
                '}';
    }
}
