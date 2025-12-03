package Kløverly.presentation.controllers;

import javafx.event.ActionEvent;
import Kløverly.persistence.DataManager;
import Kløverly.presentation.core.ViewManager;

public class ViewAllPlanetsController {

  public void init(DataManager dm)
  {

  }

  public void onBackButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("Home");
  }
}
