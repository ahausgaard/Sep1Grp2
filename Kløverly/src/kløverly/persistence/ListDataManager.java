package kløverly.persistence;

import kløverly.domain.Resident;
import kløverly.domain.Task;

import java.util.List;

public class ListDataManager implements DataManager
{
  private DataContainer dataContainer;

  public ListDataManager()
  {
    this.dataContainer = new DataContainer(new java.util.ArrayList<>());
  }

  @Override public void addTask(Task task)
  {
    dataContainer.getTaskList().add(task);
  }

  @Override public List<Task> getAllTasks()
  {
    return List.of();
  }

  @Override public List<Resident> getAllResidents()
  {
    return List.of();
  }
}
