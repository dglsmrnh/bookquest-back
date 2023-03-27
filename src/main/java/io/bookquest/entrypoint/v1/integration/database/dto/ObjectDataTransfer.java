package io.bookquest.entrypoint.v1.integration.database.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ObjectDataTransfer<T> {

    private Long totalRecords;

    @JsonProperty("records")
    private List<T> records;

    @JsonIgnore
    public Long getTotalRecords() {
        return totalRecords;
    }

    @JsonProperty("totalRecords")
    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public ObjectDataTransfer(List<T> records) {
        this.records = records;
    }

    public ObjectDataTransfer(Long totalRecords, List<T> records) {
        this.totalRecords = totalRecords;
        this.records = records;
    }

    public ObjectDataTransfer() {
    }
}


