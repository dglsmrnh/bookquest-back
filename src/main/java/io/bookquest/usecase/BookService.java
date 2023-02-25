package io.bookquest.usecase;

import io.bookquest.entrypoint.v1.dto.BookDataTransfer;
import io.bookquest.entrypoint.v1.integration.openlibrary.OpenLibraryClient;
import io.bookquest.entrypoint.v1.integration.openlibrary.dto.BookOpenLibrary;
import io.bookquest.entrypoint.v1.integration.openlibrary.dto.EnvelopeData;
import io.bookquest.entrypoint.v1.mapper.BookMapper;
import io.bookquest.persistence.entity.Book;
import io.bookquest.persistence.repository.BookRepository;
import io.bookquest.usecase.categories.CategoriesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static java.lang.Math.round;
import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


@Service
public class BookService {

    @Autowired
    private OpenLibraryClient openLibraryClient;

    @Autowired
    private BookRepository repository;

    @Cacheable("getBook")
    public BookDataTransfer getBook(String isbn, String title) {
        return BookMapper.toDto(processBook(isbn, title));
    }
    public Book processBook(String isbn, String title) {

        if (nonNull(title)) {
            var book = repository.findByBookNameIgnoreCase(title).stream().findFirst();
            return book.orElse(searchWithTitleAndCreate(title, book.isPresent()));
        } else if (nonNull(isbn)) {
            var book = repository.findByIsbn(isbn).stream().findFirst();
            return book.orElse(createBook(isbn, book.isPresent()));
        }

        throw new ResponseStatusException(BAD_REQUEST, "necessário ter pelo menos um parâmetro na requisição: [title, isbn]");
    }

    private Book searchWithTitleAndCreate(String title, boolean isPresent) {
        if (isPresent)
            return null;

        EnvelopeData envelopeData = openLibraryClient.searchBookByParam(title);
        BookOpenLibrary book = envelopeData.getDocs().stream()
                .filter(doc -> doc.getTitle().equalsIgnoreCase(title) && doc.getPagesMedian() != null)
                .findFirst().orElseThrow();
        book.setSearch(true);
        return populateDataAndSave(book, envelopeData);
    }

    private Book createBook(String isbn, boolean isPresent) {
        if (isPresent)
            return null;

        BookOpenLibrary book = openLibraryClient.getBookByISBN(isbn);
        String titleName = book.getTitle();
        EnvelopeData envelopeData = openLibraryClient.searchBookByParam(titleName);
        book.setSearch(false);
        return populateDataAndSave(book, envelopeData);
    }

    private Book populateDataAndSave(BookOpenLibrary book, EnvelopeData envelopeData) {
        var categories = new ArrayList<String>();
        envelopeData.getDocs().stream()
                .map(doc -> getCategories(doc.getSubject()))
                .forEach(categories::addAll);
        book.setCategories(categories);
        return saveBook(book);
    }

    public Book saveBook(BookOpenLibrary book) {
        return repository.save(BookMapper.toEntity(book));
    }

    private boolean containsSubject(List<String> subjects) {
        return subjects != null && !subjects.isEmpty();
    }

    private List<String> getCategories(List<String> subjects) {
        return Arrays.stream(CategoriesEnum.values())
                .map(CategoriesEnum::getCategory)
                .filter(category -> containsSubject(subjects) && subjects.contains(category))
                .toList();
    }
}


