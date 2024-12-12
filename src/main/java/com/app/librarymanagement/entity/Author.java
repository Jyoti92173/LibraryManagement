package com.app.librarymanagement.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {
    @Id                                //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id", length = 11)
    private int authorId;

    @Column(name = "name", length = 45)
    private String name;
    private  String phoneNo;

    @OneToMany(mappedBy = "author")
    private Set<Book> books;

    public Author(String name, String phoneNo) {
        this.name = name;
        this.phoneNo = phoneNo;
    }


    public Author() {
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }



    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
