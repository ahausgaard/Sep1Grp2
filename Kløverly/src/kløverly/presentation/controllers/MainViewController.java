package kløverly.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import kløverly.presentation.core.ViewManager;

public class MainViewController
{
  public Button adminButton;
  public Button addTaskViewButton;
  public Button taskListViewButton;
  public Button residentListButton;

  public void onAddTaskViewButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("AddTask");
  }

  public void onTaskListViewButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("TaskList");
  }

  public void onAdminButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("Admin");
  }

  public void onResidentListButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("ResidentList");
  }

  public void onCommunityPointsButtonPressed(ActionEvent event) {
      ViewManager.showView("CommunityPoints");
  }
}
