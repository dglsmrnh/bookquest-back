package io.bookquest.usecase;

import io.bookquest.entrypoint.v1.dto.ReadingEntrypoint;
import io.bookquest.entrypoint.v1.integration.database.dto.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

interface RepositoryCentralized {

    List<QuestionsGetRecord> getBookQuestions(String id);

    List<AnswerRecord> getAnswerQuestions(List<QuestionsGetRecord> questions);

    List<BookDataTransfer> getBook(String title, String isbn10, String isbn13);

    Optional<BookDataTransfer> getBook(String idBook);

    List<BookDataTransfer> findAllBookWithoutQuiz();

    void saveQuiz(String isbn);

    List<String> saveRecordList(ObjectDataTransfer<Record> questions);

    void saveReading(String username, String isbn, ReadingEntrypoint reading, String status);

    void saveBook(BookDataTransfer book);

    void saveCategories(List<String> categories);

    void saveBookCategory(RecordDataTransfer record);

    void saveCreate(String username, UserDataTransfer user);

    UserDataTransfer getUser(String username);

    CategoryRecord getClass(String idClass);

    List<ReadingRecord> getBookFromUser(String idUser, String pageSize, String page);

    List<ReadingRecord> getReading(String username, String isbn);
}
