package kløverly.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import kløverly.domain.CommunityTask;
import kløverly.domain.Resident;
import kløverly.domain.Task;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.ControllerConfigurator;

public class AdminController
{
  private DataManager dm;
  public Button testButton;
  public TextField testTextField;

  public void initialize()
  {
    dm = ControllerConfigurator.getDataManager();
  }

  public void onTestButtonPressed(ActionEvent actionEvent)
  {
    String name = testTextField.getText();
    Resident newResident = new Resident(name);

    dm.addResident(newResident);
    System.out.println(dm.toString());
    testTextField.setText("");

  }
}

