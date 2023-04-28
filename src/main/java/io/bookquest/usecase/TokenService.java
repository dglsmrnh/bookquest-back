package io.bookquest.usecase;

import io.bookquest.entrypoint.v1.integration.database.DatabaseClient;
import io.bookquest.entrypoint.v1.integration.database.TokenClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TokenService {

    @Autowired
    private TokenClient databaseClient;

    @Value("${salesforce.client_id}")
    private String clientId;

    @Value("${salesforce.client_secret}")
    private String clientSecret;

    @Value("${salesforce.username}")
    private String username;

    @Value("${salesforce.password}")
    private String password;

    private String token;

    public String getToken() {
        if (Objects.isNull(token))
            setToken();
        return token;
    }

    @Scheduled(fixedDelay = 100000)
    public void setToken() {
        this.token = databaseClient.getToken("password", clientId, clientSecret, username, password)
                .accessToken();
    }
}
