package kløverly;

import javafx.application.Application;
import javafx.stage.Stage;
import kløverly.domain.CommunityTask;
import kløverly.domain.Task;
import kløverly.persistence.DataManager;
import kløverly.persistence.ListDataManager;
import kløverly.presentation.core.ControllerConfigurator;
import kløverly.presentation.core.ViewManager;

public class RunKløverlyApp extends Application
{
  @Override public void start(Stage primaryStage) throws Exception
  {
    //Initialize datamanager
    DataManager dm = new ListDataManager();
    ControllerConfigurator.setDataManager(dm);

    //Set view and primary stage
    ViewManager.init(primaryStage, "MainView");
    ViewManager.showView("Home");

    dm.getAllTasks();
    dm.getAllResidents();
  }

  public static void main(String[] args)
  {
    launch(args);
  }
}
