package io.bookquest.entrypoint.v1.integration.database;

import io.bookquest.entrypoint.v1.integration.database.dto.BookDataTransfer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ApexClient",
        url = "https://bookquest-dev-ed.develop.my.salesforce.com/services/apexrest")
public interface ApexClient {

    @GetMapping("/books")
    List<BookDataTransfer> getBook(@RequestParam("book_name") String title,
                                   @RequestParam("isbn_10") String isbn10,
                                   @RequestParam("isbn_13") String isbn13,
                                   @RequestHeader("Authorization") String token);
}
