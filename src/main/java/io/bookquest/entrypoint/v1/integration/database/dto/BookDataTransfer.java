package io.bookquest.entrypoint.v1.integration.database.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BookDataTransfer {

    @JsonProperty("Name")
    String name;

    @JsonProperty("CompleteTitle__c")
    String completeTitle;

    String isbn13;

    @JsonProperty("ISBN10__c")
    String isbn10;

    @JsonProperty("Pages__c")
    Integer pages;

    @JsonProperty("XP__c")
    Integer xp;

    @JsonProperty("QuizEnable__c")
    boolean quizEnable;

    List<String> categories;

    @JsonIgnore
    public List<String> getCategories() {
        return categories;
    }

    @JsonProperty("BookCategories__r")
    public void setCategories(Map<String, Object> categories) {
        this.categories = new ArrayList<>();
        var records = categories.getOrDefault("records", List.of());
        if (records instanceof List<?>) {
            ((List<?>) records).forEach(record -> {
                if (record instanceof Map<?,?>) {
                    List<String> listaCategoria = ((Map<?, ?>) record).values().stream()
                            .filter(categoryRelation -> categoryRelation instanceof Map<?, ?>)
                            .map(categoryRelation -> {
                                Object name = ((Map<?, ?>) categoryRelation).get("Name");
                                return String.valueOf(name);
                            }).filter(categoriesString -> !categoriesString.equalsIgnoreCase("null"))
                            .toList();
                    this.categories.addAll(listaCategoria);
                }
            });
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompleteTitle() {
        return completeTitle;
    }

    public void setCompleteTitle(String completeTitle) {
        this.completeTitle = completeTitle;
    }

    @JsonIgnore
    public String getIsbn13() {
        return isbn13;
    }

    @JsonProperty("ISBN__c")
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public boolean isQuizEnable() {
        return quizEnable;
    }

    public void setQuizEnable(boolean quizEnable) {
        this.quizEnable = quizEnable;
    }

    public BookDataTransfer() {
    }

    public BookDataTransfer(String name, String completeTitle, String isbn13, String isbn10,
                            Integer pages, Integer xp, boolean quizEnable) {
        this.name = name;
        this.completeTitle = completeTitle;
        this.isbn13 = isbn13;
        this.isbn10 = isbn10;
        this.pages = pages;
        this.xp = xp;
        this.quizEnable = quizEnable;
    }
}




