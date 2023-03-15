package io.bookquest.entrypoint.v1.integration.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AnswerRecord(@JsonProperty("attributes") TypeAttribute attribute,
                           @JsonProperty("Question__c") String question,
                           @JsonProperty("Description__c") String description,
                           @JsonProperty("IsCorrectAnswer__c") boolean isCorrect) {
}
