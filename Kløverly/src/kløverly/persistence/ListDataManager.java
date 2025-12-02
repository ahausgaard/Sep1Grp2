package kløverly.persistence;

import kløverly.domain.Resident;
import kløverly.domain.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListDataManager implements DataManager
{
  private static final String TASK_FILE_PATH = "tasks.bin";
  private DataContainer dataContainer;

  public ListDataManager()
  {
    this.dataContainer = loadData();
  }

  @Override public void addTask(Task task)
  {
    dataContainer.getTaskList().add(task);
    saveData();
  }

  @Override public List<Task> getAllTasks()
  {
    return dataContainer.getTaskList();
  }

  @Override public List<Resident> getAllResidents()
  {
    return List.of();
  }

  @Override public String toString()
  {
    return "ListDataManager{" + "dataContainer=" + dataContainer + '}';
  }

  @Override
  public void saveData()
  {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tasks.bin")))
    {
      oos.writeObject(dataContainer);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public DataContainer loadData()
  {
    try (ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream("tasks.bin")))
    {
      return (DataContainer) ois.readObject();
    }
    catch (IOException | ClassNotFoundException e)
    {
      return new DataContainer(new ArrayList<>(), new ArrayList<>());
    }
  }
}



