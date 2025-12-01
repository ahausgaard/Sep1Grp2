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
  public Button chooseTaskButton;
  public Button completeTaskButton;
  public Button editTaskButton;
  private DataManager dm;
  public TableColumn <Task, String> taskNameColumn;
  public TableColumn <Task, String>taskTypeColumn;
  public TableColumn <Task, String> taskValueColumn;

  /*public void init(DataManager dm)
  {
    dm.getAllTasks();
    System.out.println(dm.getAllTasks());
  }*/

  public void showTasks()
  {
    List<Task> tasks = dm.getAllTasks();
    tasks.forEach(System.out::println);

  }

  public void onBackButtonPressed(ActionEvent actionEvent)
  {
    ViewManager.showView("Home");
  }
  public void onChooseTaskButtonPressed(ActionEvent actionEvent)
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

    if (!tasks.isEmpty())
    {
      taskTable.getItems().addAll(tasks);

    }
  }

  public void onCompleteTaskButtonPressed(ActionEvent actionEvent)
  {
  }

  public void onEditTaskButtonPressed(ActionEvent actionEvent)
  {
  }
}
