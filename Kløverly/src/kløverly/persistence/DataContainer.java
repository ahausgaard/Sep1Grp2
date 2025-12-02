package kløverly.persistence;

import kløverly.domain.CommunityTask;
import kløverly.domain.Task;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class DataContainer implements Serializable
{
  @Serial
  private static final long serialVersionUID = 1L;

  private List<Task> taskList;

  public DataContainer(List<Task> taskList, List<Resident> residentList)
  {
    if (taskList == null)
    {
      throw new IllegalArgumentException("taskList cannot be null");
    }
    if (residentList == null)
    {
      throw new IllegalArgumentException("residentList cannot be null");
    }
    this.taskList = taskList;
    this.residentList = residentList;
  }

  public List<Task> getTaskList()
  {
    return taskList;
  }



  @Override public String toString()
  {
    return "DataContainer{" + "taskList=" + taskList + '}';
  }
}
