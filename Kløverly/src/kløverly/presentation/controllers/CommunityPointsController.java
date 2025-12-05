package kløverly.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import kløverly.domain.Resident;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.ControllerConfigurator;
import kløverly.presentation.core.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

    public class CommunityPointsController implements Initializable {

        @FXML
        public Label pointsLabel;

        @FXML
        public ProgressBar progressBar;

        private DataManager dm;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            // 1. Få adgang til databasen
            dm = ControllerConfigurator.getDataManager();

            // 2. Beregn point med det samme siden åbner
            calculateCommunityPoints();
        }

        private void calculateCommunityPoints() {
            int totalPoints = dm.getCurrentCommunityPoints();
            int targetGoal = 1000; // Fællesmålet (du kan ændre det til f.eks. 5000)


            // 4. Opdater teksten på skærmen
            pointsLabel.setText("Vi har samlet " + dm.getCurrentCommunityPoints() + " ud af " + targetGoal + " point!");

            // 5. Opdater den grønne bar (skal være et tal mellem 0.0 og 1.0)
            double progress = (double) totalPoints / targetGoal;
            progressBar.setProgress(progress);
        }

        public void onBackButtonPressed(ActionEvent actionEvent) {
            // Gå tilbage til forsiden
            ViewManager.showView("Home");
        }

    }


