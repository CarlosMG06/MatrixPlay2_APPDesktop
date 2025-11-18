package com.desktop;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import javafx.fxml.Initializable;

public class CtrlWaiting implements Initializable, Messages, MessageListener {

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        JSONObject msgObj = new JSONObject();
        msgObj.put("type", C_AWAITING_COUNTDOWN);
        WSManager.client.safeSend(msgObj.toString());
    }

    public void receiveMessage(JSONObject msgObj) {
        String type = msgObj.optString("type", "");
        if (type.equals(T_COUNTDOWN)) {
            UtilsViews.setView("ViewCountdown");
        }
    }
}
