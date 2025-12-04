package kløverly.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import kløverly.domain.Resident;
import kløverly.domain.Task;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.ControllerConfigurator;
import kløverly.presentation.core.ViewManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ResidentListController implements Initializable
{
  public TableView<Resident> residentTable;
  public Button openResidentViewButton;
  public TableColumn<Resident, String> residentNameColumn;
  public TableColumn<Resident, String> residentIdColumn;
  public TableColumn<Resident, String> residentPointsColumn;

  public void onBackButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("Home");
  }

  public void onOpenResidentViewPressed(ActionEvent actionEvent)
  {
    //Open TaskView and load selectedResident
    Resident selectedResident = residentTable.getSelectionModel().getSelectedItem();
    if (selectedResident != null)
    {
      ViewManager.showView("ResidentView", selectedResident);
    }
    else
    {
      new Alert(Alert.AlertType.ERROR, "No resident selected.").show();
    }
  }

  @Override public void initialize(URL location, ResourceBundle resources)
  {
    //Initialize table and get residents
    residentNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    residentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    residentPointsColumn.setCellValueFactory(new PropertyValueFactory<>("personalPointAmount"));

    DataManager dm = ControllerConfigurator.getDataManager();
    List<Resident> residents = dm.getAllResidents();

    residentTable.setEditable(false);
    residentTable.setColumnResizePolicy(
        TableView.CONSTRAINED_RESIZE_POLICY); // Locks columns to fit table width

    residentTable.getColumns().forEach(
        column ->
        {column.setResizable(false);
          column.setReorderable(false);
        }
    );

    //Disable buttons when no data is selected
    openResidentViewButton.disableProperty()
        .bind(residentTable.getSelectionModel().selectedItemProperty().isNull());

    //Populate table
    if (!residents.isEmpty())
    {
      residentTable.getItems().addAll(residents);
    }
  }


}



