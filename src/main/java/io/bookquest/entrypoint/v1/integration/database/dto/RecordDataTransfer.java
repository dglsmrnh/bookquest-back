package io.bookquest.entrypoint.v1.integration.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record RecordDataTransfer(@JsonProperty("allOrNone") boolean allOrNone,
                                 @JsonProperty("records") List<Record> records) {
}
