package kløverly.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import kløverly.presentation.core.ViewManager;

public class MainViewController
{
  public Button addTaskButton;
  public Button adminButton;
  public Button viewAvailableTasksButton;

  public void onAddTaskButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("AddTask");
  }

  public void onAdminButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("Admin");
  }

  public void onViewAvailableTasksButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("ViewAvailableTasks");
  }
}
