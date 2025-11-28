package kløverly.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import kløverly.presentation.core.ViewManager;

public class MainViewController
{

  public Button addPlanetButton;
  public Button viewPlanetsButton;
  public Button searchPlanet;

  public void onAddPlanetButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("AddPlanet");
  }


  public void onViewPlanetsButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("ViewAllPlanets");
  }

  public void onSearchPlanetButtonPressed(ActionEvent actionEvent)
  {
  }
}
