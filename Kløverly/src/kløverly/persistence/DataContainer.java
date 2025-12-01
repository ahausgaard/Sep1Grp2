package kløverly.persistence;

import kløverly.domain.CommunityTask;
import kløverly.domain.Task;

import java.util.List;

public class DataContainer
{
  private List<Task> taskList;

  public DataContainer(List<Task> taskList)
  {
    this.taskList = taskList;
    initializeData();
  }

  public List<Task> getTaskList()
  {
    return taskList;
  }


  private void initializeData() {
    taskList.add(new CommunityTask("Task1", "Community", 40, "Collecting Leaves for John"));
    taskList.add(new CommunityTask("Task2", "Community", 30, "Collecting asparagus for John"));
    taskList.add(new CommunityTask("Task3", "Community", 20, "Rubbing John's back"));
    taskList.add(new CommunityTask("Task4", "Community", 10, "Talk to John"));
}
}
