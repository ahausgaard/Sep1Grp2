package Kløverly.persistence;

import Kløverly.domain.Planet;

import java.util.List;

public class ListDataManager implements DataManager
{
  private DataContainer dataContainer;

  public ListDataManager()
  {
    this.dataContainer = new DataContainer(new java.util.ArrayList<>());
  }

  @Override public void addPlanet(Planet planet)
  {
    dataContainer.getPlanetList().add(planet);
  }

  @Override public List<Planet> getAllPlanets()
  {
    return dataContainer.getPlanetList();
  }

  @Override public Planet getPlanetByName(String name)
  {
    return dataContainer.getPlanetList().stream()
        .filter(p -> p.getName().equalsIgnoreCase(name))
        .findFirst()
        .orElse(null);
  }

  @Override public void deletePlanet(Planet planet)
  {
    dataContainer.getPlanetList().remove(planet);
  }
}
