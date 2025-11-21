package com.desktop;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class CtrlGame implements Initializable, Messages, MessageListener {

    @FXML
    private VBox gameContainer;

    private GameDisplay display;

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
    }

    private GameBar getBarToMove() {
        if (Main.playerNumber == 1) {
            return display.getP1Bar();
        } else {
            return display.getP2Bar();
        }
    }

    public void onShow() {
        barToMove = getBarToMove();

        Scene scene = gameContainer.getScene();
        if (scene != null) {
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.UP) {
                    barToMove.setDirUp();
                }
                if (event.getCode() == KeyCode.DOWN) {
                    barToMove.setDirDown();
                }
            });
            scene.setOnKeyReleased(event -> {
                if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN) {
                    barToMove.setDirStill();
                }
            });
        }

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Moure la barra segons la seva direcció
                switch (barToMove.getDirection()) {
                    case UP:
                        barToMove.setPosY(barToMove.moveUp());
                        break;
                    case DOWN:
                        barToMove.setPosY(barToMove.moveDown());
                        break;
                    case STILL:
                        // No moure
                        break;
                }
                JSONObject msgObj = new JSONObject();
                msgObj.put("type", C_MOVE);
                JSONObject value = new JSONObject();
                value.put(C_NAME, Main.clientName);
                value.put(C_INPUT, barToMove.getPosY());
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
            JSONObject gameData = new JSONObject(msgObj.optString(K_SERVER_GAME_DATA));
            display.setDatos(gameData);
            JSONArray clientArray = new JSONArray(msgObj.optString(K_CLIENTS_LIST));
            for (Object clientData : clientArray) {
                JSONObject client = (JSONObject) clientData;
                String name = client.optString("name");
                if (name.equals(Main.clientName)) {
                    int player = client.optInt("player");
                    Main.playerNumber = player;
                }
            }
            // if (Main.playerNumber == 1) {
            //     posY = gameData.optInt("p1PossY");
            // } else {
            //     posY = gameData.optInt("p2possY");
            // }
        }
    }
}
