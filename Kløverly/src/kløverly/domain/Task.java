package kløverly.domain;

import java.util.Objects;

public abstract class Task
{
  private String title;
  private String type;
  private int value;
  private String description;

  public Task(String title, String type, int value, String description)
  {
    this.title = title;
    this.type = type;
    this.value = value;
    this.description = description;
  }

  public Task()
  {
  }

  @Override public String toString()
  {
    return "Task{" + "title='" + title + '\'' + ", type='" + type + '\''
        + ", value=" + value + ", description='" + description + '\'' + '}';
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
