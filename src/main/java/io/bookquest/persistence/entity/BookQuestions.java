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

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> options;

    @Column
    private String correctAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    public BookQuestions( String question, List<String> options, String correctAnswer, Book book) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.book = book;
    }

    public BookQuestions() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
