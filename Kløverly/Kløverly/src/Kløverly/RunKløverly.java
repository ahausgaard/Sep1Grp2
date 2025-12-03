package Kløverly;

import javafx.application.Application;
import javafx.stage.Stage;
import Kløverly.persistence.DataManager;
import Kløverly.persistence.ListDataManager;
import Kløverly.presentation.core.ControllerConfigurator;
import Kløverly.presentation.core.ViewManager;

public class RunKløverly extends Application
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
