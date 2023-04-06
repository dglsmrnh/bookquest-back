package io.bookquest.config.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "LogClient", url = "https://log-api.newrelic.com/log/v1")
public interface LogClient {

    @PostMapping
    void log(@RequestParam("Api-Key") String apiKey, @RequestBody Object log);
}
