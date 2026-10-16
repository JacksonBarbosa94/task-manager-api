package dio.taskmanager.application;

import dio.taskmanager.application.output.TaskOutput;
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.domain.TaskNotFoundException;
import dio.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class GetTaskByIdUseCase {
    private final TaskRepository repository;

    public GetTaskByIdUseCase(TaskRepository taskRepository) {
        this.repository = taskRepository;
    }

    public TaskOutput execute(TaskId id) {
        return repository.findById(id).map(TaskOutput::from).orElseThrow(() -> new TaskNotFoundException(id));
    }
    }

