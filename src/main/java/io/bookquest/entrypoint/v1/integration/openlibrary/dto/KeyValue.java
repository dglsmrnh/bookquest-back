package io.bookquest.entrypoint.v1.integration.openlibrary.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KeyValue {

    private String key;

    public String getKey() {
        var array = key.split("/");
        return array[array.length - 1];
    }

    public void setKey(String key) {
        this.key = key;
    }
}
