package io.bookquest.entrypoint.v1.mapper;

import io.bookquest.entrypoint.v1.dto.BookEntrypoint;
import io.bookquest.entrypoint.v1.integration.database.dto.BookCategoryRecord;
import io.bookquest.entrypoint.v1.integration.database.dto.BookDataTransfer;
import io.bookquest.entrypoint.v1.integration.database.dto.TypeAttribute;
import io.bookquest.entrypoint.v1.integration.openlibrary.dto.BookOpenLibrary;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Math.round;
import static java.lang.Math.toIntExact;

public class BookMapper {

    public static Record toBookCategory(BookDataTransfer book, String category) {
        category = CategoryMapper.normalizePortuguese(category);
        return new BookCategoryRecord(new TypeAttribute("BookCategory__c"), Map.of("ISBN__c", book.isbn13()),
                Map.of("ExternalId__c", category), book.isbn13().concat(category));
    }

    public static BookDataTransfer toEntity(BookOpenLibrary dto) {
        Integer xp;
        if (dto.isSearch()) {
            xp = calculateXp(dto.getPagesMedian());

            AtomicReference<String> isbn13 = new AtomicReference<>();
            AtomicReference<String> isbn10 = new AtomicReference<>();
            dto.getIsbn().forEach(isbn -> {
                if (isbn.length() == 13)
                    isbn13.set(isbn);
                else
                    isbn10.set(isbn);
            });
            return new BookDataTransfer(dto.getTitle(), "", isbn13.get(), isbn10.get(), dto.getPagesMedian(), xp, false);
        }

        String isbn13 = dto.getIsbn13().stream().findFirst().orElse(null);
        String isbn10 = dto.getIsbn10().stream().findFirst().orElse(null);
        xp = calculateXp(dto.getPages());
        return new BookDataTransfer(dto.getTitle(), "", isbn13, isbn10, dto.getPages(), xp, false);
    }

    public static BookEntrypoint toDto(BookDataTransfer book, List<String> categories) {
        return new BookEntrypoint(null, book.name(), book.xp(),
                book.pages(), book.isbn13(), book.isbn10(), categories);
    }

    private static Integer calculateXp(Integer pages) {
        return toIntExact(round(pages * 0.5));
    }
}
