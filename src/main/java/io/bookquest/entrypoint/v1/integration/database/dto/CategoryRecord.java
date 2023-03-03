package io.bookquest.entrypoint.v1.integration.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CategoryRecord(@JsonProperty("attributes") TypeAttribute attribute,
                             @JsonProperty("Name") String name,
                             @JsonProperty("ExternalId__c") String externalId) {
}
