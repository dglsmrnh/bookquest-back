package io.bookquest.entrypoint.v1.integration.openlibrary;

import io.bookquest.entrypoint.v1.integration.openlibrary.dto.BookOpenLibrary;
import io.bookquest.entrypoint.v1.integration.openlibrary.dto.EnvelopeData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OpenLibraryClient", url = "https://openlibrary.org")
public interface OpenLibraryClient {

    @GetMapping(path = "/isbn/{isbn}.json", produces = "application/json")
    BookOpenLibrary getBookByISBN(@PathVariable("isbn") String isbn);

    @GetMapping(path = "/search.json", produces = "application/json")
    EnvelopeData searchBookByParam(@RequestParam("q") String queryParamToSearch);
}
