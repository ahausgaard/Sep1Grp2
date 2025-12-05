package kløverly.persistence;

import kløverly.domain.CommunityGoal;
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

  void setGoal(CommunityGoal goal);
  CommunityGoal getCommunityGoal();
  void addCommunityPoints(int points);
  int getCurrentCommunityPoints();


  void saveData();
  DataContainer loadData();


}
