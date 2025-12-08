package kløverly.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import kløverly.domain.CommunityGoal;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.ControllerConfigurator;
import kløverly.presentation.core.ViewManager;

import java.net.URL;
import java.util.ResourceBundle;

public class CommunityPointsController implements Initializable {

    // --- Her er alle dine Labels fra designet ---
    @FXML public Label goalTitleLabel;                 // NY
    @FXML public Label deadlineLabel;                  // NY
    @FXML public Label CommunityPointsDescriptionLabel;
    @FXML public Label pointsLabel;
    @FXML public ProgressBar progressBar;

    private DataManager dm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dm = ControllerConfigurator.getDataManager();
        calculateCommunityPoints();
    }

    private void calculateCommunityPoints() {
        // 1. Hent målet
        CommunityGoal goal = dm.getCommunityGoal();

        if (goal != null) {
            // 2. Udfyld alle felterne med data fra Admin-siden
            goalTitleLabel.setText(goal.getTitle());                      // Titel
            deadlineLabel.setText("Deadline: " + goal.getDeadlineDate()); // Deadline
            CommunityPointsDescriptionLabel.setText(goal.getPrize());     // Beskrivelse

            // 3. Point og bar
            int current = goal.getCurrentPoints();
            int target = goal.getTargetPoints();

            pointsLabel.setText("Vi har samlet " + current + " ud af " + target + " point!");

            if (target > 0) {
                progressBar.setProgress((double) current / target);
            }
        } else {
            // Hvis intet mål findes endnu
            goalTitleLabel.setText("Intet fællesmål");
            deadlineLabel.setText("");
            CommunityPointsDescriptionLabel.setText("Bed en admin om at oprette et mål.");
            pointsLabel.setText("0 / 0");
            progressBar.setProgress(0);
        }
    }

    public void onBackButtonPressed(ActionEvent actionEvent) {
        ViewManager.showView("Home");
    }
}