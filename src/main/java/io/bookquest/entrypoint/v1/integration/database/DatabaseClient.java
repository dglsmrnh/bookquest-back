package io.bookquest.entrypoint.v1.integration.database;

import io.bookquest.entrypoint.v1.integration.database.dto.BookDataTransfer;
import io.bookquest.entrypoint.v1.integration.database.dto.RecordDataTransfer;
import io.bookquest.entrypoint.v1.integration.database.dto.UserDataTransfer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

@FeignClient(name = "DatabaseClient",
        url = "https://bookquest-dev-ed.develop.my.salesforce.com/services/data/v57.0")
public interface DatabaseClient {
    @PatchMapping("/sobjects/Book__c/ISBN__c/{isbn13}")
    Map<String, Object> saveOrUpdateBook(@PathVariable("isbn13") String isbn13, @RequestBody BookDataTransfer book,
                            @RequestHeader("Authorization") String authToken);

    @PatchMapping("/composite/sobjects/Category__c/ExternalId__c")
    List<Map<String, Object>> saveOrUpdateCategories(@RequestBody RecordDataTransfer book,
                                                     @RequestHeader("Authorization") String authToken);

    @PatchMapping("/composite/sobjects/BookCategory__c/ExternalId13__c")
    List<Map<String, Object>> saveOrUpdateBookCategory(@RequestBody RecordDataTransfer record,
                                                     @RequestHeader("Authorization") String authToken);

    @PatchMapping("/sobjects/Account/Username__c/{username}}")
    Map<String, Object> saveUser(@PathVariable("username") String username, @RequestBody UserDataTransfer user,
                                         @RequestHeader("Authorization") String authToken);

}
