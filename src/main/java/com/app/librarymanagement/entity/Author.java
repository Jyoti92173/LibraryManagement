package com.app.librarymanagement.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Set;

@Entity
@Data
@Table(name = "author")
public class Author{

    @Id                            //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", length = 11)
    private int authorId;

    @Column(name = "name", length = 45)
    private String name;
    private  String phoneNo;

    @OneToMany(mappedBy = "author")
    private Set<Book> books;

   

}
