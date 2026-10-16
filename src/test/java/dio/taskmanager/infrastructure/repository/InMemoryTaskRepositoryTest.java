package dio.taskmanager.infrastructure.repository;

import dio.taskmanager.domain.Task;
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.domain.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskRepositoryTest {

    private InMemoryTaskRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryTaskRepository();
    }

    @Test
    void saveShouldPersistTask() {
        Task task = new Task("Estudar Java", Optional.of("Revisar coleções"));

        Task saved = repository.save(task);

        assertSame(task, saved);
        assertTrue(repository.findById(task.getId()).isPresent());
        assertEquals(task, repository.findById(task.getId()).orElseThrow());
    }

    @Test
    void findAllShouldReturnStoredTasks() {
        Task first = new Task("Comprar pão", Optional.empty());
        Task second = new Task("Lavar louça", Optional.of("Depois do almoço"));
        repository.save(first);
        repository.save(second);

        List<Task> tasks = repository.findAll();

        assertEquals(2, tasks.size());
        assertEquals(Set.of(first.getId(), second.getId()), tasks.stream().map(Task::getId).collect(Collectors.toSet()));

        tasks.clear();
        assertEquals(2, repository.findAll().size());
    }

    @Test
    void findByIdShouldReturnExistingTaskAndEmptyOptionalForMissingId() {
        Task task = new Task("Fazer exercício", Optional.of("30 minutos"));
        repository.save(task);

        assertTrue(repository.findById(task.getId()).isPresent());
        assertEquals("Fazer exercício", repository.findById(task.getId()).orElseThrow().getTitle());
        assertTrue(repository.findById(new TaskId()).isEmpty());
    }

    @Test
    void deleteShouldRemoveTaskFromRepository() {
        Task task = new Task("Enviar e-mail", Optional.of("Resumo do sprint"));
        repository.save(task);

        repository.delete(task.getId());

        assertTrue(repository.findById(task.getId()).isEmpty());
        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    void taskShouldStartInPendingStatus() {
        Task task = new Task("Organizar agenda", Optional.empty());

        assertEquals(TaskStatus.PENDING, task.getStatus());
    }
}