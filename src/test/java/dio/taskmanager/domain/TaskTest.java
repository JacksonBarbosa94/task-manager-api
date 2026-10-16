package dio.taskmanager.domain;

import dio.taskmanager.infrastructure.http.request.UpdateTaskRequest;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void shouldCreateTaskWithPendingStatus() {
        Task task = new Task("Estudar Spring", Optional.of("Revisar testes"));

        assertNotNull(task.getId());
        assertEquals("Estudar Spring", task.getTitle());
        assertEquals(Optional.of("Revisar testes"), task.getDescription());
        assertEquals(TaskStatus.PENDING, task.getStatus());
    }

    @Test
    void shouldRejectNullOrBlankTitle() {
        assertThrows(IllegalArgumentException.class, () -> new Task(null, Optional.empty()));
        assertThrows(IllegalArgumentException.class, () -> new Task("   ", Optional.empty()));
    }

    @Test
    void shouldRejectBlankTitleOnUpdate() {
        Task task = new Task("Estudar Spring", Optional.of("Revisar testes"));

        assertThrows(IllegalArgumentException.class, () -> task.update(Optional.of("   "), Optional.empty(), Optional.empty()));
        assertEquals("Estudar Spring", task.getTitle());
    }

    @Test
    void shouldNormalizeStatusInUpdateRequest() {
        UpdateTaskRequest request = new UpdateTaskRequest(Optional.empty(), Optional.empty(), Optional.of("in-progress"));

        assertEquals(TaskStatus.IN_PROGRESS, request.toInput().status().orElseThrow());
    }
}