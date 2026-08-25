package com.app.librarymanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
@Table(name = "book")
public class Book{

    @Id                                //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", length = 11)
    private int bookId;

    @Column(name = "book_title", length = 45)
    private String title;

    @Column(name = "cost",length = 1000)
    private int cost;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;


}
