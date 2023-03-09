package io.bookquest.usecase.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.bookquest.entrypoint.v1.integration.chatsonic.ChatSonicClient;
import io.bookquest.entrypoint.v1.dto.BookQuizEntrypoint;
import io.bookquest.entrypoint.v1.integration.chatsonic.dto.ChatSonicDataTransfer;
import io.bookquest.entrypoint.v1.integration.database.dto.BookDataTransfer;
import io.bookquest.entrypoint.v1.integration.database.dto.ObjectDataTransfer;
import io.bookquest.entrypoint.v1.mapper.QuestionMapper;
import io.bookquest.persistence.entity.Book;
import io.bookquest.persistence.entity.BookQuestions;
import io.bookquest.persistence.repository.BookRepository;
import io.bookquest.persistence.repository.QuestionRepository;
import io.bookquest.usecase.DatabaseRepository;
import io.bookquest.usecase.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class BookQuizSchedule {

    @Autowired
    private ChatSonicClient chatSonicClient;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Value("${write-sonic.api.key:}")
    private String apiKey;

    @Autowired
    private DatabaseRepository databaseRepository;

    @Scheduled(fixedDelay = 60000)
    @CacheEvict(value = "getBook", allEntries = true)
    public void addQuestionToBook() {
        List<BookDataTransfer> books = databaseRepository.findAllBookWithoutQuiz();

        books.forEach(book -> {
            ObjectMapper mapper = new ObjectMapper();
            String json = chatSonicClient.aiSearch(apiKey, new ChatSonicDataTransfer(book.getName()));

            try {
                Map<String, String> map = mapper.readValue(json, Map.class);
                var jsonNormalize = JsonUtil.normalizeChatSonicJson(map);

                var bookQuestions = mapper.readValue(jsonNormalize, new TypeReference<List<BookQuizEntrypoint>>() {});
                var questionsRecord = QuestionMapper.toRecord(bookQuestions, book);
                databaseRepository.saveAllQuestions(questionsRecord);

                book.setQuizEnable(true);
                databaseRepository.saveBook(book);
            } catch (JsonProcessingException e) {

            }
        });
    }
}
