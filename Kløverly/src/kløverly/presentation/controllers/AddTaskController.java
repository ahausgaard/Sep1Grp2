package kløverly.presentation.controllers;

import javafx.scene.control.*;
import kløverly.domain.GreenTask;
import kløverly.domain.Task;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.ControllerConfigurator;
import kløverly.presentation.core.ViewManager;
import kløverly.domain.CommunityTask;
import kløverly.domain.SwapTask;
import kløverly.domain.Resident;

import java.util.Optional;

public class AddTaskController
{
  public Label statusLabel;
  public Button addTaskButton;
  public Button cancelButton;
  public TextField taskNameInput;
  public TextField taskDescriptionInput;
  public Label spinnerInput;
  private DataManager dm;
  public ComboBox<String> taskChoiceBox;
  public ComboBox<String> swapTargetBox;
  public Spinner<Integer> spinner;

  public void initialize()
  {
    dm = ControllerConfigurator.getDataManager();

    taskChoiceBox.getItems()
        .addAll("Fællesopgave", "Bytteopgave", "Grøn opgave");

    SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
        -100, 100, 0);
    spinner.setValueFactory(valueFactory);

    taskChoiceBox.getSelectionModel().selectedItemProperty()
        .addListener((obs, oldVal, newVal) -> {
          if ("Bytteopgave".equals(newVal))
          {

            swapTargetBox.setVisible(true);
            swapTargetBox.setManaged(true);
          }
          else
          {

            swapTargetBox.setVisible(false);
            swapTargetBox.setManaged(false);
          }
        });


    if (dm.getAllResidents() != null)
    {

      for (Resident r : dm.getAllResidents())
      {
        swapTargetBox.getItems().add(r.getName());
      }
    }

  }

  public void onAddTaskButtonPressed() {
    String name = taskNameInput.getText();
    String description = taskDescriptionInput.getText();
    int value = spinner.getValue();
    String type = taskChoiceBox.getValue();
    String swapTarget = swapTargetBox.getValue();


    String errorMsg = validateInput(name, type, value, swapTarget);

    if (errorMsg != null) {
      statusLabel.setStyle("-fx-text-fill: red;");
      statusLabel.setText(errorMsg);
      return;
    }


    Task newTask = null;

    switch (type) {
      case "Bytteopgave":

        Resident foundResident = findResidentByName(swapTarget);
        newTask = new SwapTask(name, value, description, foundResident);

        System.out.println("Points checked for: " + swapTarget);
        break;

      case "Grøn opgave":
        newTask = new GreenTask(name, value, description);
        break;

      case "Fællesopgave":
        newTask = new CommunityTask(name, value, description);
        break;

      default:
        statusLabel.setStyle("-fx-text-fill: red;");
        statusLabel.setText("Ukendt opgavetype.");
        return;
    }

    // --- TRIN 3: GEM OG NULSTIL ---
    dm.addTask(newTask);

    statusLabel.setStyle("-fx-text-fill: green;");
    statusLabel.setText("Opgaven blev tilføjet ✔");

    clearInputFields();
    System.out.println(dm.toString());
  }
  private void clearInputFields() {
    taskNameInput.setText("");
    taskDescriptionInput.setText("");
    spinner.getValueFactory().setValue(0);
    taskChoiceBox.setValue("Vælg ny opgave");
    swapTargetBox.setValue("Vælg ny beboer");
  }

  private String validateInput(String name, String type, int value, String swapTarget) {
    if (name == null || name.trim().isEmpty()) {
      return "Udfyld venligst opgavens navn.";
    }

    if (type == null) {
      return "Du skal vælge en opgavetype først.";
    }

    if (type.equals("Bytteopgave")) {
      if (swapTarget == null || swapTarget.equals("Vælg ny beboer")) {
        return "Vælg venligst en beboer.";
      }

      Resident r = findResidentByName(swapTarget);

      if (r == null) {
        return "Kunne ikke finde beboeren i systemet.";
      }

      if (r.getPersonalPointAmount() < value) {
        return r.getName() + " har kun " + r.getPersonalPointAmount() + " point.";
      }
    }

    return null;
  }

  private Resident findResidentByName(String name) {
    for (Resident r : dm.getAllResidents()) {
      if (r.getName().equals(name)) {
        return r;
      }
    }
    return null;
  }

  public void onCancelButtonPressed()
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Bekræft annullering");
    alert.setHeaderText("Er du sikker på, at du vil annullere?");
    alert.setContentText("Alle indtastede data vil gå tabt.");

    ButtonType ja = new ButtonType("Ja");
    ButtonType nej = new ButtonType("Nej", ButtonBar.ButtonData.CANCEL_CLOSE);

    alert.getButtonTypes().setAll(ja, nej);

    Optional<ButtonType> result = alert.showAndWait();

    if (result.isPresent() && result.get() == ja)
    {
      ViewManager.showView("Home");
    }

  }

}

