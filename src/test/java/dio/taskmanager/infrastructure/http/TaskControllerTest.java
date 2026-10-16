package dio.taskmanager.infrastructure.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.UUID;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldHandleCompleteTaskLifecycle() throws Exception {
        var createPayload = Map.of(
                "title", "Estudar Spring Boot",
                "description", "Revisar testes de integração"
        );

        var createResponse = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createPayload)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Estudar Spring Boot"))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String taskId = objectMapper.readTree(createResponse).get("id").asText();

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))));

        mockMvc.perform(get("/tasks/{id}", taskId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(taskId))
                .andExpect(jsonPath("$.title").value("Estudar Spring Boot"));

        var updatePayload = Map.of(
                "title", "Estudar Spring Boot 3",
                "description", "Atualizar documentação",
                "status", "in-progress"
        );

        mockMvc.perform(patch("/tasks/{id}", taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatePayload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Estudar Spring Boot 3"))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));

        mockMvc.perform(delete("/tasks/{id}", taskId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/tasks/{id}", taskId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldRejectInvalidTaskAndReturnNotFoundForUnknownTask() throws Exception {
        var invalidPayload = Map.of("title", "   ", "description", "descrição inválida");

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidPayload)))
                .andExpect(status().isBadRequest());

        var missingId = UUID.randomUUID();

        mockMvc.perform(get("/tasks/{id}", missingId))
                .andExpect(status().isNotFound());
    }
}
