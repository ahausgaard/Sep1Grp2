package kløverly.presentation.controllers;

import javafx.scene.control.*;
import kløverly.domain.CommunityTask;
import kløverly.domain.Task;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.ControllerConfigurator;
import kløverly.presentation.core.ViewManager;
import kløverly.domain.GreenTask;
import kløverly.domain.SwapTask;


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
    public ComboBox<String> choiceBoxDrop;
    public ComboBox<String> swapTargetBox;
    public Spinner<Integer> spinner;

    public void initialize()
    {
        dm = ControllerConfigurator.getDataManager();


        //TODO Lav i FX
        choiceBoxDrop.getItems().addAll("Bytteopgave", "FællesOpgave", "PersonligOpgaver");

        // Sæt spinnerens værdier
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        spinner.setValueFactory(valueFactory);

        // Opdater label når spinner ændres
        spinner.valueProperty().addListener((obs, oldVal, newVal) -> {
            spinnerInput.setText("Point");
        });

        // 1. Lyt efter ændringer i opgavetype-boksen
        choiceBoxDrop.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if ("Bytteopgave".equals(newVal)) {
                // Hvis bytteopgave er valgt: Vis den nye boks
                swapTargetBox.setVisible(true);
                swapTargetBox.setManaged(true);
            } else {
                // Ellers: Skjul den
                swapTargetBox.setVisible(false);
                swapTargetBox.setManaged(false);
            }
        });

        swapTargetBox.getItems().addAll("Johan Larsen", "Tarik Maarouf", "Donna", "Flemming");


    }
    //PRØVER IGEN GITHUYB
    public void onAddTaskButtonPressed()
    {

        String name = taskNameInput.getText();
        String description = taskDescriptionInput.getText();
        int value = spinner.getValue();
        String type = choiceBoxDrop.getValue();

        // Validering af opgavetype
        if (type == null) {
            statusLabel.setText("Du skal vælge en opgavetype først.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        Task newTask;


        switch (type) {
            case "Bytteopgave":
                String valgtBeboer = swapTargetBox.getValue();
                if (valgtBeboer == null) {
                    statusLabel.setText("Husk at vælge en beboer til bytteopgaven!");
                    statusLabel.setStyle("-fx-text-fill: red;");
                    return; // Stop hvis ingen beboer er valgt
                }

                newTask = new SwapTask(name, value, description);
                break;

            case "FællesOpgave":
                newTask = new CommunityTask(name, value, description);
                break;

            case "PersonligOpgaver":
                newTask = new GreenTask(name, value, description);
                break;

            default:
                statusLabel.setText("Ukendt opgavetype: " + type);
                statusLabel.setStyle("-fx-text-fill: red;");
                return;
        }

        dm.addTask(newTask);

        statusLabel.setText("Opgaven blev tilføjet ✔️");
        statusLabel.setStyle("-fx-text-fill: green;");


        taskNameInput.setText("Test");

        //TODO Lav i fx (slider)
        taskNameInput.setText("");
        taskDescriptionInput.setText("");
        spinner.getValueFactory().setValue(0);
        spinnerInput.setText("Point");
        choiceBoxDrop.setValue("Vælge nye opgave");


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

        if (result.isPresent() && result.get() == ja) {
            ViewManager.showView("Home");
        }

    }

}

