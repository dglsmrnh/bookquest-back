package io.bookquest.entrypoint.v1.integration.database;

import io.bookquest.entrypoint.v1.integration.database.dto.TokenDataTransfer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "TokenClient", url = "https://login.salesforce.com/services/oauth2/token")
public interface TokenClient {

    @PostMapping
    TokenDataTransfer getToken(@RequestParam("grant_type") String grant,
                               @RequestParam("client_id") String clientId,
                               @RequestParam("client_secret") String clientSecret,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password);


}
