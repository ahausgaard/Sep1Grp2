package Kløverly.persistence;

import Kløverly.domain.Planet;

import java.util.List;

public interface DataManager
{
  void addPlanet(Planet planet);
  List<Planet> getAllPlanets();
  Planet getPlanetByName(String name);
  void deletePlanet(Planet planet);
}
