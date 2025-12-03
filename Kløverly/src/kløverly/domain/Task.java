package kløverly.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public abstract class Task implements Serializable
{
  @Serial
  private static final long serialVersionUID = 1L;

  private String title;
  private String type;
  private int value;
  private String description;
  private TaskStatus status;

  public Task(String title, String type, int value, String description)
  {
    this.title = title;
    this.type = type;
    this.value = value;
    this.description = description;
    this.status = TaskStatus.open;
  }

  public Task()
  {
  }

  public String getType()
  {
    return type;
  }

  public int getValue()
  {
    return value;
  }

  public String getDescription()
  {
    return description;
  }

  public String getTitle()
  {
    return title;
  }

  public TaskStatus getStatus()
  {
    return status;
  }

  @Override public String toString()
  {
    return "Task{" + "title='" + title + '\'' + ", type='" + type + '\''
        + ", value=" + value + ", description='" + description + '\'' + ", status=" + status + '}';
  }

  @Override public boolean equals(Object o)
  {
    if (!(o instanceof Task task))
      return false;
    return value == task.value && Objects.equals(title, task.title)
        && Objects.equals(type, task.type) && Objects.equals(description,
        task.description);
  }

  @Override public int hashCode()
  {
    return Objects.hash(title, type, value, description);
  }
}
