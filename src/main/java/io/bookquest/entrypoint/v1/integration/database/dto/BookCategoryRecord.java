package io.bookquest.entrypoint.v1.integration.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record BookCategoryRecord(@JsonProperty("attributes") TypeAttribute attribute,
                                 @JsonProperty("Book__r") Map<String, String> bookRelationId,
                                 @JsonProperty("Category__r") Map<String, String> categoryRelationId,
                                 @JsonProperty("ExternalId13__c") String externalId13) {
}
