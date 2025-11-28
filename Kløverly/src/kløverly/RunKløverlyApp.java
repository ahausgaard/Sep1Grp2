package kløverly;

import javafx.application.Application;
import javafx.stage.Stage;
import kløverly.persistence.DataManager;
import kløverly.persistence.ListDataManager;
import kløverly.presentation.core.ControllerConfigurator;
import kløverly.presentation.core.ViewManager;

public class RunKløverlyApp extends Application
{
  @Override public void start(Stage primaryStage) throws Exception
  {
    DataManager dm = new ListDataManager();
    ControllerConfigurator.setDataManager(dm);

    ViewManager.init(primaryStage, "MainView");
    ViewManager.showView("Home");
  }

  public static void main(String[] args)
  {
    launch(args);
  }
}
