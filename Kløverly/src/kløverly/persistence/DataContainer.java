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
    taskList.add(new CommunityTask("Bladopsamling", "Fælles", 40, "Samle blade for John"));
    taskList.add(new CommunityTask("Aspargeshøst", "Fælles", 30, "Høst Johns asparges"));
    taskList.add(new CommunityTask("Rygmassage", "Fælles", 20, "Massér Johns ryg"));
    taskList.add(new CommunityTask("Tal med ensom person", "Fælles", 10, "Tal med John om hans problemer."));
}
}
