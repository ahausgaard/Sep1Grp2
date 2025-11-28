package kløverly.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kløverly.domain.Task;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.ControllerConfigurator;
import kløverly.presentation.core.ViewManager;

public class AddTaskController
{
  public TextField planetNameInput;
  public TextField climateDescriptionInput;
  public Label statusLabel;
  public Button addTaskButton;
  public Button cancelButton;
  private DataManager dm;

  public void initialize()
  {
    dm = ControllerConfigurator.getDataManager();
  }

  public void onAddTaskButtonPressed()
  {

  }

  public void onCancelButtonPressed()
  {

  }




}
