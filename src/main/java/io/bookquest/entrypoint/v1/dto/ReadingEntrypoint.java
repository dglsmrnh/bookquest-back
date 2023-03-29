package io.bookquest.entrypoint.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ReadingEntrypoint(@JsonProperty("reading_chapter") Integer chapterReading,
                                @JsonProperty("reading_percentage") BigDecimal readingPercentage,
                                @JsonProperty("pages_read") Integer pagesRead,
                                @JsonProperty("quiz_answered") boolean isQuizANswered) {
}
