package io.bookquest.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String bookName;

    @Column
    private Integer xp;

    @Column
    private Integer pages;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> isbn;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> categories;

    @Column
    private boolean isQuizEnable;

    public boolean isQuizEnable() {
        return isQuizEnable;
    }

    public void setQuizEnable(boolean quizEnable) {
        isQuizEnable = quizEnable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<String> getIsbn() {
        return isbn;
    }

    public void setIsbn(List<String> isbn) {
        this.isbn = isbn;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Book(String bookName, Integer xp, Integer pages, List<String> isbn, List<String> categories) {
        this.bookName = bookName;
        this.xp = xp;
        this.pages = pages;
        this.isbn = isbn;
        this.categories = categories;
        this.isQuizEnable = false;
    }

    @Deprecated
    public Book() {
    }
}
