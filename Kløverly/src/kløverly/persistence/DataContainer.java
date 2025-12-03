package kløverly.persistence;

import kløverly.domain.CommunityTask;
import kløverly.domain.Resident;
import kløverly.domain.Task;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataContainer implements Serializable
{
  @Serial
  private static final long serialVersionUID = 1L;

  private List<Task> taskList;
  private List<Resident> residentList;

  public DataContainer(List<Task> taskList, List<Resident> residentList)
  {
    this.taskList = (taskList!= null) ? taskList : new ArrayList<>();
    this.residentList = (residentList != null) ? residentList : new ArrayList<>();
  }

  public void setTaskList(List<Task> taskList)
  {
    this.taskList = taskList;
  }

  public void setResidentList(List<Resident> residentList)
  {
    this.residentList = residentList;
  }

  public DataContainer()
  {
    this.taskList = new ArrayList<>();
    this.residentList = new ArrayList<>();
  }

  public List<Task> getTaskList()
  {
    return taskList;
  }

  public List<Resident> getResidentList()
  {
    return residentList;
  }

  @Override public String toString()
  {
    return "{taskList=" + taskList + "residentList=" + residentList + "}";
  }
}
