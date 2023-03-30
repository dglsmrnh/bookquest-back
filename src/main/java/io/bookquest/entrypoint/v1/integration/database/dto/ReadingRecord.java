package io.bookquest.entrypoint.v1.integration.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

public record ReadingRecord(@JsonProperty("ChapterReading__c") Integer chapterReading,
                            @JsonProperty("Read__c") Integer pagesRead,
                            @JsonProperty("ReadingPercentage__c") BigDecimal readingPercentage,
                            @JsonProperty("QuizAnswered__c") boolean isQuizAnswered,
                            @JsonProperty("Account__r") Map<String, String> accountRelation,
                            @JsonProperty("Book__r") Map<String, String> bookRelation,
                            @JsonProperty("Status__c") String status) {
}