package io.bookquest.entrypoint.v1.integration.openlibrary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookOpenLibrary {

    private String title;
    private List<KeyValue> works;

    private List<String> subject;

    @JsonProperty("number_of_pages")
    private Integer pages;

    @JsonProperty("number_of_pages_median")
    private Integer pagesMedian;
    @JsonProperty("isbn_10")
    private List<String> isbn10;

    private List<String> isbn;

    private boolean isSearch;

    public Integer getPagesMedian() {
        return pagesMedian;
    }

    public void setPagesMedian(Integer pagesMedian) {
        this.pagesMedian = pagesMedian;
    }

    public List<String> getIsbn() {
        return isbn;
    }

    public void setIsbn(List<String> isbn) {
        this.isbn = isbn;
    }

    public boolean isSearch() {
        return isSearch;
    }

    public void setSearch(boolean search) {
        isSearch = search;
    }

    private List<String> categories;

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<String> getSubject() {
        return subject;
    }

    public void setSubject(List<String> subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<KeyValue> getWorks() {
        return works;
    }

    public void setWorks(List<KeyValue> works) {
        this.works = works;
    }

    public List<String> getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(List<String> isbn10) {
        this.isbn10 = isbn10;
    }

    public BookOpenLibrary(String title, List<KeyValue> works, List<String> subject, Integer pages,
                           Integer pagesMedian, List<String> isbn10, List<String> isbn,
                           boolean isSearch, List<String> categories) {
        this.title = title;
        this.works = works;
        this.subject = subject;
        this.pages = pages;
        this.pagesMedian = pagesMedian;
        this.isbn10 = isbn10;
        this.isbn = isbn;
        this.isSearch = isSearch;
        this.categories = categories;
    }

    public BookOpenLibrary() {
    }
}
