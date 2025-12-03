package Kløverly.persistence;

import Kløverly.domain.Planet;

import java.util.List;

public class DataContainer
{
  private List<Planet> planetList;

  public DataContainer(List<Planet> planetList)
  {
    this.planetList = planetList;
  }

  public List<Planet> getPlanetList()
  {
    return planetList;
  }
}
