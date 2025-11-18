package com.desktop;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import org.json.JSONObject;

public class CtrlCountdown implements Initializable, Messages, MessageListener {

    @FXML
    Label labelCountdown, labelName1, labelName2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void receiveMessage(JSONObject msgObj) {
        String type = msgObj.optString(K_TYPE, "");
        if (type.equals(T_COUNTDOWN)) {
            JSONObject value = new JSONObject(msgObj.optString(K_VALUE, ""));
            String player1 = value.optString(V_P1NAME, "");
            String player2 = value.optString(V_P2NAME, "");
            
            int seconds = value.optInt(V_SECONDS);
            
            if (seconds == 5) {
                labelName1.setText(player1);
                labelName2.setText(player2);
                if (Main.clientName == player1) {
                    Main.playerNumber = 1;
                    Main.rivalName = player2;
                } else {
                    Main.playerNumber = 2;
                    Main.rivalName = player1;
                }
            } else if (seconds == 0) {
                Platform.runLater(() -> UtilsViews.setView("ViewGame"));
            }
            labelCountdown.setText(String.valueOf(seconds));

            labelCountdown.setStyle("-fx-font-size: " + (64 + 16 * (5 - seconds)) + "px;");
        }
    }

}
