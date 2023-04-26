package io.bookquest.entrypoint.v1.integration.mail;


import io.bookquest.entrypoint.v1.integration.mail.dto.MailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "mailClient",
        url = "https://api.courier.com/send")
public interface MailClient {

    @PostMapping
    void sendMail(@RequestBody MailDto dto, @RequestHeader("Authorization") String authToken);
}