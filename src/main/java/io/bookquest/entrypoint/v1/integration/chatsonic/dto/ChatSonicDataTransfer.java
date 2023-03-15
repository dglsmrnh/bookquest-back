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
        this.inputText = """
                me faça 5 perguntas no formato de alternativa com as respostas certas do livro %s no formato json com as opções de respostas em um array, retornar apenas o json, exemplo do formato esperado:
                [{"question": "Quem escreveu O Hobbit?","options": ["J.R.R. Tolkien", "J.K.Rowling", "George R.R. Martin", "William Shakespeare"],"correctAnswer":"J.R.R. Tolkien"},{"question": "Qual a sua idade?","options": ["10", "15", "18", "20"],"correctAnswer":"20"}]
                  """.formatted(inputText);
    }
}
