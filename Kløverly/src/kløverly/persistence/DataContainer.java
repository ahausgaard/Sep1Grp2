package kløverly.persistence;

import kløverly.domain.Task;

import java.util.List;

public class DataContainer
{
  private List<Task> taskList;


  public DataContainer(List<Task> taskList)
  {
    this.taskList = taskList;
  }

  public List<Task> getTaskList()
  {
    return taskList;
  }
}
