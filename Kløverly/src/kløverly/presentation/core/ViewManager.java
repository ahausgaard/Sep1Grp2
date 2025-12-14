package kløverly.presentation.core;

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
//TODO
      mainLayout = root;

      Scene scene = new Scene(root, 900, 600);
      primaryStage.setScene(scene);
      primaryStage.setTitle("Kløverly App");
      primaryStage.show();

      scene.getStylesheets().add(
              ViewManager.class.getResource(fxmlDirectoryPath + "fx.css").toExternalForm()
      );

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
    // KIG HER DET ER ÅBENT VINDUE TIL POINT OVERSIGT
    // Ny metode til at åbne et separat vindue (popup)
    public static void openWindow(String viewName, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewManager.class.getResource(fxmlDirectoryPath + viewName + ".fxml"));
            Parent root = loader.load();

            Stage stage = new Stage(); // Opretter et nyt vindue
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show(); // Viser vinduet

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  public static void showView(String viewName, Object argument)
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(ViewManager.class.getResource(fxmlDirectoryPath + viewName + ".fxml"));
    try
    {
      Parent root = loader.load();
      Object controller = loader.getController();

      if (controller instanceof AcceptsObjectArgument)
      {
        ((AcceptsObjectArgument) controller).setArgument(argument);
      }
      else
      {
        System.err.println("Controller does not implement AcceptsOnlyArgument or is null for view. " + viewName);
      }

      mainLayout.setCenter(root);

    }
    catch (IOException e)
    {
      e.printStackTrace();
      new Alert(Alert.AlertType.ERROR, "Cannot find view: " + viewName).show();
    }
  }
}
