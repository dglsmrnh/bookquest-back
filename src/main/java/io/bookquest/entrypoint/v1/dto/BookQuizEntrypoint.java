package io.bookquest.entrypoint.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BookQuizEntrypoint {

    @JsonProperty("question")
    private String question;

    @JsonProperty("options")
    private List<String> options;

    @JsonProperty("correctAnswer")
    private String correctAnswer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public BookQuizEntrypoint() {
    }

    public BookQuizEntrypoint(String question, List<String> options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}
