package Kløverly.presentation.core;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewManager
{
  private static BorderPane mainLayout;
  private final static String fxmlDirectoryPath = "/fxml/";

  public static void init(Stage primaryStage, String initialView) throws IOException
  {
      BorderPane root = FXMLLoader.load(
          ViewManager.class.getResource(fxmlDirectoryPath + initialView + ".fxml")
      );

      mainLayout = root;

      Scene scene = new Scene(root, 900, 600);
      primaryStage.setScene(scene);
      primaryStage.setTitle("Planets! How do they work?");
      primaryStage.show();
  }

  public static void showView(String viewName)
  {
    try
    {
      Parent root = FXMLLoader.load(ViewManager.class.getResource(fxmlDirectoryPath + viewName + ".fxml"));
      mainLayout.setCenter(root);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      Alert error = new Alert(Alert.AlertType.ERROR,
          "Cannot find view: " + viewName);
      error.show();
    }
  }

    public static void showView(String viewName, String argument)
    {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(ViewManager.class.getResource(fxmlDirectoryPath + viewName + ".fxml"));
    try
    {
      Parent root = loader.load();
      AcceptsStringArgument controller = loader.getController();
      controller.setArgument(argument);
      mainLayout.setCenter(root);

    }
    catch (IOException e)
    {
      e.printStackTrace();
      new Alert(Alert.AlertType.ERROR, "Cannot find view: " + viewName).show();
    }
  }
}
