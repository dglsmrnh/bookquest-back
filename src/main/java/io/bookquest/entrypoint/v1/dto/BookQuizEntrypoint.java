package io.bookquest.entrypoint.v1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonInclude(Include.NON_NULL)
public class BookQuizEntrypoint {

    @JsonProperty("question")
    private String question;

    @JsonProperty("options")
    private List<String> options;

    @JsonProperty("correctAnswer")
    private String correctAnswer;

    @JsonProperty("right_choose")
    private Boolean rightOption;

    public Boolean isRightOption() {
        return rightOption;
    }

    public void setRightOption(boolean rightOption) {
        this.rightOption = rightOption;
    }

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
