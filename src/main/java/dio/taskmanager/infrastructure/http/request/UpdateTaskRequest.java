package dio.taskmanager.infrastructure.http.request;

import dio.taskmanager.application.input.UpdateTaskInput;
import dio.taskmanager.domain.TaskStatus;

import java.util.Locale;
import java.util.Optional;

public record UpdateTaskRequest(
        Optional<String> title,
        Optional<String> description,
        Optional<String> status
)
{
    public UpdateTaskInput toInput() {
        return new UpdateTaskInput(title, description,
                status.map(value -> TaskStatus.valueOf(value.trim().replace('-', '_').replace(' ', '_').toUpperCase(Locale.ROOT))));
    }

}
