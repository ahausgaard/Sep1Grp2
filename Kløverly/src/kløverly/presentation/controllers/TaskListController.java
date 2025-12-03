package kløverly.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import kløverly.domain.CommunityTask;
import kløverly.domain.Task;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.ControllerConfigurator;
import kløverly.presentation.core.ViewManager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TaskListController implements Initializable
{
  public TableView<Task> taskTable;
  public Button completeTaskButton;
  public Button editTaskButton;
  public TableColumn<Task, String> taskNameColumn;
  public TableColumn<Task, String> taskTypeColumn;
  public TableColumn<Task, String> taskValueColumn;

  public void onBackButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("Home");
  }

  public void onCompleteTaskButtonPressed(ActionEvent actionEvent)
  {
    //Open CompleteTaskView and load selectedTask
    Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
    if (selectedTask != null)
    {
      ViewManager.showView("CompleteTask", selectedTask);
    }
    else
    {
      new Alert(Alert.AlertType.ERROR, "No task selected.").show();
    }
  }

  public void onEditTaskButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("EditTask");
  }

  @Override public void initialize(URL location, ResourceBundle resources)
  {
    //Initialize table and get tasks
    taskNameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    taskTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    taskValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
    DataManager dm = ControllerConfigurator.getDataManager();
    List<Task> tasks = dm.getAllTasks();
    taskTable.setEditable(false);
    taskTable.setColumnResizePolicy(
        TableView.CONSTRAINED_RESIZE_POLICY); // Locks columns to fit table width
    taskTable.getColumns().forEach(
        column -> column.setResizable(false)); // Prevent manual resizing

    //Disable buttons when no data is selected
    completeTaskButton.disableProperty()
        .bind(taskTable.getSelectionModel().selectedItemProperty().isNull());

    editTaskButton.disableProperty()
        .bind(taskTable.getSelectionModel().selectedItemProperty().isNull());

    //Populate table
    if (!tasks.isEmpty())
    {
      taskTable.getItems().addAll(tasks);
    }
  }
}


