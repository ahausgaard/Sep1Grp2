package kløverly.persistence;

import kløverly.domain.CommunityGoal;
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

  //Task
  @Override public void addTask(Task task)
  {
    dataContainer.getTaskList().add(task);
    saveData();
  }

  @Override public void deleteTask(Task task)
  {
    dataContainer.getTaskList().remove(task);
    saveData();
  }

  @Override public List<Task> getAllTasks()
  {
    return dataContainer.getTaskList();
  }


  //Resident
  @Override public void addResident(Resident resident)
  {
    dataContainer.getResidentList().add(resident);
    saveData();
  }

  @Override public void deleteResident(Resident resident)
  {
    dataContainer.getResidentList().remove(resident);
    saveData();
  }


  @Override public List<Resident> getAllResidents()
  {
    return dataContainer.getResidentList();
  }


  //CommunityGoal
  @Override public void setGoal(CommunityGoal goal)
  {
    dataContainer.setCommunityGoal(goal);
    saveData();
  }

  @Override public void addCommunityPoints(int points)
  {
    int currentPoints = dataContainer.getCommunityGoal().getCurrentPoints();
    dataContainer.getCommunityGoal().setCurrentPoints(currentPoints + points);
    saveData();
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
      System.out.println("Saved data: " + dataContainer.toString());
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public DataContainer loadData()
  {

    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TASK_FILE_PATH)))
    {
      DataContainer container = (DataContainer) ois.readObject();

      // Ensure lists are not null after reading
      if(container.getTaskList() == null)
        container.setTaskList(new ArrayList<>());

      if(container.getResidentList() == null)
        container.setResidentList(new ArrayList<>());

      return container;
    }
    catch (IOException | ClassNotFoundException e)
    {
      DataContainer newContainer = new DataContainer();
      newContainer.setTaskList(new ArrayList<>());
      newContainer.setResidentList(new ArrayList<>());

      return newContainer;
    }
  }
}



