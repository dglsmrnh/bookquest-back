package io.bookquest.entrypoint.v1;

import io.bookquest.entrypoint.v1.dto.BookEntrypoint;
import io.bookquest.usecase.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public BookEntrypoint getBook(@RequestParam(value = "isbn", required = false) String isbn,
                                  @RequestParam(value = "title", required = false) String  bookTitle){

        return bookService.processBook(isbn, bookTitle);
    }

    @GetMapping("/users/{username}/books")
    public void getAllBookFromUser()
}
