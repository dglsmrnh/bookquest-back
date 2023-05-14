package io.bookquest.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.bookquest.entrypoint.v1.dto.UserEntrypoint;
import io.bookquest.util.TestUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationServiceTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldRegisterUserWhenRequestIsValid() throws Exception {
        String randomUsername = TestUtil.generateString();
        String randomName = TestUtil.generateString();
        String randomPassword = TestUtil.generateString();
        UserEntrypoint user = new UserEntrypoint(randomUsername, randomPassword, randomName, null, null, null, "Free", randomName.concat("@gmail.com"));

        String payload = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/v1/users").content(payload).contentType("application/json"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void shouldLoginWhenUserIsRegisteredAndRequestIsValid() throws Exception {
        String randomUsername = TestUtil.generateString();
        String randomName = TestUtil.generateString();
        String randomPassword = TestUtil.generateString();
        UserEntrypoint user = new UserEntrypoint(randomUsername, randomPassword, randomName, null, null, null, "Free", randomName.concat("@gmail.com"));

        String payload = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/v1/users").content(payload).contentType("application/json"))
                .andExpect(status().is2xxSuccessful());
    }
}
