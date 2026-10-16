package dio.taskmanager.application;

import dio.taskmanager.application.input.CreateTaskInput;
import dio.taskmanager.application.output.TaskOutput;
import dio.taskmanager.domain.Task;
import dio.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

/**
 * Use case for creating a new task.
 */
@Service
public class CreateTaskUseCase {
    private final TaskRepository taskRepository;

    public CreateTaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskOutput execute(CreateTaskInput input) {
      var task = new Task(input.title(), input.description());
      var savedTask = taskRepository.save(task);
        // Implementation for creating a task

        //repositury save
        return TaskOutput.from(savedTask);
    }
}
