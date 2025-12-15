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
import kløverly.presentation.core.ViewManager;
// animationer til fællespoint
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
        celebrationAnimation.setByX(0.1); // Voks 10%
        celebrationAnimation.setByY(0.1);
        celebrationAnimation.setCycleCount(Animation.INDEFINITE);
        celebrationAnimation.setAutoReverse(true);
    }

    private void startUpdateLoop() {
        // Opdater hvert sekund (Duration.seconds(1))
        // Hvis du vil have det hurtigere/glattere, brug Duration.millis(100)
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            calculateGreenPoints();
        }));

        timeline.setCycleCount(Timeline.INDEFINITE); // Kør for evigt
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

            // Opdater baren
            if (target > 0) {
                progressBar.setProgress((double) current / target);
            } else {
                progressBar.setProgress(0);
            }

            // --- TJEK OM MÅLET ER NÅET ---
            if (current >= target && target > 0) {
                // A. Guld Bar
                progressBar.setStyle("-fx-accent: #FFD700; -fx-control-inner-background: #FFF8E1;");

                // B. Fest-tekst
                pointsLabel.setText("🎉 MÅL OPNÅET! " + goal.getPrize().toUpperCase() + "! 🎉");
                pointsLabel.setStyle("-fx-text-fill: #E65100; -fx-font-weight: bold; -fx-font-size: 16px;");

                // C. Start animation (KUN hvis den ikke allerede kører)
                if (celebrationAnimation.getStatus() != Animation.Status.RUNNING) {
                    celebrationAnimation.play();
                }

            } else {
                // --- NORMAL TILSTAND ---
                pointsLabel.setText("Vi har samlet " + current + " ud af " + target + " point!");

                // Nulstil til normal
                progressBar.setStyle("-fx-accent: #4caf50;");
                pointsLabel.setStyle("-fx-text-fill: black; -fx-font-size: 12px;");

                // Stop animation og nulstil størrelse, hvis vi pludselig mister point
                celebrationAnimation.stop();
                pointsLabel.setScaleX(1.0);
                pointsLabel.setScaleY(1.0);
            }

        } else {
            // Håndtering af manglende mål...
            goalTitleLabel.setText("Intet grønt mål");
            // ... (resten af din nulstillings-kode)
            if (celebrationAnimation != null) celebrationAnimation.stop();
        }
    }



    public void onBackButtonPressed(ActionEvent actionEvent) {
        // Hent knappen
        javafx.scene.Node source = (javafx.scene.Node) actionEvent.getSource();
        // Find vinduet
        javafx.stage.Stage stage = (javafx.stage.Stage) source.getScene().getWindow();
        // Luk vinduet
        stage.close();
    }
}