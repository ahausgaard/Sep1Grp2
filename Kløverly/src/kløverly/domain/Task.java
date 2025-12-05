package kløverly.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import kløverly.util.IdGenerator;

public abstract class Task implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String title;
    private int value;
    private String description;
    private TaskStatus status;
    private String id;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Task(String prefix, String title, int value, String description) {
        this.title = title;
        this.value = value;
        this.description = description;
        this.status = TaskStatus.open;
        this.id = IdGenerator.generate(prefix);
    }

    // Lav en switch til task der ændre fra engelsk til dansk

    public Task() {
    }

    public String getType()
    {
        return this.getClass().getSimpleName();
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Task{" + "title='" + title + '\'' + '\''
                + ", value=" + value + ", description='" + description + '\'' + ", status=" + status + '}';
    }
}
