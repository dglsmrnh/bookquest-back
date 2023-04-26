package io.bookquest.entrypoint.v1.integration.database;

import io.bookquest.entrypoint.v1.dto.UserEntrypoint;
import io.bookquest.entrypoint.v1.integration.database.dto.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "DatabaseClient",
        url = "https://bookquest-dev-ed.develop.my.salesforce.com/services/data/v57.0")
public interface DatabaseClient {
    @PatchMapping("/sobjects/Book__c/ISBN__c/{isbn13}")
    Map<String, Object> saveOrUpdateBook(@PathVariable("isbn13") String isbn13, @RequestBody BookDataTransfer book,
                                         @RequestHeader("Authorization") String authToken);

    @GetMapping("/sobjects/Book__c/ISBN__c/{isbn13}")
    BookDataTransfer getBook(@PathVariable("isbn13") String isbn13,
                             @RequestHeader("Authorization") String authToken);

    @PatchMapping("/composite/sobjects/Product2/ExternalId__c")
    List<Map<String, Object>> saveOrUpdateCategories(@RequestBody RecordDataTransfer book,
                                                     @RequestHeader("Authorization") String authToken);

    @PatchMapping("/composite/sobjects/BookCategory__c/ExternalId13__c")
    List<Map<String, Object>> saveOrUpdateBookCategory(@RequestBody RecordDataTransfer record,
                                                       @RequestHeader("Authorization") String authToken);

    @PatchMapping("/sobjects/Account/Username__c/{username}")
    Map<String, Object> saveUser(@PathVariable("username") String username,
                                 @RequestBody UserDataTransfer user,
                                 @RequestHeader("Authorization") String authToken);

    @GetMapping(value = "/sobjects/Account/Username__c/{username}", consumes = "application/json")
    UserDataTransfer getUser(@PathVariable("username") String username,
                             @RequestHeader("Authorization") String authToken);

    @GetMapping(value = "/sobjects/Product2/{id}", consumes = "application/json")
    @Cacheable("userClassOrCategory")
    CategoryRecord getClass(@PathVariable("id") String id,
                            @RequestHeader("Authorization") String authToken);

    @PatchMapping("/sobjects/Reading__c/ExternalId__c/{externalId}")
    @CacheEvict(value = "user", allEntries = true)
    Map<String, Object> saveQuiz(@PathVariable("externalId") String externalId,
                                 @RequestBody ReadingRecord reading,
                                 @RequestHeader("Authorization") String authToken);

    @GetMapping("/query")
    String query(@RequestParam("q") String query,
                 @RequestHeader("Authorization") String authToken);

    @PostMapping("/composite/sobjects")
    List<Map<String, Object>> batchInsert(@RequestBody ObjectDataTransfer<Record> record,
                                          @RequestHeader("Authorization") String authToken);

    @PostMapping("/sobjects/Quiz__c")
    Map<String, Object> saveQuiz(@RequestBody QuizRecord reading,
                                 @RequestHeader("Authorization") String authToken);

    @GetMapping(value = "/sobjects/Account/Username__c/{email}", consumes = "application/json")
    UserDataTransfer getUserByEmail(@PathVariable("email") String email,
                                  @RequestHeader("Authorization") String authToken);

}
