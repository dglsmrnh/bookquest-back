package io.bookquest.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private DatabaseService databaseService;

    public boolean login(String username, String password) {
        return true;
    }

    public void createAccount(){

    }
}
