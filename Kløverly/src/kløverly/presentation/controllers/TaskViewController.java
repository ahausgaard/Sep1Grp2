package kløverly.presentation.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
  public Label displayId;
  public Label displayValue;
  public Label displayDescription;
  public Button cancelButton;
  public ComboBox<Resident> completerBox;
  public TextField editDescription;
  public TextField editStakeholder;
  public Spinner<Integer> editValue;
  public Button editTaskButton;
  private Task selectedTask;

  private final BooleanProperty isEditing = new SimpleBooleanProperty(false);

  @Override public void setArgument(Object argument)
  {
    //Set Task received from Task List
    if (argument instanceof Task)
    {
      this.selectedTask = (Task) argument;
      populateFields();
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

    editDescription.visibleProperty().bind(isEditing);
    displayDescription.visibleProperty().bind(isEditing.not());


    displayValue.visibleProperty().bind(isEditing.not());
    editValue.visibleProperty().bind(isEditing);
    editValue.managedProperty().bind(editValue.visibleProperty());

  }

  private void populateFields()
  {
    if (this.selectedTask != null)
    {
      // Update the UI elements
      taskHeaderLabel.setText("Opgave: " + this.selectedTask.getTitle());

      displayId.setText(this.selectedTask.getId());

      displayDescription.setText(this.selectedTask.getDescription());
      editDescription.setText(this.selectedTask.getDescription());

      displayValue.setText(String.valueOf(this.selectedTask.getValue()));
      SpinnerValueFactory<Integer> valueFactory =
          new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100,
              selectedTask.getValue());
      editValue.setValueFactory(valueFactory);
      editValue.setEditable(true);

      displayStakeholder.setText(this.selectedTask.getTitle()); //TODO Fix Stakeholder
    }
    else
    {
      taskHeaderLabel.setText("Error: No task loaded.");
    }
  }

  public void onCancelButtonPressed(ActionEvent actionEvent)
  {
    if (isEditing.get())
    {
      cancelButton.setText("Annullér ændringer");
      populateFields();
      isEditing.set(false);
    }
    else
    {
      ViewManager.showView("TaskList");
    }
  }

  public void onEditTaskButtonPressed(ActionEvent actionEvent)
  {
    boolean currentlyEditing = isEditing.get();

    if (currentlyEditing)
    {
      saveTask();
      editTaskButton.setText("Redigér opgave");
      isEditing.set(false);
    }
    else
    {
    editTaskButton.setText("Gem ændringer");
    isEditing.set(true);
    }
  }

  private void saveTask()
  {
    String newDescription = editDescription.getText();
    selectedTask.setDescription(newDescription);

    int newValue = editValue.getValue();
    selectedTask.setValue(newValue);
    populateFields();
  }

  public void onCompleterBoxPressed(ActionEvent actionEvent)
  {

  }

}
