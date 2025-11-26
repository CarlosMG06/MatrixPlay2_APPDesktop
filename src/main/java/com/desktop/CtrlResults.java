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
        JSONObject msgObj = new JSONObject();
        msgObj.put("type", C_EXIT);
        WSManager.client.safeSend(msgObj.toString());
        Main main = new Main();
        main.stop();
    }

    @Override
    public void receiveMessage(JSONObject msgObj) {
    }

}
