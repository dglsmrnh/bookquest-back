package io.bookquest.entrypoint.v1.integration.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TypeAttribute(@JsonProperty("type") String type) {
}
