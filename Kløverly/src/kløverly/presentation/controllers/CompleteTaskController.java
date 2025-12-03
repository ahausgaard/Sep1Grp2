package kløverly.presentation.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import kløverly.domain.Task;

import java.net.URL;
import java.util.ResourceBundle;

public class CompleteTaskController implements Initializable
{
  public Label taskHeaderLabel;
  public TaskListController taskListController;
  private Task selectedTask;

  @Override public void initialize(URL location, ResourceBundle resources)
  {
    var selectedTask = taskListController.selectedTask;
    System.out.println(selectedTask.toString());
    //taskHeaderLabel.setLabelFor("Opgave: " + selectedTask.getTitle());
  }
}
