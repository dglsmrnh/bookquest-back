package io.bookquest.entrypoint.v1.integration.database.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDataTransfer {

    @JsonProperty("Senha__c") String senha;

    @JsonProperty("Name") String name;

    @JsonProperty("Classe__r") Map<String, String> classe;

    @JsonProperty("Coins__c") Integer coins;

    @JsonProperty("LevelXP__c") Integer levelXp;

    @JsonProperty("Type") String accountType;

    @JsonProperty("Classe__c") String classeOutput;

    @JsonProperty("Email__c") String email;

    public UserDataTransfer(String name, Map<String, String> classe) {
        setName(name);
        setClasse(classe);
    }

    public UserDataTransfer(Integer levelXp) {
        this.levelXp = levelXp;
    }

    public UserDataTransfer() {
    }

    public String getSenha() {
        return senha;
    }

    public UserDataTransfer setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDataTransfer setName(String name) {
        this.name = name;
        return this;
    }

    public Map<String, String> getClasse() {
        return classe;
    }

    public UserDataTransfer setClasse(Map<String, String> classe) {
        this.classe = classe;
        return this;
    }

    public Integer getCoins() {
        return coins;
    }

    public UserDataTransfer setCoins(Integer coins) {
        this.coins = coins;
        return this;
    }

    public Integer getLevelXp() {
        return levelXp;
    }

    public UserDataTransfer setLevelXp(Integer levelXp) {
        this.levelXp = levelXp;
        return this;
    }

    public String getAccountType() {
        return accountType;
    }

    public UserDataTransfer setAccountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    public String getClasseOutput() {
        return classeOutput;
    }

    public UserDataTransfer setClasseOutput(String classeOutput) {
        this.classeOutput = classeOutput;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDataTransfer setEmail(String email) {
        this.email = email;
        return this;
    }
}
