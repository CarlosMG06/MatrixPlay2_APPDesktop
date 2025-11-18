package com.desktop;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class CtrlGame implements Initializable, Messages, MessageListener {

    @FXML
    private VBox gameContainer;

    private GameDisplay display;

    private Boolean KEY_UP = false;
    private Boolean KEY_DOWN = false;
    private GameBar barToMove;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        display = new GameDisplay(512, 512);
        gameContainer.getChildren().add(display);

        gameContainer.widthProperty().addListener((obs, oldVal, newVal) -> {
            display.setWidth(newVal.doubleValue());
        });
        gameContainer.heightProperty().addListener((obs, oldVal, newVal) -> {
            display.setHeight(newVal.doubleValue());
        });

        gameContainer.setOnKeyPressed(event -> {
            System.out.println("\n\n\nsfdsdf entro aca \n\n\n");
            if (event.getCode().equals(KeyCode.UP)) KEY_UP = true;
            if (event.getCode().equals(KeyCode.DOWN)) KEY_DOWN = true;
        });
        gameContainer.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.UP)) KEY_UP = false;
            if (event.getCode().equals(KeyCode.DOWN)) KEY_DOWN = false;
        });
        gameContainer.setFocusTraversable(true);

    }

    private GameBar getBarToMove() {
        if (Main.playerNumber == 1) {
            return display.getP1Bar();
        } else {
            return display.getP2Bar();
        }
    }

    public void onShow() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                barToMove = getBarToMove();
                int newPosY = barToMove.getPosY();
                if (KEY_UP) {
                    newPosY = barToMove.moveUp();
                }
                if (KEY_DOWN) {
                    newPosY = barToMove.moveDown();
                }
                JSONObject msgObj = new JSONObject();
                msgObj.put("type", C_MOVE);
                JSONObject value = new JSONObject();
                value.put(C_NAME, Main.clientName);
                value.put(C_INPUT, newPosY);
                msgObj.put("value", value);
                WSManager.client.safeSend(msgObj.toString());
            }
        };
        timer.start();
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
