package com.desktop;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import javafx.fxml.Initializable;

public class CtrlWaiting implements Initializable {

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    public void receiveMessage(JSONObject messageObj) {
        String type = messageObj.optString("type", "");
        if (type.equals("two_clients_ready")) {
            CtrlCountdown ctrlCountdown = (CtrlCountdown) UtilsViews.getController("ViewCountdown");
            ctrlCountdown.onShow();
            UtilsViews.setView("ViewCountdown");
        }
    }
}
