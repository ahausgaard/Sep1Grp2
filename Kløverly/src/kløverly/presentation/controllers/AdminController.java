package kløverly.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import kløverly.domain.CommunityTask;
import kløverly.domain.Resident;
import kløverly.domain.Task;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.ControllerConfigurator;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable
{
  private DataManager dm;
  public Button testButton;
  public TextField testTextField;

  @Override public void initialize(URL location, ResourceBundle resources)
  {
    testButton.disableProperty()
        .bind(testTextField.textProperty().isEmpty());

    dm = ControllerConfigurator.getDataManager();
  }

  public void onTestButtonPressed(ActionEvent actionEvent)
  {
    String name = testTextField.getText().trim();
    Resident newResident = new Resident(name);

    dm.addResident(newResident);
    System.out.println(dm.toString());
    testTextField.setText("");
  }


}

