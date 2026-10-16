package dio.taskmanager.application;

import dio.taskmanager.application.input.CreateTaskInput;
import dio.taskmanager.application.output.TaskOutput;
import dio.taskmanager.domain.Task;
import dio.taskmanager.domain.TaskRepository;
import dio.taskmanager.domain.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateTaskUseCaseTest {
    private CreateTaskUseCase createTaskUseCase;
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        createTaskUseCase = new CreateTaskUseCase(taskRepository);
    }

    @Test
    void shouldCreateTaskAndReturnOutput() {
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TaskOutput output = createTaskUseCase.execute(
            new CreateTaskInput("Estudar Spring", Optional.of("Revisar testes"))
        );

        assertNotNull(output);
        assertEquals("Estudar Spring", output.title());
        assertEquals(Optional.of("Revisar testes"), output.description());
        assertEquals(TaskStatus.PENDING.name(), output.status());
        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void shouldRejectBlankTitle() {
        assertThrows(IllegalArgumentException.class, () ->
            createTaskUseCase.execute(new CreateTaskInput("   ", Optional.empty()))
        );

        verify(taskRepository, never()).save(any(Task.class));
    }
}