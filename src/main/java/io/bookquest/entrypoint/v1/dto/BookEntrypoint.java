package io.bookquest.entrypoint.v1.dto;

import java.util.List;

public record BookEntrypoint(Long id, String bookName, Integer xp, Integer pages, String isbn13,
                             String isbn10, List<String> categories) {
}
