package kløverly.presentation.controllers;

import javafx.scene.control.*;
import kløverly.domain.CommunityTask;
import kløverly.domain.Task;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.ControllerConfigurator;

public class AddTaskController
{
  public Label statusLabel;
  public Button addTaskButton;
  public Button cancelButton;
  public TextField taskNameInput;
  public TextField taskDescriptionInput;
  public Slider taskValueSlider;
  public Label sliderInput;
  private DataManager dm;
   public ComboBox<String> choiceBoxDrop;

  public void initialize()
  {
    dm = ControllerConfigurator.getDataManager();

    choiceBoxDrop.getItems().addAll("Bytteopgave", "FællesOpgave", "item 3");

      taskValueSlider.valueProperty().addListener((observable, oldValue, newValue) -> {

          sliderInput.setText(Double.toString(newValue.intValue()));


      });

  }
//PRØVER IGEN GITHUYB
  public void onAddTaskButtonPressed()
  {
    //Take input and save in variables
    String name = taskNameInput.getText();
    String description = taskDescriptionInput.getText();
    int value = (int) taskValueSlider.getValue();


    String type = choiceBoxDrop.getValue();



    Task newTask = new CommunityTask(name, type, value, description);
    dm.addTask(newTask);

    taskNameInput.setText("Test");


    //Reset inputs
    taskNameInput.setText("");

    taskDescriptionInput.setText("");
    taskValueSlider.setValue(0);


    System.out.println(dm.toString());

  }

  public void onCancelButtonPressed()
  {

  }




}
