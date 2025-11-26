package Kløverly.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Kløverly.domain.Planet;
import Kløverly.persistence.DataManager;
import Kløverly.presentation.core.ControllerConfigurator;
import Kløverly.presentation.core.ViewManager;

public class AddPlanetController
{
  public TextField planetNameInput;
  public TextField climateDescriptionInput;
  public TextField distanceInput;
  public Label statusLabel;
  public CheckBox hasAtmosphereCheckbox;
  public CheckBox hasLifeCheckBox;
  private DataManager dm;

  public void initialize()
  {
    dm = ControllerConfigurator.getDataManager();
  }

  public void onAddPlanetPressed(ActionEvent actionEvent)
  {
    if(!planetNameInput.getText().isEmpty() && !climateDescriptionInput.getText().isEmpty() && !distanceInput.getText().isEmpty())
    {
      boolean hasAtmosphere = hasAtmosphereCheckbox.isSelected();
      boolean hasLife = hasLifeCheckBox.isSelected();

      Planet planet = new Planet(planetNameInput.getText().trim(), climateDescriptionInput.getText().trim(), Integer.parseInt(distanceInput.getText().trim()), hasLife, hasAtmosphere);
      planetNameInput.clear();
      climateDescriptionInput.clear();
      distanceInput.clear();
      hasAtmosphereCheckbox.setSelected(false);
      hasLifeCheckBox.setSelected(false);
      dm.addPlanet(planet);
      statusLabel.setText("Status: Planet Created.");
      System.out.println(planet);
    }
    else {
      statusLabel.setText("Status: Please fill every field.");
      System.out.println("Please fill every field.");
    }
  }

  public void onCancelPlanetAddPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("Home");
  }
}
