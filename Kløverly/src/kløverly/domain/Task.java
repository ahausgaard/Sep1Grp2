package kløverly.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import kløverly.util.IdGenerator;

public abstract class Task implements Serializable
{
  @Serial
  private static final long serialVersionUID = 1L;

  private String title;
  private String type;
  private int value;
  private String description;
  private TaskStatus status;
  private String id;

  public void setTitle(String title)
  {
    this.title = title;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public void setValue(int value)
  {
    this.value = value;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public void setStatus(TaskStatus status)
  {
    this.status = status;
  }

  public Task(String prefix, String title, String type, int value, String description)
  {
    this.title = title;
    this.type = type;
    this.value = value;
    this.description = description;
    this.status = TaskStatus.open;
    this.id = IdGenerator.generate(prefix);
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

  public String getId(){return id;}

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
