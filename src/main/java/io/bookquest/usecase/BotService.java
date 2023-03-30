package io.bookquest.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BotService {

    @Autowired
    private DatabaseRepository databaseRepository;

    public void getAllAvaiableClass(String username) {

    }
}
