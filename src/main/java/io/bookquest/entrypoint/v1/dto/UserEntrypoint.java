package io.bookquest.entrypoint.v1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserEntrypoint(
        @NotNull

        @JsonProperty("username") String username,
        @NotNull
        @JsonProperty("password") String senha,
        @NotNull

        @JsonProperty("name") String name,
        @JsonProperty("class") String classe,
        @JsonProperty("coins") Integer coins,
        @JsonProperty("level_xp") Integer levelXp,
        @JsonProperty("account_type") String accountType,
        @JsonProperty("email") String email) {
}
