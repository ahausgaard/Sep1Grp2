package kløverly.persistence;

import kløverly.domain.Task;

import java.util.List;

public class ListDataManager implements DataManager
{
  private DataContainer dataContainer;

  public ListDataManager()
  {
    this.dataContainer = new DataContainer(new java.util.ArrayList<>());
  }

  @Override public void addPlanet(Task planet)
  {
    dataContainer.getPlanetList().add(planet);
  }

  @Override public List<Task> getAllPlanets()
  {
    return dataContainer.getPlanetList();
  }

  @Override public Task getPlanetByName(String name)
  {
    return dataContainer.getPlanetList().stream()
        .filter(p -> p.getName().equalsIgnoreCase(name))
        .findFirst()
        .orElse(null);
  }

  @Override public void deletePlanet(Task planet)
  {
    dataContainer.getPlanetList().remove(planet);
  }
}
