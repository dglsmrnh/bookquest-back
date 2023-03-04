package io.bookquest.entrypoint.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserEntrypoint(@JsonProperty("username") String username,
                             @JsonProperty("password") String senha,
                             @JsonProperty("name") String name,
                             @JsonProperty("class") String classe,
                             @JsonProperty("coins") Integer coins,
                             @JsonProperty("level_xp") Integer levelXp,
                             @JsonProperty("account_type") String accountType) {
}
