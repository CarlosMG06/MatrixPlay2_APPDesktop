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

    public void receiveMessage(JSONObject messageObj) {
        String type = messageObj.optString("type", "");
        if (type.equals("countdown")) {
            int secondsRemaining = messageObj.optString("secondsRemaining", "");
            if (secondsRemaining == 0) {
                Platform.runLater(() -> UtilsViews.setView("ViewGame"));
            } else {
                labelCountdown.setText(String.valueOf(secondsRemaining));
            }
            labelCountdown.setStyle("-fx-font-size: " + (64 + 16 * (3 - secondsRemaining)) + "px;");
        }
    }

}
