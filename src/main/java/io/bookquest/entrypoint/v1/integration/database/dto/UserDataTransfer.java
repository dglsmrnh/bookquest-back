package io.bookquest.entrypoint.v1.integration.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record UserDataTransfer(@JsonProperty("Senha__c") String senha,
                               @JsonProperty("Name") String name,
                               @JsonProperty("Classe_r") Map<String, String> classe,
                               @JsonProperty("Coins_c") Integer coins,
                               @JsonProperty("LevelXP__c") Integer levelXp,
                               @JsonProperty("Type") String accountType) {
}
