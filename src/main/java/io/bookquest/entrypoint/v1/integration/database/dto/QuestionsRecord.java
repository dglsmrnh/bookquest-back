package io.bookquest.entrypoint.v1.integration.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record QuestionsRecord (@JsonProperty("attributes") TypeAttribute attribute,
                              @JsonProperty("Livro__r") Map<String, String> bookRelationId,
                              @JsonProperty("Inquiry__c") String question) {
}
