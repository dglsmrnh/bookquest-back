package io.bookquest.usecase;

import io.bookquest.entrypoint.v1.integration.database.DatabaseClient;
import io.bookquest.entrypoint.v1.integration.database.dto.BookDataTransfer;
import io.bookquest.entrypoint.v1.integration.database.dto.RecordDataTransfer;
import io.bookquest.entrypoint.v1.integration.database.dto.UserDataTransfer;
import io.bookquest.entrypoint.v1.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DatabaseService {

    @Autowired
    private DatabaseClient databaseClient;

    @Autowired
    private TokenService tokenService;

    public void saveBook(BookDataTransfer book) {
        var response = databaseClient.saveOrUpdateBook(book.isbn13(), book, getToken());
        validateResponse(response);
    }

    private static void validateResponse(Map<String, Object> response) {
        var isRequestSuccess = String.valueOf(response.get("success"))
                .equalsIgnoreCase("true");
        if (!isRequestSuccess)
            throw new RuntimeException("Requisição para o Salesforce sem sucesso");
    }

    public void saveCategories(List<String> categories) {
        var response = databaseClient.saveOrUpdateCategories(CategoryMapper.toCategoryDataTransfer(categories), getToken());
        response.forEach(DatabaseService::validateResponse);
    }

    public void saveBookCategory(RecordDataTransfer record) {
        var response = databaseClient.saveOrUpdateBookCategory(record, getToken());
        response.forEach(DatabaseService::validateResponse);
    }

    public void saveCreate(String username , UserDataTransfer user) {
        var response = databaseClient.saveUser(username, user, getToken());
        validateResponse(response);
    }

    private String getToken() {
        return "Bearer ".concat(tokenService.getToken());
    }
}
