package io.bookquest.entrypoint.v1.integration.chatsonic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatSonicDataTransfer {

    @JsonProperty("enable_google_results")
    private final boolean enableGoogleResults = false;

    @JsonProperty("enable_memory")
    private final boolean enableMemory = false;

    @JsonProperty("input_text")
    private String inputText;

    public boolean isEnableGoogleResults() {
        return enableGoogleResults;
    }

    public boolean isEnableMemory() {
        return enableMemory;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public ChatSonicDataTransfer(String inputText) {
        this.inputText = "me faça 5 perguntas no formato de alternativa com as respostas certas do livro" + inputText +
                " no formato json com as opções de respostas em um array, exemplo: [{\n" +
                "    \"question\": \"Quem escreveu O Hobbit?\",\n" +
                "    \"options\": [\"J.R.R. Tolkien\", \"J.K. Rowling\", \"George R.R. Martin\", \"William Shakespeare\"],\n" +
                "    \"correctAnswer\": \"J.R.R. Tolkien\"\n" +
                "  }]";
    }
}
