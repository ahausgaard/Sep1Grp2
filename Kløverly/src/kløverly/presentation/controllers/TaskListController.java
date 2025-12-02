package kløverly.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
  private DataManager dm;
  public TableColumn <Task, String> taskNameColumn;
  public TableColumn <Task, String>taskTypeColumn;
  public TableColumn <Task, String> taskValueColumn;

  public void onBackButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("Home");
  }

  public void onCompleteTaskButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("CompleteTask");
  }

  public void onEditTaskButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("EditTask");
  }

  @Override public void initialize(URL location, ResourceBundle resources)
  {
    taskNameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    taskTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    taskValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
    dm = ControllerConfigurator.getDataManager();
    List<Task> tasks = dm.getAllTasks();
    taskTable.setEditable(false);
    taskTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Locks columns to fit table width
    taskTable.getColumns().forEach(column -> column.setResizable(false)); // Prevent manual resizing


    if (!tasks.isEmpty())
    {
      taskTable.getItems().addAll(tasks);

    }
  }

}
