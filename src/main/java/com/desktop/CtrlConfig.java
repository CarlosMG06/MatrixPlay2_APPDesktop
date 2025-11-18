package com.desktop;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class CtrlConfig implements Initializable, Messages, MessageListener {

    @FXML
    public TextField txtName;

    @FXML
    public TextField txtHost;

    @FXML
    public Label txtMessage;

    private String protocol;
    private String host;
    private int port;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void connectToServer() {
        protocol = "wss";
        host = txtHost.getText();
        port = 443;
        WSManager.connectToServer(protocol, host, port);
    }

    @Override
    public void receiveMessage(JSONObject msgObj) {
        String type = msgObj.optString(K_TYPE, "");
        switch (type) {
            case T_GET_NAME:
                Main.clientName = txtName.getText();
                msgObj = new JSONObject();
                msgObj.put(K_TYPE, C_CHECK_NAME);
                msgObj.put(K_VALUE, Main.clientName);
                WSManager.client.safeSend(msgObj.toString());
            case T_CHECK_NAME_STATUS:
                String status = msgObj.optString(K_VALUE, "");
                if (status == V_NAME_AVAILABLE) {
                    msgObj = new JSONObject();
                    msgObj.put("type", C_AWAITING_COUNTDOWN);
                    WSManager.client.safeSend(msgObj.toString());
                    UtilsViews.setViewAnimating("ViewWaiting");
                } else if (status == V_NAME_USED) {
                    WSManager.updateConnectionText("Name already in use", Color.RED);
                }
        }
    }
}