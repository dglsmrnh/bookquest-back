package io.bookquest.entrypoint.v1.mapper;

import io.bookquest.entrypoint.v1.dto.BookQuizEntrypoint;
import io.bookquest.entrypoint.v1.integration.database.dto.*;
import io.bookquest.persistence.entity.Book;
import io.bookquest.persistence.entity.BookQuestions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class QuestionMapper {

    public static List<BookQuestions> toEntityList(List<BookQuizEntrypoint> dtos, Book book) {
        return dtos.stream().map(dto -> toEntity(dto, book)).toList();
    }

    public static BookQuestions toEntity(BookQuizEntrypoint dto, Book book) {
        return new BookQuestions(dto.getQuestion(), dto.getOptions(), dto.getCorrectAnswer(), book);
    }

    public static List<BookQuizEntrypoint> toDtoList(List<BookQuestions> dtos) {
        return dtos.stream().map(QuestionMapper::toDto).toList();
    }

    public static BookQuizEntrypoint toDto(BookQuestions dto) {
        return new BookQuizEntrypoint(dto.getQuestion(), dto.getOptions(), dto.getCorrectAnswer());
    }

    public static List<Record> toRecord(List<BookQuizEntrypoint> bookQuestions,
                                        BookDataTransfer book) {
        return bookQuestions.stream()
                .map(question -> toQuestionRecord(question, book)).toList();
    }

    public static Record toQuestionRecord(BookQuizEntrypoint bookQuestion,
                                          BookDataTransfer book) {
        if (!bookQuestion.getOptions().contains(bookQuestion.getCorrectAnswer()))
            throw new RuntimeException();
        return new QuestionsRecord(new TypeAttribute("Questions__c"), Map.of("ISBN__c", book.getIsbn13()), bookQuestion.getQuestion());
    }

    public static List<List<Record>> toListAnswerRecord(List<BookQuizEntrypoint> bookQuestions, List<String> ids) {
        AtomicInteger loopId = new AtomicInteger(0);
        List<List<Record>> answerList = new ArrayList<>();
        bookQuestions.forEach(question -> {
            String id = ids.get(loopId.getAndIncrement());
            answerList.add(
                    question.getOptions().stream()
                            .map(answer -> toAnswerRecord(answer, id, question.getCorrectAnswer()))
                            .toList()
            );
        });
        return answerList;
    }

    public static Record toAnswerRecord(String answer, String idQuestion, String correctAnswer) {
        return new AnswerRecord(new TypeAttribute("Answers__c"), idQuestion, answer, correctAnswer.equalsIgnoreCase(answer));
    }

}