package io.bookquest.entrypoint.v1.integration.database.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public record BookDataTransfer(@JsonProperty("Name") String name,
                               @JsonProperty("CompleteTitle__c") String completeTitle,
                               @JsonIgnore String isbn13,
                               @JsonProperty("ISBN10__c") String isbn10,
                               @JsonProperty("Pages__c") Integer pages, @JsonProperty("XP__c") Integer xp,
                               @JsonProperty("QuizEnable__c") boolean quizEnable) {
}
