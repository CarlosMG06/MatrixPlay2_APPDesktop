package com.desktop;

import org.json.JSONObject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CtrlResults implements Messages, MessageListener {

    @FXML
    Label labelWinner;

    @FXML
    private void playAgain() {
        JSONObject msgObj = new JSONObject();
        msgObj.put("type", C_PLAY_AGAIN);
        msgObj.put("value", Main.clientName);
        WSManager.client.safeSend(msgObj.toString());
        CtrlWaiting ctrlWaiting = (CtrlWaiting) UtilsViews.getController("ViewWaiting");
        ctrlWaiting.onShow();
        UtilsViews.setView("ViewWaiting");
    }
    @FXML
    private void exit() {
        Main main = new Main();
        main.stop();
    }

    @Override
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
