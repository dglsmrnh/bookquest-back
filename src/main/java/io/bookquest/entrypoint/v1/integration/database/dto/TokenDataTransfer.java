package io.bookquest.entrypoint.v1.integration.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenDataTransfer(@JsonProperty("access_token") String accessToken, String id) {
}
