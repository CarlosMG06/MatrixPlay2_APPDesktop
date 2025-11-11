package com.desktop;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class CtrlCountdown implements Initializable {

    @FXML
    Label labelCountdown;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void onShow() {
        startCountdown();  
    }

    private void startCountdown() {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                for (int i = 3; i >= 0; i--) {
                    final int count = i;
                    Platform.runLater(() -> {
                        if (count == 0) {
                            labelCountdown.setText("GO!");
                        } else {
                            labelCountdown.setText(String.valueOf(count));
                        }
                        labelCountdown.setStyle("-fx-font-size: " + (64 + 16 * (3 - count)) + "px;");
                    });
                    Thread.sleep(1000);
                }
                Platform.runLater(() -> UtilsViews.setView("ViewGame"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
