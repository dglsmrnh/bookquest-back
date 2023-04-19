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
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Profile("dev")
public class DatabaseInMemoryRepository implements RepositoryCentralized {

    public List<QuestionsGetRecord> getBookQuestions(String id) {
        return null;
    }

    public List<AnswerRecord> getAnswerQuestions(List<QuestionsGetRecord> questions) {
        return null;
    }

    public List<BookDataTransfer> getBook(String title, String isbn10, String isbn13) {
        return null;
    }

    public Optional<BookDataTransfer> getBook(String idBook) {
        return null;
    }

    public List<BookDataTransfer> findAllBookWithoutQuiz() {
        return null;
    }

    public void saveQuiz(String isbn) {

    }

    public List<String> saveRecordList(ObjectDataTransfer<Record> questions) {
        return null;
    }

    public void saveReading(String username, String isbn, ReadingEntrypoint reading, String status) {

    }

    public void saveBook(BookDataTransfer book) {

    }

    public void saveCategories(List<String> categories) {

    }

    public void saveBookCategory(RecordDataTransfer record) {

    }

    public void saveCreate(String username, UserDataTransfer user) {

    }

    public UserDataTransfer getUser(String username) {
        return null;
    }

    public CategoryRecord getClass(String idClass) {
        return null;
    }

    public List<ReadingRecord> getBookFromUser(String idUser, String pageSize, String page) {
        return null;
    }

    public List<ReadingRecord> getReading(String username, String isbn) {
        return null;
    }
}


