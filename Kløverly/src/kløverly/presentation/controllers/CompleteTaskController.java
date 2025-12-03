package kløverly.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import kløverly.domain.Resident;
import kløverly.domain.Task;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.AcceptsObjectArgument;
import kløverly.presentation.core.ControllerConfigurator;
import kløverly.presentation.core.ViewManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CompleteTaskController implements Initializable, AcceptsObjectArgument

{
  public Label taskHeaderLabel;
  public Label displayStakeholder;
  public Label displayType;
  public Label displayValue;
  public Label displayDescription;
  public Button cancelButton;
  public ComboBox completerBox;
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
    DataManager dm = ControllerConfigurator.getDataManager();
    List<Resident> residents = dm.getAllResidents();
    completerBox.getItems().addAll(residents);
  }




  private void updateTaskDetails() {
    if (this.selectedTask != null) {
      // Print to console (for debugging)
      System.out.println("Loaded Task: " + this.selectedTask.toString());

      // Update the UI element (taskHeaderLabel)
      taskHeaderLabel.setText("Opgave: " + this.selectedTask.getTitle());
      displayType.setText(this.selectedTask.getType());
      displayDescription.setText(this.selectedTask.getDescription());
      displayValue.setText(String.valueOf(this.selectedTask.getValue()));

    } else {
      // This should only happen if setArgument was called with null
      taskHeaderLabel.setText("Error: No task loaded.");
    }
  }

  public void onCancelButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("TaskList");
  }

  public void onCompleterBoxPressed(ActionEvent actionEvent)
  {

  }
}
