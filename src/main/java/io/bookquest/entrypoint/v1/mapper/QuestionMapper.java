package io.bookquest.entrypoint.v1.mapper;

import io.bookquest.entrypoint.v1.dto.BookQuizDataTransfer;
import io.bookquest.persistence.entity.Book;
import io.bookquest.persistence.entity.BookQuestions;

import java.util.List;

public class QuestionMapper {

    public static List<BookQuestions> toEntityList(List<BookQuizDataTransfer> dtos, Book book) {
        return dtos.stream().map(dto -> toEntity(dto, book)).toList();
    }

    public static BookQuestions toEntity(BookQuizDataTransfer dto, Book book) {
        return new BookQuestions(dto.getQuestion(), dto.getOptions(), dto.getCorrectAnswer(), book);
    }

    public static List<BookQuizDataTransfer> toDtoList(List<BookQuestions> dtos) {
        return dtos.stream().map(QuestionMapper::toDto).toList();
    }

    public static BookQuizDataTransfer toDto(BookQuestions dto) {
        return new BookQuizDataTransfer(dto.getQuestion(), dto.getOptions(), dto.getCorrectAnswer());
    }
}
