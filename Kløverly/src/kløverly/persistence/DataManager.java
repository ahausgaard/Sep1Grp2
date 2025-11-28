package kløverly.persistence;

import kløverly.domain.Task;

import java.util.List;

public interface DataManager
{
  void addPlanet(Task planet);
  List<Task> getAllPlanets();
  Task getPlanetByName(String name);
  void deletePlanet(Task planet);
}
