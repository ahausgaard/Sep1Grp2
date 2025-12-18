package kløverly.presentation.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import kløverly.domain.GreenGoal;
import kløverly.persistence.DataManager;
import kløverly.presentation.core.ControllerConfigurator;

// animations
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GreenPointsViewController implements Initializable {
    @FXML public Label goalTitleLabel;
    @FXML public Label deadlineLabel;
    @FXML public Label GreenPointsDescriptionLabel;
    @FXML public Label pointsLabel;
    @FXML public ProgressBar progressBar;

    private DataManager dm;
    private ScaleTransition celebrationAnimation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dm = ControllerConfigurator.getDataManager();

        setupAnimation();

        startUpdateLoop();
        calculateGreenPoints();
    }

    private void setupAnimation() {
        celebrationAnimation = new ScaleTransition(Duration.millis(600), pointsLabel);
        celebrationAnimation.setByX(0.1);
        celebrationAnimation.setByY(0.1);
        celebrationAnimation.setCycleCount(Animation.INDEFINITE);
        celebrationAnimation.setAutoReverse(true);
    }

    private void startUpdateLoop() {
        // Update every second
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            calculateGreenPoints();
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void calculateGreenPoints() {
        GreenGoal goal = dm.getGreenGoal();

        if (goal != null) {
            goalTitleLabel.setText(goal.getTitle());
            deadlineLabel.setText("Deadline: " + goal.getDeadlineDate());
            GreenPointsDescriptionLabel.setText("Præmie: " + goal.getPrize());

            int current = goal.getCurrentPoints();
            int target = goal.getTargetPoints();

            // Update bar
            if (target > 0) {
                progressBar.setProgress((double) current / target);
            } else {
                progressBar.setProgress(0);
            }

            //Check target
            if (current >= target && target > 0) {
                //  Celebration mode
                progressBar.setStyle("-fx-accent: #FFD700; -fx-control-inner-background: #FFF8E1;");

                pointsLabel.setText("🎉 MÅL OPNÅET! " + goal.getPrize().toUpperCase() + "! 🎉");
                pointsLabel.setStyle("-fx-text-fill: #E65100; -fx-font-weight: bold; -fx-font-size: 16px;");

                if (celebrationAnimation.getStatus() != Animation.Status.RUNNING) {
                    celebrationAnimation.play();
                }

            } else {
                // Normal mode
                pointsLabel.setText("Vi har samlet " + current + " ud af " + target + " point!");

                progressBar.setStyle("-fx-accent: #4caf50;");
                pointsLabel.setStyle("-fx-text-fill: black; -fx-font-size: 12px;");

                celebrationAnimation.stop();
                pointsLabel.setScaleX(1.0);
                pointsLabel.setScaleY(1.0);
            }

        } else {
            goalTitleLabel.setText("Intet grønt mål");
            if (celebrationAnimation != null) celebrationAnimation.stop();
        }
    }



    public void onBackButtonPressed(ActionEvent actionEvent) {
        javafx.scene.Node source = (javafx.scene.Node) actionEvent.getSource();
        javafx.stage.Stage stage = (javafx.stage.Stage) source.getScene().getWindow();
        stage.close();
    }
}