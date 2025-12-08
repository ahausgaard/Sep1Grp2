package kløverly.persistence;

import kløverly.domain.GreenGoal;
import kløverly.domain.Resident;
import kløverly.domain.Task;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataContainer implements Serializable
{


  private List<Task> taskList;
  private List<Resident> residentList;
  private GreenGoal greenGoal;

  public DataContainer(List<Task> taskList, List<Resident> residentList, GreenGoal goal)
  {
    this.taskList = (taskList!= null) ? taskList : new ArrayList<>();
    this.residentList = (residentList != null) ? residentList : new ArrayList<>();
    this.greenGoal = (goal != null) ? goal : new GreenGoal("Nyt grønt mål", 1000,
        LocalDate.of(2026, 12, 24), "præmie");
  }
  public DataContainer()
  {
    this.taskList = new ArrayList<>();
    this.residentList = new ArrayList<>();
    this.greenGoal = new GreenGoal("Nyt grønt mål", 1000, LocalDate.of(2026, 12, 24), "præmie");
  }

  public List<Task> getTaskList()
  {
    return taskList;
  }
  public void setTaskList(List<Task> taskList)
  {
    this.taskList = taskList;
  }

  public List<Resident> getResidentList()
  {
    return residentList;
  }
  public void setResidentList(List<Resident> residentList)
  {
    this.residentList = residentList;
  }

  public GreenGoal getGreenGoal()
  {
    return greenGoal;
  }
  public void setGreenGoal(GreenGoal greenGoal)
  {
    this.greenGoal = greenGoal;
  }

  @Override public String toString()
  {
    return "DataContainer{" + "taskList=" + taskList + ", residentList="
        + residentList + ", greenGoal=" + greenGoal + '}';
  }
}
