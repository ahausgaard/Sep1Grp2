package kløverly.presentation.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import kløverly.domain.CommunityTask;
import kløverly.domain.Task;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.ControllerConfigurator;

public class AddTaskController
{
  public Label statusLabel;
  public Button addTaskButton;
  public Button cancelButton;
  public TextField taskNameInput;
  public TextField taskDescriptionInput;
  public Slider taskValueSlider;
  private DataManager dm;

  public void initialize()
  {
    dm = ControllerConfigurator.getDataManager();
  }

  public void onAddTaskButtonPressed()
  {
    //Take input and save in variables
    String name = taskNameInput.getText();
    String description = taskDescriptionInput.getText();
    int value = (int) taskValueSlider.getValue();

    //New task. Still hardcoded CommunityTask. WIP
    Task newTask = new CommunityTask(name, "Fælles", value, description);
    dm.addTask(newTask);

    //Reset inputs
    taskNameInput.setText("");
    taskDescriptionInput.setText("");
    taskValueSlider.setValue(0);

    System.out.println(dm.toString());

  }

  public void onCancelButtonPressed()
  {

  }




}
