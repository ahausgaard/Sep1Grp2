package kløverly.presentation.controllers;

import javafx.scene.control.*;
import kløverly.domain.CommunityTask;
import kløverly.domain.Task;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.ControllerConfigurator;
import kløverly.presentation.core.ViewManager;

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
    public Spinner<Integer> spinner;

    public void initialize()
    {
        dm = ControllerConfigurator.getDataManager();


        //TODO Lav i FX
        choiceBoxDrop.getItems().addAll("Bytteopgave", "FællesOpgave", "Personlig point");

        // Sæt spinnerens værdier
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        spinner.setValueFactory(valueFactory);

        // Opdater label når spinner ændres
        spinner.valueProperty().addListener((obs, oldVal, newVal) -> {
            spinnerInput.setText("Point");
        });


    }
    //PRØVER IGEN GITHUYB
    public void onAddTaskButtonPressed()
    {

        String type = choiceBoxDrop.getValue();
        if (type == null) {
            statusLabel.setText("Du skal vælge en opgavetype først.");
            statusLabel.setStyle("-fx-text-fill: red;"); // rød tekst
            return; // stop metoden her
        }


        //tager input og gemmer variabler
        String name = taskNameInput.getText();
        String description = taskDescriptionInput.getText();
        int value = spinner.getValue();


// choiceboxdrop lave en switch som skal tjekke den value den har og at den registere point i den rigtig task/opgaves type,, Fra

        Task newTask = new CommunityTask(name, type, value, description);
        dm.addTask(newTask);

        // 4️⃣ Feedback til brugeren
        statusLabel.setText("Opgaven blev tilføjet ✔️");
        statusLabel.setStyle("-fx-text-fill: green;");


        taskNameInput.setText("Test");


        //Reset inputs
        taskNameInput.setText("");

        //TODO Lav i fx (slider)
        taskNameInput.setText("");
        taskDescriptionInput.setText("");
        spinner.getValueFactory().setValue(0);
        spinnerInput.setText("Point");
        choiceBoxDrop.setValue(null); // valgfrit: reset ComboBox


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

