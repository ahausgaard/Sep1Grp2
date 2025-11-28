package kløverly.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import kløverly.presentation.core.ViewManager;

public class MainViewController
{
    public Button addTaskButton;
    public Button viewScoreButton;
    public Button adminButton;

  public void onAddTaskButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("AddTask");
  }

  public void onViewScoreButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("ViewScore");
  }

  public void onAdminButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("Admin");
  }
}
