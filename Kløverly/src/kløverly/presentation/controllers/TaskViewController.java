package kløverly.presentation.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import kløverly.domain.Resident;
import kløverly.domain.Task;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.AcceptsObjectArgument;
import kløverly.presentation.core.ControllerConfigurator;
import kløverly.presentation.core.ViewManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TaskViewController implements Initializable, AcceptsObjectArgument

{
  public Label taskHeaderLabel;
  public Label displayStakeholder;
  public Label displayType;
  public Label displayValue;
  public TextField displayDescription;
  public Button cancelButton;
  public ComboBox<Resident> completerBox;
  private Task selectedTask;

  @Override public void setArgument(Object argument)
  {
    //Set Task received from Task List
    if (argument instanceof Task)
    {
      this.selectedTask = (Task) argument;
      updateTaskDetails();
    }
    else
    {
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

  private void updateTaskDetails()
  {
    if (this.selectedTask != null)
    {
      // Update the UI elements
      taskHeaderLabel.setText("Opgave: " + this.selectedTask.getTitle());
      displayType.setText(this.selectedTask.getType());
      displayDescription.setText(this.selectedTask.getDescription());
      displayValue.setText(String.valueOf(this.selectedTask.getValue()));

    }
    else
    {
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
