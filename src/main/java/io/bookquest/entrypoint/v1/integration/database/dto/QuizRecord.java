package io.bookquest.entrypoint.v1.integration.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record QuizRecord(@JsonProperty("Book__r") Map<String, String> book) {
}
