package io.bookquest.persistence.entity;

import jakarta.persistence.*;

@Entity
public class UserBook {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private UserTable user;

    @ManyToOne
    private Book book;

    @Column
    private String status;
}
