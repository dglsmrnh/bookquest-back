package io.bookquest.entrypoint.v1.integration.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ObjectDataTransfer<T> {

    @JsonProperty("records")
    private List<T> records;

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public ObjectDataTransfer(List<T> records) {
        this.records = records;
    }

    public ObjectDataTransfer() {
    }
}


