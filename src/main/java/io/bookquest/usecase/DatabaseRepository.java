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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<QuestionsGetRecord> getBookQuestions(String id) {
        var json = databaseClient.query("SELECT FIELDS(ALL) from Questions__c Where Livro__c = '%s' LIMIT 200".formatted(id), getToken());
        try {
            return mapper.readValue(json, new TypeReference<ObjectDataTransfer<QuestionsGetRecord>>() {})
                    .getRecords();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<AnswerRecord> getAnswerQuestions(List<QuestionsGetRecord> questions) {
        String questionsId = questions.stream().map(QuestionsGetRecord::id)
                .collect(Collectors.joining("','"));
        var json = databaseClient.query("SELECT FIELDS(ALL) from Answers__c Where Question__c IN ('%s')  LIMIT 200".formatted(questionsId), getToken());
        try {
            return mapper.readValue(json, new TypeReference<ObjectDataTransfer<AnswerRecord>>() {})
                    .getRecords();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

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

    public void saveReading(String username, String isbn, ReadingEntrypoint reading, String status) {
        Map<String, Object> response = databaseClient.saveQuiz(username.concat(isbn),
                BookMapper.toNewReadingRecord(username, isbn, reading, status), getToken());
        validateResponse(response);
    }

    public void saveBook(BookDataTransfer book) {
        var response = databaseClient.saveOrUpdateBook(book.getIsbn13(), book, getToken());
        validateResponse(response);
    }

    private static void validateResponse(Map<String, Object> response) {
        var isRequestSuccess = String.valueOf(response.get("success"))
                .equalsIgnoreCase("true");
        if (!isRequestSuccess)
            throw new RuntimeException("Requisição para o Salesforce sem sucesso: ".concat(response.toString()));
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

    public List<ReadingRecord> getBookFromUser(String idUser, String pageSize, String page) {
        var query = "SELECT FIELDS(ALL) from Reading__c Where Account__c = '%s' Limit %s OFFSET %s"
                .formatted(idUser, pageSize, page);
        String json = databaseClient.query(query, getToken());
        try {
            return mapper.readValue(json, new TypeReference<ObjectDataTransfer<ReadingRecord>>() {})
                    .getRecords();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ReadingRecord> getReading(String username, String isbn) {
        var query = "SELECT FIELDS(ALL) from Reading__c Where ExternalId__c = '%s' Limit 200"
                .formatted(username.concat(isbn));
        String json = databaseClient.query(query, getToken());
        try {
            return mapper.readValue(json, new TypeReference<ObjectDataTransfer<ReadingRecord>>() {})
                    .getRecords();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}


