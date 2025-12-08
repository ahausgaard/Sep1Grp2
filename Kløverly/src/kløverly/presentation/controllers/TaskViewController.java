package kløverly.presentation.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import kløverly.domain.GreenGoal;
import kløverly.domain.Resident;
import kløverly.domain.SwapTask;
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
  @FXML public Button finishTaskButton;
  public Button deleteTaskButton;
  public Label stakeholderLabel;

  private Resident stakeholder;
  private Task selectedTask;
  private DataManager dm;
  private Resident completer;
  private final Alert deletionAlert = new Alert(Alert.AlertType.CONFIRMATION);

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

    completerBox.setPromptText("Vælg beboer");
    completerBox.getItems().addAll(residents);

    completerBox.setConverter(new StringConverter<Resident>()
    {
      @Override public String toString(Resident resident)
      {
        if (resident == null)
        {
          return null;
        }
        return resident.getName();
      }

      @Override public Resident fromString(String string)
      {
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
        .bind(completerBox.valueProperty().isNull().or(isEditing));

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
      SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
          0, 100, selectedTask.getValue());
      editValue.setValueFactory(valueFactory);
      editValue.setEditable(true);

      if (selectedTask instanceof SwapTask swapTask)
      {
        stakeholder = swapTask.getStakeholder();
        displayStakeholder.setText(stakeholder.getName());
        stakeholderLabel.setVisible(true);
        completerBox.getItems().remove(swapTask.getStakeholder());
      }
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

  @FXML public void onFinishTaskButtonPressed(ActionEvent actionEvent)
  {
    // 1. Tjek om en beboer er valgt
    completer = completerBox.getValue();
    if (completer == null)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setHeaderText("Mangler beboer");
      alert.setContentText(
          "Du skal vælge en beboer i listen før du kan udføre opgaven.");
      alert.showAndWait();
      return;
    }

    // 2. Bekræftelse - Er du sikker?
    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
    confirm.setTitle("Afslut opgave");
    confirm.setHeaderText("Er du sikker?");
    confirm.setContentText(
        "Vil du afslutte opgaven: " + selectedTask.getTitle() + "?");

    // Hvis brugeren trykker "Annuller", så stop her
    if (confirm.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK)
    {
      return;
    }

    // 3. Fordel point (FØR vi sletter opgaven!)

    String taskType = selectedTask.getType();

    Alert pointRegistrationAlert;

    // Vi bruger en simpel switch til at tjekke typen
    switch (taskType)
    {
      case "GreenTask":
        // --- Grøn opgave ---
        GreenGoal goal = dm.getGreenGoal();
        if (goal != null)
        {
          goal.addPoints(selectedTask.getValue());

          // Vis jubel-besked
          pointRegistrationAlert = new Alert(Alert.AlertType.INFORMATION);
          pointRegistrationAlert.setHeaderText("Fællespoint registreret!");
          pointRegistrationAlert.setContentText("Jubii! Vi har nu " + goal.getCurrentPoints()
              + " point i fællesskabet.");
          pointRegistrationAlert.showAndWait();
        }
        else
        {
          // Hvis der ikke er noget mål endnu
          Alert alert = new Alert(Alert.AlertType.WARNING,
              "Hov! Der er ikke oprettet noget fællesmål endnu.");
          alert.showAndWait();
        }
        break;

      case "SwapTask":
        finishTask();
        stakeholder.setPersonalPointAmount(
            stakeholder.getPersonalPointAmount() - selectedTask.getValue());
        break;

      case "CommunityTask":
        finishTask();
        break;

      default:
        System.out.println("Ingen point-logik for typen: " + taskType);
        break;
    }

    // 4. SLET OPGAVEN OG GEM (Nu hvor pointene er givet)
    dm.deleteTask(selectedTask);
    dm.saveData();

    // 5. Gå tilbage til oversigten
    ViewManager.showView("TaskList");
  }

  private void finishTask()
  {
    Alert pointRegistrationAlert;
    if ((completer.getPersonalPointAmount() + selectedTask.getValue()) < 0)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING,
          "Hov!\n" + completer.getName() + " har ikke råd til at tage opgaven.");
      alert.showAndWait();
      return;
    }

    completer.setPersonalPointAmount(
        completer.getPersonalPointAmount() + selectedTask.getValue());

    pointRegistrationAlert = new Alert(Alert.AlertType.INFORMATION);
    pointRegistrationAlert.setHeaderText("Personlige point registreret");
    pointRegistrationAlert.setContentText(
        completer.getName() + " har fået " + selectedTask.getValue()
            + " point.");
    pointRegistrationAlert.showAndWait();
  }

  public void onDeleteTaskButtonPressed(ActionEvent actionEvent)
  {
    //Alert
    deletionAlert.setTitle("Slet opgave");
    deletionAlert.setHeaderText(null);
    deletionAlert.setContentText(
        "Er du sikker på, du vil slette opgave: " + selectedTask.getTitle());
    ButtonType buttonTypeDelete = new ButtonType("Slet");
    ButtonType buttonTypeCancel = new ButtonType("Annullér");
    deletionAlert.getButtonTypes().setAll(buttonTypeDelete, buttonTypeCancel);

    Optional<ButtonType> result = deletionAlert.showAndWait();

    if (result.isPresent() && result.get() == buttonTypeDelete)
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
