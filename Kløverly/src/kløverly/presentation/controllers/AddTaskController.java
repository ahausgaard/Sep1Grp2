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

    // Sæt spinnerens værdier
    SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
        -100, 100, 0);
    spinner.setValueFactory(valueFactory);

    // Opdater label når spinner ændres
    spinner.valueProperty().addListener((obs, oldVal, newVal) -> {
      spinnerInput.setText("Point");
    });

    // 1. Lyt efter ændringer i opgavetype-boksen
    taskChoiceBox.getSelectionModel().selectedItemProperty()
        .addListener((obs, oldVal, newVal) -> {
          if ("Bytteopgave".equals(newVal))
          {
            // Hvis bytteopgave er valgt: Vis den nye boks
            swapTargetBox.setVisible(true);
            swapTargetBox.setManaged(true);
          }
          else
          {
            // Ellers: Skjul den
            swapTargetBox.setVisible(false);
            swapTargetBox.setManaged(false);
          }
        });

    // Vi tjekker om listen er null for at undgå fejl
    if (dm.getAllResidents() != null)
    {
      // Vi bruger 'getAllResidents()' som er det rigtige navn i din DataManager
      for (Resident r : dm.getAllResidents())
      {
        swapTargetBox.getItems().add(r.getName());
      }
    }

  }

  public void onAddTaskButtonPressed()
  {

    String name = taskNameInput.getText();
    String description = taskDescriptionInput.getText();
    int value = spinner.getValue();
    String type = taskChoiceBox.getValue();
    Task newTask;

    // Validering af opgavetype
    if (type == null)
    {
      statusLabel.setStyle("-fx-text-fill: red;");
      statusLabel.setText("Du skal vælge en opgavetype først.");
      return;
    }

    switch (type)
    {
      case "Bytteopgave":
        // 1. Hent navnet på den valgte beboer
        String selectedName = swapTargetBox.getValue();

        // Tjek om brugeren har glemt at vælge en
        if (selectedName == null)
        {
          statusLabel.setStyle("-fx-text-fill: red;");
          statusLabel.setText("Vælg venligst en beboer.");
          return;
        }

        // 2. Find den rigtige Resident i systemet
        Resident foundResident = null;

        // HER ER RETTELSEN: Vi bruger nu dm.getAllResidents()
        for (Resident r : dm.getAllResidents())
        {
          if (r.getName().equals(selectedName))
          {
            foundResident = r;
            break; // Stop løkken når vi har fundet personen
          }
        }

        // 3. Hvis beboeren findes: Opdater point og opret opgave
        if (foundResident != null)
        {
          // Hent nuværende point
          int currentPoints = foundResident.getPersonalPointAmount();

          if (currentPoints < value)
          {
            statusLabel.setStyle("-fx-text-fill: red;");
            statusLabel.setText(
                foundResident.getName() + " Har kun " + currentPoints
                    + " Point ");
            return;
          }

          // Opret opgaven med beboer-objektet
          newTask = new SwapTask(name, value, description, foundResident);

          // (Valgfrit) Print til konsollen for at teste
          System.out.println(
              "Point opdateret for " + selectedName + ". Nye point: " + (
                  currentPoints + value));
        }
        else
        {
          // Sikkerhedsnet hvis noget går galt
          statusLabel.setStyle("-fx-text-fill: red;");
          statusLabel.setText("Kunne ikke finde beboeren i systemet.");
          return;
        }
        break;

      case "Grøn opgave":
        newTask = new GreenTask(name, value, description);
        break;

      case "Fællesopgave":
        newTask = new CommunityTask(name, value, description);
        break;

      default:
        statusLabel.setStyle("-fx-text-fill: red;");
        statusLabel.setText("Ukendt opgavetype: " + type);
        return;
    }

    dm.addTask(newTask);
    statusLabel.setStyle("-fx-text-fill: green;");
    statusLabel.setText("Opgaven blev tilføjet ✔");

    taskNameInput.setText("Test");


    taskNameInput.setText("");
    taskDescriptionInput.setText("");
    spinner.getValueFactory().setValue(0);
    spinnerInput.setText("Point");
    taskChoiceBox.setValue("Vælg ny opgave");
    swapTargetBox.setValue("Vælg ny beboer");

    System.out.println(dm.toString());

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

