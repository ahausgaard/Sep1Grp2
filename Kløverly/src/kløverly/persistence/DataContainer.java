package kløverly.persistence;

import kløverly.domain.CommunityGoal;
import kløverly.domain.CommunityTask;
import kløverly.domain.Resident;
import kløverly.domain.Task;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataContainer implements Serializable
{
  @Serial
  private static final long serialVersionUID = 2L;

  private List<Task> taskList;
  private List<Resident> residentList;
  private CommunityGoal communityGoal;

  public DataContainer(List<Task> taskList, List<Resident> residentList, CommunityGoal goal)
  {
    this.taskList = (taskList!= null) ? taskList : new ArrayList<>();
    this.residentList = (residentList != null) ? residentList : new ArrayList<>();
    this.communityGoal = (goal != null) ? goal : new CommunityGoal("Nyt fællesmål", 1000,
        LocalDate.of(2026, 12, 24), "præmie");
  }

  public void setTaskList(List<Task> taskList)
  {
    this.taskList = taskList;
  }

  public void setResidentList(List<Resident> residentList)
  {
    this.residentList = residentList;
  }

  public void setCommunityGoal(CommunityGoal communityGoal)
  {
    this.communityGoal = communityGoal;
  }

  public CommunityGoal getCommunityGoal()
  {
    return communityGoal;
  }

  public DataContainer()
  {
    this.taskList = new ArrayList<>();
    this.residentList = new ArrayList<>();
    this.communityGoal = new CommunityGoal("Nyt fællesmål", 1000, LocalDate.of(2026, 12, 24), "præmie");
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
    return "DataContainer{" + "taskList=" + taskList + ", residentList="
        + residentList + ", communityGoal=" + communityGoal + '}';
  }
}
