package io.bookquest.usecase.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.bookquest.entrypoint.v1.integration.chatsonic.ChatSonicClient;
import io.bookquest.entrypoint.v1.integration.chatsonic.dto.ChatSonicDataTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BookQuizSchedule {

    @Autowired
    private ChatSonicClient chatSonicClient;

    @Scheduled(fixedDelay = 60000)
    public void addQuestionToBook() {
        String json = chatSonicClient.aiSearch("", new ChatSonicDataTransfer("me faça 5 perguntas no formato de alternativa com as respostas certas do livro O hobbit no formato json com as opções de respostas em um array"));

        try {
            Map<String, String> map = new ObjectMapper().readValue("", new TypeReference<>() {});
            new ObjectMapper().readValue(map.get("message").replace("```JSON", "").replace("```",""), JsonNode.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
