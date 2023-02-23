package io.bookquest.persistence.repository;

import io.bookquest.persistence.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByBookNameIgnoreCase(String title);

    List<Book> findByIsbn(String isbn);
}
