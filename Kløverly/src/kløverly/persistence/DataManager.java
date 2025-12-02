package kløverly.persistence;

import kløverly.domain.Resident;
import kløverly.domain.Task;

import java.util.List;

public interface DataManager
{
  void addTask(Task task);
  void addResident(Resident resident);
  List<Task> getAllTasks();
  List<Resident> getAllResidents();
  void saveData();
  DataContainer loadData();

}
