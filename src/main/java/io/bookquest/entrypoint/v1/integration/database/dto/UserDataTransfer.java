package io.bookquest.entrypoint.v1.integration.database.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDataTransfer(@JsonProperty("Senha__c") String senha,
                               @JsonProperty("Name") String name,
                               @JsonProperty("Classe__r") Map<String, String> classe,
                               @JsonProperty("Coins__c") Integer coins,
                               @JsonProperty("LevelXP__c") Integer levelXp,
                               @JsonProperty("Type") String accountType,
                               @JsonProperty("Classe__c") String classeOutput) {

    public UserDataTransfer(String name, Map<String, String> classe) {
        this(null, name, classe, null, null, null, null);
    }
}
