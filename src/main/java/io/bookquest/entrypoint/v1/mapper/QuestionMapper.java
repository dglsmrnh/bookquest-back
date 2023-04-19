package io.bookquest.entrypoint.v1.mapper;

import io.bookquest.entrypoint.v1.dto.BookQuizEntrypoint;
import io.bookquest.entrypoint.v1.integration.database.dto.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class QuestionMapper {

    public static List<Record> toRecord(List<BookQuizEntrypoint> bookQuestions,
                                        BookDataTransfer book) {
        return bookQuestions.stream()
                .map(question -> toQuestionRecord(question, book)).toList();
    }

    public static Record toQuestionRecord(BookQuizEntrypoint bookQuestion,
                                          BookDataTransfer book) {
        return new QuestionsRecord(new TypeAttribute("Questions__c"), Map.of("ISBN__c", book.getIsbn13()), bookQuestion.getQuestion());
    }

    public static List<Record> toListAnswerRecord(List<BookQuizEntrypoint> bookQuestions, List<String> ids) {
        AtomicInteger loopId = new AtomicInteger(0);
        AtomicReference<List<Record>> answerRecord = new AtomicReference<>();
        bookQuestions.forEach(question -> {
            String id = ids.get(loopId.getAndIncrement());
            answerRecord.set(
                    question.getOptions().stream()
                            .map(answer -> toAnswerRecord(answer, id, question.getCorrectAnswer()))
                            .toList()
            );
        });
        return answerRecord.get();
    }

    public static Record toAnswerRecord(String answer, String idQuestion, String correctAnswer) {
        return new AnswerRecord(new TypeAttribute("Answers__c"), idQuestion, answer, correctAnswer.equalsIgnoreCase(answer));
    }

}