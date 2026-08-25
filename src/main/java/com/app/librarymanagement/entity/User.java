package com.app.librarymanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
@Table(name = "user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", length = 11)
    private int id;

    @Column(name = "user_name", length = 45)
    private String name;

    @Column(name = "user_email", length = 30)
    private String email;

}
