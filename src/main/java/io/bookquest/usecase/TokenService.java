package io.bookquest.usecase;

import io.bookquest.entrypoint.v1.integration.database.DatabaseClient;
import io.bookquest.entrypoint.v1.integration.database.TokenClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

    public String getToken() {
        return databaseClient.getToken("password", clientId, clientSecret, username, password)
                .accessToken();
    }
}
