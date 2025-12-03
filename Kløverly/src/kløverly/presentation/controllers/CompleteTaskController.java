package kløverly.presentation.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import kløverly.domain.Task;
import kløverly.presentation.core.AcceptsObjectArgument;

import java.net.URL;
import java.util.ResourceBundle;

public class CompleteTaskController implements Initializable, AcceptsObjectArgument

{
  public Label taskHeaderLabel;
  private Task selectedTask;

  @Override public void setArgument(Object argument)
  {
    if (argument instanceof Task) {
      this.selectedTask = (Task) argument;
      updateTaskDetails();
    } else {
      this.selectedTask = null;
      taskHeaderLabel.setText("Error: Invalid argument type passed.");
    }
  }

  @Override public void initialize(URL location, ResourceBundle resources)
  {

    //taskHeaderLabel.setLabelFor("Opgave: " + selectedTask.getTitle());
  }

  private void updateTaskDetails() {
    if (this.selectedTask != null) {
      // Print to console (for debugging)
      System.out.println("Loaded Task: " + this.selectedTask.toString());

      // Update the UI element (taskHeaderLabel)
      taskHeaderLabel.setText("Opgave: " + this.selectedTask.getTitle());
    } else {
      // This should only happen if setArgument was called with null
      taskHeaderLabel.setText("Error: No task loaded.");
    }
  }
}
