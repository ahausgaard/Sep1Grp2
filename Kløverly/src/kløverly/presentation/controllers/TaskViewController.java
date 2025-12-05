package kløverly.presentation.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import kløverly.domain.Resident;
import kløverly.domain.Task;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.AcceptsObjectArgument;
import kløverly.presentation.core.ControllerConfigurator;
import kløverly.presentation.core.ViewManager;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
  public Button finishTaskButton;
  public Button deleteTaskButton;
  private Task selectedTask;
  private DataManager dm;
  private Resident completer;
  private Alert deletionAlert = new Alert(Alert.AlertType.CONFIRMATION);

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
    dm = ControllerConfigurator.getDataManager();
    List<Resident> residents = dm.getAllResidents();
    residents.sort(Comparator.comparing(Resident::getName));

    completerBox.getItems().addAll(residents);

    completerBox.setConverter(new StringConverter<Resident>() {
      @Override
      public String toString(Resident resident) {
        if (resident == null) {
          return null;
        }

        return resident.getName();
      }

      @Override
      public Resident fromString(String string) {
        return null;
      }
    });


    editDescription.visibleProperty().bind(isEditing);
    displayDescription.visibleProperty().bind(isEditing.not());

    deleteTaskButton.visibleProperty().bind(isEditing);
    displayValue.visibleProperty().bind(isEditing.not());
    editValue.visibleProperty().bind(isEditing);
    editValue.managedProperty().bind(editValue.visibleProperty());

    finishTaskButton.disableProperty()
        .bind(completerBox.valueProperty().isNull().or(isEditing)); //TODO || selectedTask.getValue() > completer.getPersonalPointAmount
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
      cancelButton.setText("Tilbage");
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
      cancelButton.setText("Tilbage");
      isEditing.set(false);
    }
    else
    {
    editTaskButton.setText("Gem ændringer");
    cancelButton.setText("Annullér");
    isEditing.set(true);
    }
  }

  private void saveTask()
  {
    String newDescription = editDescription.getText();
    selectedTask.setDescription(newDescription);

    int newValue = editValue.getValue();
    selectedTask.setValue(newValue);

    dm.saveData();

    populateFields();

  }

  public void onFinishTaskButtonPressed(ActionEvent actionEvent)
  {
    System.out.println(selectedTask.getType());
      switch(selectedTask.getType()) {
          case "CommunityTask" -> {
              System.out.println("COMMUNITYYYY");
              // Her kan du evt. lave logik for fællesopgaver senere
          }
          case "SwapTask" ->
          {
              Resident completer = completerBox.getValue();

              if (completer != null) {
                  // 1. Giv point
                  int points = selectedTask.getValue();
                  int currentPoints = completer.getPersonalPointAmount();
                  completer.setPersonalPointAmount(currentPoints + points);

                  // 2. Gem data
                  dm.saveData();

                  // 3. VIS SUCCES-BESKED (Så du ved det virkede)
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Opgaver udført");
                  alert.setHeaderText("Point tildelt!");
                  alert.setContentText(completer.getName() + " har nu fået " + points + " point.");
                  alert.showAndWait();

                  // 4. Gå tilbage til listen (frivilligt - du kan slette linjen hvis du vil blive)
                  ViewManager.showView("TaskList");

              } else {
                  // 4. VIS FEJL HVIS INGEN ER VALGT
                  Alert alert = new Alert(Alert.AlertType.WARNING);
                  alert.setTitle("Mangler info");
                  alert.setHeaderText("Ingen beboer valgt");
                  alert.setContentText("Du skal vælge en beboer i listen ved siden af knappen.");
                  alert.showAndWait();
              }
          }
      }
      }

  public void onDeleteTaskButtonPressed(ActionEvent actionEvent)
  {
    //Alert
    deletionAlert.setTitle("Slet opgave");
    deletionAlert.setHeaderText(null);
    deletionAlert.setContentText("Er du sikker på, du vil slette opgave: " + selectedTask.getTitle());
    ButtonType buttonTypeDelete = new ButtonType("Slet");
    ButtonType buttonTypeCancel = new ButtonType("Annullér");
    deletionAlert.getButtonTypes().setAll(buttonTypeDelete, buttonTypeCancel);

    Optional<ButtonType> result = deletionAlert.showAndWait();

    if(result.isPresent() && result.get() == buttonTypeDelete)
    {
      dm.deleteTask(selectedTask);
      System.out.println("Task deleted.");
      ViewManager.showView("TaskList");
    }
    else
    {
      System.out.println("Deletion cancelled.");
    }
  }
}
