package kløverly.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import kløverly.domain.CommunityGoal;
import kløverly.domain.Resident;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.ControllerConfigurator;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminController implements Initializable
{
  public TextField goalTitleField;
  public DatePicker deadlineInput;
  public Spinner<Integer> targetInput;
  public Button addResidentButton;
  public TextField residentNameField;
  public Button addGoalButton;
  private DataManager dm;

  @Override public void initialize(URL location, ResourceBundle resources)
  {
    addResidentButton.disableProperty()
        .bind(residentNameField.textProperty().isEmpty());

    addGoalButton.disableProperty()
        .bind(goalTitleField.textProperty().isEmpty().or(deadlineInput.valueProperty().isNull()).or(targetInput.getEditor().textProperty().isEmpty()));

    SpinnerValueFactory<Integer> targetValueFactory =
        new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10000, 1000);
    targetInput.setValueFactory(targetValueFactory);

    dm = ControllerConfigurator.getDataManager();
  }

  public void onAddResidentButtonPressed(ActionEvent actionEvent)
  {
    String name = residentNameField.getText().trim();
    Resident newResident = new Resident(name);

    dm.addResident(newResident);
    System.out.println(dm.toString());
    residentNameField.setText("");
  }

    public void onAddGoalButtonPressed(ActionEvent actionEvent)
  {
    String goalTitle = goalTitleField.getText();
    LocalDate deadline = deadlineInput.getValue();
    int target = targetInput.getValue();
    String prize = "";

    CommunityGoal communityGoal = new CommunityGoal(goalTitle, target, deadline, prize);
    dm.setGoal(communityGoal);

    deadlineInput.setValue(LocalDate.now());
    goalTitleField.setText("");
  }
}

