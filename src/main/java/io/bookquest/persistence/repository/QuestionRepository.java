package io.bookquest.persistence.repository;

import io.bookquest.persistence.entity.BookQuestions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<BookQuestions, Long> {

    List<BookQuestions> findByBookId(Long idBook);
}
