package io.bookquest.entrypoint.v1.integration.database.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record QuestionsGetRecord(@JsonProperty("Livro__c") String bookRelationId,
                                 @JsonProperty("Inquiry__c") String question,
                                 @JsonProperty("Id") String id) {
}
