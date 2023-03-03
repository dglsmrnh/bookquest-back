package io.bookquest.entrypoint.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record UserEntrypoint(@JsonProperty("username") String username,
                             @JsonProperty("password") String senha,
                             @JsonProperty("name") String name,
                             @JsonProperty("class") Map<String, String> classe,
                             @JsonProperty("coins") Integer coins,
                             @JsonProperty("level_xp") Integer levelXp,
                             @JsonProperty("account_type") String accountType) {
}
