package dio.taskmanager.application;

import dio.taskmanager.application.output.TaskOutput;
import dio.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTaskUseCase {
    private final TaskRepository repository;

    public GetTaskUseCase(TaskRepository taskRepository) {
        this.repository = taskRepository;
    }
    public List<TaskOutput> execute() {
        var tasks = repository.findAll();
        return repository.findAll().stream().map(TaskOutput::from).toList();
    }
}
