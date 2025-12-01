package kløverly.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.ViewManager;

public class ViewAvailableTasksController
{

  public TableColumn taskNameColumn;
  public TableColumn taskTypeColumn;
  public TableColumn taskValueColumn;

  public void init(DataManager dm)
  {
    dm.getAllTasks();
  }

  public void onBackButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("Home");
  }
}
