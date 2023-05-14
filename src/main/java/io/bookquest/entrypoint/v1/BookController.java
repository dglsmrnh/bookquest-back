package io.bookquest.entrypoint.v1;

import io.bookquest.entrypoint.v1.dto.BookEntrypoint;
import io.bookquest.entrypoint.v1.dto.ReadingEntrypoint;
import io.bookquest.usecase.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<List<ReadingEntrypoint>> getAllBookFromUser(@PathVariable("idUser") String idUser,
                                   @RequestParam(name = "page_size", required = false, defaultValue = "200") String pageSize,
                                   @RequestParam(name = "page", required = false, defaultValue = "200") String page) {

        return ResponseEntity.ok(bookService.getBooksFromUser(idUser));
    }

    @PatchMapping("/readings/{externalId}")
    public ResponseEntity<Void> saveBookToUser(@PathVariable("externalId") String externalId,
                                                 @RequestBody ReadingEntrypoint reading) {
        String[] idComposable = externalId.split("-");
        bookService.updateReading(idComposable[0], idComposable[1], reading);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/books/{idBook}/forums")
    public Object getForum() {
        return Map.of("discussion", "o que vocês sentiram falta nesse livro?", "comments",
                List.of(Map.of("name","Killua", "comment", "senti falta de mais presença dos anões nas batalhas","upvote", 3, "downvote", 0)));
    }

    @PostMapping("/books/{idBook}/forums")
    public Object createForum() {
        return ResponseEntity.created(URI.create("/")).build();
    }

    @PutMapping("/books/{idBook}/forums/{idForum}")
    public Object updateForum() {
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/books/{idBook}/forums/{idForum}/comments")
    public Object createComment() {
        return ResponseEntity.created(URI.create("/")).build();
    }

    @PutMapping("/books/{idBook}/forums/{idForum}/comments/{idComment}")
    public Object updateComment() {
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/books/{idBook}/forums/{idForum}/comments/{idComment}")
    public Object upvoteDownVoteComment() {
        return ResponseEntity.noContent().build();
    }
}
