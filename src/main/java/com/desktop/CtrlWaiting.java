package com.desktop;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class CtrlWaiting implements Initializable, Messages, MessageListener {

    @FXML
    Label labelWaiting;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    public void onShow() {
        String waitingText = "Esperant a l'oponent";
        new Thread(() -> {
            while (UtilsViews.getActiveView().equals("ViewWaiting")) {
                try {
                    for (int i = 0; i <= 3; i++) {
                        final int dots = i;
                        Platform.runLater(() -> {
                            labelWaiting.setText(waitingText + ".".repeat(dots));
                        });
                        Thread.sleep(800);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void receiveMessage(JSONObject msgObj) {
        String type = msgObj.optString("type", "");
        if (type.equals(T_COUNTDOWN)) {
            JSONObject value = new JSONObject(msgObj.optString(K_VALUE, ""));
            String player1 = value.optString(V_P1NAME, "");
            String player2 = value.optString(V_P2NAME, "");
            if (Main.clientName.equals(player1)) {
                Main.playerNumber = 1;
                Main.rivalName = player2;
            } else {
                Main.playerNumber = 2;
                Main.rivalName = player1;
            }
            int seconds = value.optInt(V_SECONDS);
            Platform.runLater(() -> {
                CtrlCountdown ctrlCountdown = (CtrlCountdown) UtilsViews.getController("ViewCountdown");
                ctrlCountdown.labelName1.setText(player1);
                ctrlCountdown.labelName2.setText(player2); 
                ctrlCountdown.labelCountdown.setText(String.valueOf(seconds));

                ctrlCountdown.labelCountdown.setStyle("-fx-font-size: " + (64 + 16 * (5 - seconds)) + "px;");
                UtilsViews.setView("ViewCountdown");
            });
        }
    }
}
