package io.bookquest.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BookQuestions {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String question;

    @ElementCollection
    private List<String> alternatives;

    @Column
    private String rightAlternative;

    @ManyToOne
    private Book book;
}
