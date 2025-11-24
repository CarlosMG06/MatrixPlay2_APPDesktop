package com.desktop;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CtrlResults implements Messages, MessageListener {

    @FXML
    Label labelWinner;

    @FXML
    private void playAgain() {
        UtilsViews.setView("ViewWaiting");
    }
    @FXML
    private void exit() {
        Main main = new Main();
        main.stop();
    }

    @Override
    public void receiveMessage(JSONObject msgObj) {
    }

}
