package io.bookquest.entrypoint.v1;

import io.bookquest.entrypoint.v1.dto.BookEntrypoint;
import io.bookquest.entrypoint.v1.dto.ReadingEntrypoint;
import io.bookquest.usecase.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public BookEntrypoint getBook(@RequestParam(value = "isbn", required = false) String isbn,
                                  @RequestParam(value = "title", required = false) String bookTitle) {

        return bookService.processBook(isbn, bookTitle);
    }

    @GetMapping("/users/{idUser}/books")
    public ResponseEntity<Object> getAllBookFromUser(@PathVariable("idUser") String idUser,
                                   @RequestParam(name = "page_size", required = false, defaultValue = "200") String pageSize,
                                   @RequestParam(name = "page", required = false, defaultValue = "200") String page) {

        return ResponseEntity.ok(Map.of("books", List.of(Map.of("book_name", "O hobbit", "categories", List.of("ficção"), "pages_read", 190, "total_pages", 220, "xp", 235))));
    }

    @PatchMapping("/readings/{externalId}")
    public ResponseEntity<Void> saveBookToUser(@PathVariable("externalId") String externalId,
                                                 @RequestBody ReadingEntrypoint reading) {
        String[] idComposable = externalId.split("-");
        bookService.updateReading(idComposable[0], idComposable[1], reading);
        return ResponseEntity.noContent().build();
    }
}
