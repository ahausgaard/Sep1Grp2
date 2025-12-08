package kløverly.presentation.controllers;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dm = ControllerConfigurator.getDataManager();
        calculateGreenPoints();
    }

    private void calculateGreenPoints() {
        // 1. Hent målet
        GreenGoal goal = dm.getGreenGoal(); // Sørg for at din DataManager har denne metode

        if (goal != null) {
            // 2. Udfyld felterne
            goalTitleLabel.setText(goal.getTitle());
            deadlineLabel.setText("Deadline: " + goal.getDeadlineDate());

            // Brug getPrize() - husk at sikre dig at metoden findes i GreenGoal klassen!
            GreenPointsDescriptionLabel.setText("Præmie: " + goal.getPrize());

            // 3. Hent point
            int current = goal.getCurrentPoints();
            int target = goal.getTargetPoints();

            // Opdater baren (sikrer at den ikke dividerer med 0)
            if (target > 0) {
                progressBar.setProgress((double) current / target);
            } else {
                progressBar.setProgress(0);
            }

            // --- HER STARTER FESTEN (Indsæt dette) ---
            // Vi tjekker om målet er nået (current er større eller lig med target)
            if (current >= target && target > 0) {

                // A. Skift baren til GULD
                progressBar.setStyle("-fx-accent: #FFD700; -fx-control-inner-background: #FFF8E1;");

                // B. Skift teksten til fest-besked
                pointsLabel.setText("🎉 MÅL OPNÅET! " + goal.getPrize().toUpperCase() + "! 🎉");
                pointsLabel.setStyle("-fx-text-fill: #E65100; -fx-font-weight: bold; -fx-font-size: 16px;");

                // C. Start animation (Puls)
                ScaleTransition st = new ScaleTransition(Duration.millis(600), pointsLabel);
                st.setByX(0.1); // Voks 10%
                st.setByY(0.1);
                st.setCycleCount(Animation.INDEFINITE); // Kør for evigt
                st.setAutoReverse(true); // Frem og tilbage
                st.play();

            } else {
                // --- NORMAL TILSTAND (Hvis målet ikke er nået endnu) ---
                pointsLabel.setText("Vi har samlet " + current + " ud af " + target + " point!");

                // Nulstil til normal grøn farve og tekst
                progressBar.setStyle("-fx-accent: #4caf50;");
                pointsLabel.setStyle("-fx-text-fill: black; -fx-font-size: 12px;");
                pointsLabel.setScaleX(1.0); // Stop animation effekter
                pointsLabel.setScaleY(1.0);
            }

        } else {
            // Hvis der ikke er noget mål
            goalTitleLabel.setText("Intet grønt mål");
            deadlineLabel.setText("");
            GreenPointsDescriptionLabel.setText("Bed en admin om at oprette et mål.");
            pointsLabel.setText("0 / 0");
            progressBar.setProgress(0);
        }
    }

    public void onBackButtonPressed(ActionEvent actionEvent) {
        ViewManager.showView("Home");
    }
}