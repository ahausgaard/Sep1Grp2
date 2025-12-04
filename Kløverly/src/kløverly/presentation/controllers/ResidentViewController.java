package kløverly.presentation.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import kløverly.domain.Resident;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.AcceptsObjectArgument;
import kløverly.presentation.core.ControllerConfigurator;
import kløverly.presentation.core.ViewManager;

import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ResidentViewController implements Initializable, AcceptsObjectArgument

{
  public Label residentHeaderLabel;
  public Label displayId;
  public Label displayPersonalPoints;
  public Label displayActivityLevel;
  public Button cancelButton;
  public Spinner<Integer> editActivityLevel;
  public Spinner<Integer> editPersonalPoints;
  public Button editTaskButton;
  public Button deleteResidentButton;
  private Resident selectedResident;
  private DataManager dm;
  private Alert deletionAlert = new Alert(Alert.AlertType.CONFIRMATION);

  private final BooleanProperty isEditing = new SimpleBooleanProperty(false);

  @Override public void setArgument(Object argument)
  {
    //Set Resident received from Task List
    if (argument instanceof Resident)
    {
      this.selectedResident = (Resident) argument;
      populateFields();
    }
    else
    {
      this.selectedResident = null;
      residentHeaderLabel.setText("Error: Invalid argument type passed.");
    }
  }

  @Override public void initialize(URL location, ResourceBundle resources)
  {
    dm = ControllerConfigurator.getDataManager();
    List<Resident> residents = dm.getAllResidents();
    residents.sort(Comparator.comparing(Resident::getName));

    editActivityLevel.visibleProperty().bind(isEditing);
    displayActivityLevel.visibleProperty().bind(isEditing.not());

    deleteResidentButton.visibleProperty().bind(isEditing);
    displayPersonalPoints.visibleProperty().bind(isEditing.not());
    editPersonalPoints.visibleProperty().bind(isEditing);
    editPersonalPoints.managedProperty().bind(editPersonalPoints.visibleProperty());
  }

  private void populateFields()
  {
    if (this.selectedResident != null)
    {
      // Update the UI elements
      residentHeaderLabel.setText("Beboer: " + this.selectedResident.getName());

      displayId.setText(this.selectedResident.getId());

      displayActivityLevel.setText(String.valueOf(this.selectedResident.getActivityLevel()));
      SpinnerValueFactory<Integer> activityLevelFactory =
          new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100,
              selectedResident.getActivityLevel());
      editActivityLevel.setValueFactory(activityLevelFactory);
      editActivityLevel.setEditable(true);

      displayPersonalPoints.setText(String.valueOf(this.selectedResident.getPersonalPointAmount()));
      SpinnerValueFactory<Integer> personalPointsFactory =
          new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000,
              selectedResident.getPersonalPointAmount());
      editPersonalPoints.setValueFactory(personalPointsFactory);
      editPersonalPoints.setEditable(true);
    }
    else
    {
      residentHeaderLabel.setText("Error: No task loaded.");
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
      saveResident();
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

  private void saveResident()
  {
    int newActivityLevel = editActivityLevel.getValue();
    selectedResident.setActivityLevel(newActivityLevel);

    int newPersonalPoints = editPersonalPoints.getValue();
    selectedResident.setPersonalPointAmount(newPersonalPoints);

    dm.saveData();

    populateFields();

  }

  public void onCompleterBoxPressed(ActionEvent actionEvent)
  {

  }

  public void onFinishTaskButton(ActionEvent actionEvent)
  {
  }

  public void onDeleteResidentButtonPressed(ActionEvent actionEvent)
  {
    //Alert
    deletionAlert.setTitle("Slet beboer");
    deletionAlert.setHeaderText(null);
    deletionAlert.setContentText("Er du sikker på, du vil slette beboer: " + selectedResident.getName());
    ButtonType buttonTypeDelete = new ButtonType("Slet");
    ButtonType buttonTypeCancel = new ButtonType("Annullér");
    deletionAlert.getButtonTypes().setAll(buttonTypeDelete, buttonTypeCancel);

    Optional<ButtonType> result = deletionAlert.showAndWait();

    if(result.isPresent() && result.get() == buttonTypeDelete)
    {
      dm.deleteResident(selectedResident);
      System.out.println("Resident deleted.");
      ViewManager.showView("ResidentList");
    }
    else
    {
      System.out.println("Deletion cancelled.");
    }
  }
}

