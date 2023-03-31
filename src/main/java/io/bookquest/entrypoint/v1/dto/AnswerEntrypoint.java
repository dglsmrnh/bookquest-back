package io.bookquest.entrypoint.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AnswerEntrypoint {

    @JsonProperty("answers")
    List<String> answers;

    public AnswerEntrypoint(List<String> answers) {
        this.answers = answers;
    }

    public AnswerEntrypoint() {
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
