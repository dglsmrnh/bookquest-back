package io.bookquest.entrypoint.v1.mapper;

import io.bookquest.entrypoint.v1.dto.BookDataTransfer;
import io.bookquest.entrypoint.v1.integration.openlibrary.dto.BookOpenLibrary;
import io.bookquest.persistence.entity.Book;

import java.util.List;

import static java.lang.Math.round;
import static java.lang.Math.toIntExact;

public class BookMapper {

    public static Book toEntity(BookOpenLibrary dto) {
        Integer xp;
        if (dto.isSearch()) {
            xp = calculateXp(dto.getPagesMedian());
            return new Book(dto.getTitle(), xp, dto.getPagesMedian(), dto.getIsbn(), dto.getCategories());
        }

        xp = calculateXp(dto.getPages());
        return new Book(dto.getTitle(), xp, dto.getPages(), dto.getIsbn10(), dto.getCategories());
    }

    public static BookDataTransfer toDto(Book book) {
        return new BookDataTransfer(book.getId(), book.getBookName(), book.getXp(),
                book.getPages(), book.getIsbn(), book.getCategories());
    }

    private static Integer calculateXp(Integer pages) {
        return toIntExact(round(pages * 0.5));
    }
}
