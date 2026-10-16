package dio.taskmanager.domain;

import lombok.Getter;
import org.springframework.util.Assert;

import java.util.Optional;

@Getter
public class Task {
    private TaskId id;
    private String title;
    private Optional<String> description;
    private TaskStatus status;

    public Task(String title, Optional<String> description) {
        Assert.notNull(description, "Description cannot be null");

        this.id = new TaskId();
        setTitle(title);
        setDescription(description);
        setStatus(TaskStatus.PENDING);
    }

    public void setTitle(String title) {
        Assert.hasText(title, "Title cannot be null or empty");
        this.title = title;
    }

    public void setDescription(Optional<String> description) {
        Assert.notNull(description, "Description cannot be null");
        this.description = description;
    }

    public void setStatus(TaskStatus status) {
        Assert.notNull(status, "Status cannot be null");
        this.status = status;
    }

    public void update(Optional<String> title, Optional<String> description, Optional<TaskStatus> status) {
        Assert.notNull(title, "Title cannot be null");
        Assert.notNull(description, "Description cannot be null");
        Assert.notNull(status, "Status cannot be null");

        title.ifPresent(this::setTitle);
        description.ifPresent(d -> this.setDescription(Optional.of(d)));
        status.ifPresent(this::setStatus);
    }
}