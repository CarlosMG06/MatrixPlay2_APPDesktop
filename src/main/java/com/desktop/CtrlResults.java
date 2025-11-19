package com.desktop;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CtrlResults implements Messages, MessageListener {

    @FXML
    Label labelResults;

    @Override
    public void receiveMessage(JSONObject msgObj) {
    }

}
