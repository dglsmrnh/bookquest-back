package io.bookquest.entrypoint.v1.integration.chatsonic;

import feign.Headers;
import io.bookquest.entrypoint.v1.integration.chatsonic.dto.ChatSonicDataTransfer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "chatSonicClient",
        url = "https://api.writesonic.com/v2/business/content/chatsonic?engine=premium&language=pt-br")
public interface ChatSonicClient {

    @PostMapping
    String aiSearch(@RequestHeader("X-API-KEY") String apiKey, @RequestBody ChatSonicDataTransfer dto);
}
