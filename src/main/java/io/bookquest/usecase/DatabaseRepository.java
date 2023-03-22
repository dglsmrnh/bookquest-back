package io.bookquest.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.bookquest.entrypoint.v1.dto.ReadingEntrypoint;
import io.bookquest.entrypoint.v1.integration.database.ApexClient;
import io.bookquest.entrypoint.v1.integration.database.DatabaseClient;
import io.bookquest.entrypoint.v1.integration.database.dto.*;
import io.bookquest.entrypoint.v1.mapper.BookMapper;
import io.bookquest.entrypoint.v1.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DatabaseRepository {

    @Autowired
    private DatabaseClient databaseClient;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ApexClient apexClient;

    public List<BookDataTransfer> getBook(String title, String isbn10, String isbn13) {
        return apexClient.getBook(title, isbn10, isbn13, getToken());
    }

    public List<BookDataTransfer> findAllBookWithoutQuiz() {
        String json = databaseClient.query("SELECT FIELDS(ALL) from Book__c Where QuizEnable__c=false AND ISBN__c != null Limit 200", getToken());
        try {
            return mapper.readValue(json, new TypeReference<ObjectDataTransfer<BookDataTransfer>>() {})
                    .getRecords();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveQuiz(String isbn) {
        QuizRecord quiz = new QuizRecord(Map.of("ISBN__c", isbn));
        var response = databaseClient.saveQuiz(quiz, getToken());
        validateResponse(response);
    }

    public List<String> saveRecordList(ObjectDataTransfer<Record> questions) {
        var response = databaseClient.batchInsert(questions, getToken());
        return response.stream().map(batchResponse -> {
            validateResponse(batchResponse);
            return String.valueOf(batchResponse.get("id"));
        }).toList();
    }

    public void saveReading(String username, String isbn, ReadingEntrypoint reading) {
        databaseClient.saveQuiz(username.concat(isbn),
                BookMapper.toNewReadingRecord(username, isbn, reading), getToken());
    }

    public void saveBook(BookDataTransfer book) {
        var response = databaseClient.saveOrUpdateBook(book.getIsbn13(), book, getToken());
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
        response.forEach(DatabaseRepository::validateResponse);
    }

    public void saveBookCategory(RecordDataTransfer record) {
        var response = databaseClient.saveOrUpdateBookCategory(record, getToken());
        response.forEach(DatabaseRepository::validateResponse);
    }

    public void saveCreate(String username, UserDataTransfer user) {
        var response = databaseClient.saveUser(username, user, getToken());
        validateResponse(response);
    }

    public UserDataTransfer getUser(String username) {
        return databaseClient.getUser(username, getToken());
    }

    public CategoryRecord getClass(String idClass) {
        return databaseClient.getClass(idClass, getToken());
    }

    private String getToken() {
        return "Bearer ".concat(tokenService.getToken());
    }
}
