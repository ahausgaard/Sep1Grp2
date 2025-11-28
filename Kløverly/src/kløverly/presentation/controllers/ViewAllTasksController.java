package kløverly.presentation.controllers;

import javafx.event.ActionEvent;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.ViewManager;

public class ViewAllTasksController
{

  public void init(DataManager dm)
  {

  }

  public void onBackButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("Home");
  }
}
