package io.bookquest.entrypoint.v1.mapper;

import io.bookquest.entrypoint.v1.dto.BookQuizEntrypoint;
import io.bookquest.entrypoint.v1.integration.database.dto.BookDataTransfer;
import io.bookquest.entrypoint.v1.integration.database.dto.ObjectDataTransfer;
import io.bookquest.entrypoint.v1.integration.database.dto.QuestionsRecord;
import io.bookquest.entrypoint.v1.integration.database.dto.TypeAttribute;
import io.bookquest.persistence.entity.Book;
import io.bookquest.persistence.entity.BookQuestions;

import java.util.List;
import java.util.Map;

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

    public static ObjectDataTransfer<Record> toRecord(List<BookQuizEntrypoint> bookQuestions,
                                                      BookDataTransfer book) {
        List<Record> questionsRecords = bookQuestions.stream()
                .map(question -> toQuestionRecord(question, book)).toList();
        return new ObjectDataTransfer<Record>(questionsRecords);
    }

    public static Record toQuestionRecord(BookQuizEntrypoint bookQuestion,
                                                   BookDataTransfer book) {
        return new QuestionsRecord(new TypeAttribute("Questions__c"), Map.of("ISBN__c", book.getIsbn13()), bookQuestion.getQuestion());
    }
}