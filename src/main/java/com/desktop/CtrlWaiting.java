package com.desktop;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import javafx.application.Platform;
import javafx.fxml.Initializable;

public class CtrlWaiting implements Initializable, Messages, MessageListener {

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
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
            Platform.runLater(() -> {
                CtrlCountdown ctrlCountdown = (CtrlCountdown) UtilsViews.getController("ViewCountdown");
                ctrlCountdown.labelName1.setText(player1);
                ctrlCountdown.labelName2.setText(player2); 
                UtilsViews.setView("ViewCountdown");
            });
        }
    }
}
