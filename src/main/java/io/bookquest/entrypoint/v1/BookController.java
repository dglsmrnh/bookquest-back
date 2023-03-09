package io.bookquest.entrypoint.v1;

import io.bookquest.entrypoint.v1.dto.BookEntrypoint;
import io.bookquest.entrypoint.v1.dto.ReadingEntrypoint;
import io.bookquest.usecase.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/users/{username}/books")
    public void getAllBookFromUser() {

    }

    @PatchMapping("/users/{username}/books/{isbn}")
    public void saveBookToUser(@PathVariable("username") String username,
                               @PathVariable("isbn") String isbn,
                               @RequestBody ReadingEntrypoint reading) {
        bookService.saveBookToUserInventory(username, isbn, reading);
    }
}
