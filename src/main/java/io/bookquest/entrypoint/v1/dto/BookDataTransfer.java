package io.bookquest.entrypoint.v1.dto;

import io.bookquest.entrypoint.v1.integration.openlibrary.dto.BookOpenLibrary;
import io.bookquest.entrypoint.v1.integration.openlibrary.dto.KeyValue;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

public record BookDataTransfer(Long id, String bookName, Integer xp, Integer pages, List<String> isbn,
                               List<String> categories) {
}
