package kløverly.persistence;

import kløverly.domain.Resident;
import kløverly.domain.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListDataManager implements DataManager
{
  private static final String TASK_FILE_PATH = "data.bin";
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

  @Override public void addResident(Resident resident)
  {
    dataContainer.getResidentList().add(resident);
    saveData();
  }

  @Override public List<Task> getAllTasks()
  {
    return dataContainer.getTaskList();
  }

  @Override public List<Resident> getAllResidents()
  {
    return dataContainer.getResidentList();
  }

  @Override public String toString()
  {
    return "ListDataManager{" + "dataContainer=" + dataContainer + '}';
  }

  @Override
  public void saveData()
  {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.bin")))
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
        new FileInputStream("data.bin")))
    {
      DataContainer container = (DataContainer) ois.readObject();

      if(container.getTaskList() == null)
        container.setTaskList(new ArrayList<>());

      if(container.getResidentList() == null)
        container.setResidentList(new ArrayList<>());

      return container;
    }
    catch (IOException | ClassNotFoundException e)
    {
      return null;
    }
  }
}



