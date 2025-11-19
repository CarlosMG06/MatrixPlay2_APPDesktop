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
    public Label labelCountdown, labelName1, labelName2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void receiveMessage(JSONObject msgObj) {
        String type = msgObj.optString(K_TYPE, "");
        if (type.equals(T_COUNTDOWN)) {
            JSONObject value = new JSONObject(msgObj.optString(K_VALUE, ""));
            int seconds = value.optInt(V_SECONDS);
            if (seconds == 0) {
                Platform.runLater(() -> UtilsViews.setView("ViewGame"));
                CtrlGame ctrlGame = (CtrlGame) UtilsViews.getController("ViewGame");
                ctrlGame.onShow();
                msgObj = new JSONObject();
                msgObj.put("type", C_READY_STARTGAME);
                WSManager.client.safeSend(msgObj.toString());
            }
            Platform.runLater(() -> {
                labelCountdown.setText(String.valueOf(seconds));

                labelCountdown.setStyle("-fx-font-size: " + (64 + 16 * (5 - seconds)) + "px;");
            });
        }
    }

}
