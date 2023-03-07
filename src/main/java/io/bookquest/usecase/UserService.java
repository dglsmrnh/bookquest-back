package io.bookquest.usecase;

import io.bookquest.entrypoint.v1.dto.UserEntrypoint;
import io.bookquest.entrypoint.v1.integration.database.dto.UserDataTransfer;
import io.bookquest.entrypoint.v1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private DatabaseService databaseService;

    //pending bcrypt to save e compare
    public void login(String username, String password) {
        var user = databaseService.getUser(username);
        var authorize = user.senha()
                .equalsIgnoreCase(password);

        if (!authorize)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    public void createAccount(UserEntrypoint userEntrypoint) {
        UserDataTransfer user = UserMapper.toDto(userEntrypoint);
        databaseService.saveCreate(userEntrypoint.username(), user);
    }
}
