package io.bookquest.usecase;

import io.bookquest.entrypoint.v1.dto.UserEntrypoint;
import io.bookquest.entrypoint.v1.integration.database.dto.UserDataTransfer;
import io.bookquest.entrypoint.v1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private DatabaseService databaseService;

    public boolean login(String username, String password) {
        return true;
    }

    public void createAccount(UserEntrypoint userEntrypoint){
        UserDataTransfer user = UserMapper.toDto(userEntrypoint);
        databaseService.saveCreate(userEntrypoint.username(), user);
    }
}
