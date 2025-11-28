package kløverly.persistence;

import kløverly.domain.Task;

import java.util.List;

public class DataContainer
{
  private List<Task> planetList;

  public DataContainer(List<Task> planetList)
  {
    this.planetList = planetList;
  }

  public List<Task> getPlanetList()
  {
    return planetList;
  }
}
