package com.desktop;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class CtrlGame implements Initializable, Messages, MessageListener {

    @FXML
    private VBox gameContainer;

    private GameDisplay display;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        display = new GameDisplay(576, 576);
        gameContainer.getChildren().add(display);

        gameContainer.widthProperty().addListener((obs, oldVal, newVal) -> {
            display.setWidth(newVal.doubleValue());
        });
        gameContainer.heightProperty().addListener((obs, oldVal, newVal) -> {
            display.setHeight(newVal.doubleValue());
        });
    }

    @Override
    public void receiveMessage(JSONObject msgObj) {
        String type = msgObj.optString(K_TYPE, "");
        if (type.equals(T_SERVER_DATA)) {
            JSONObject data = new JSONObject(msgObj.optString(K_SERVER_GAME_DATA));
            display.setDatos(data);
        }
    }
}
