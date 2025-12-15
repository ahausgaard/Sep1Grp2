package kløverly.persistence;

import kløverly.domain.GreenGoal;
import kløverly.domain.Resident;
import kløverly.domain.Task;

import java.util.List;

public interface DataManager
{
  void addTask(Task task);
  void deleteTask(Task task);
  List<Task> getAllTasks();

  void addResident(Resident resident);
  void deleteResident(Resident resident);
  List<Resident> getAllResidents();

  void setGreenGoal(GreenGoal goal);
  GreenGoal getGreenGoal();

  void saveData();
  DataContainer loadData();


}
