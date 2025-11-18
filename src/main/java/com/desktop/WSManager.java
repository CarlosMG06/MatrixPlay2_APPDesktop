package com.desktop;

import org.json.JSONObject;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class WSManager {
    private static CtrlConfig ctrlConfig = (CtrlConfig) UtilsViews.getController("ViewConfig");
    public static UtilsWS client;

    public static void connectToServer(String protocol, String host, int port) {
        updateConnectionText("Connecting...", Color.BLACK);
        
        client = UtilsWS.getSharedInstance(protocol + "://" + host + ":" + port);
        client.onMessage(WSManager::deliverToActiveView);
        client.onError(WSManager::handleConnectionError);
    }

     private static void deliverToActiveView(String response) {
        JSONObject msgObj = new JSONObject(response);
        String activeView = UtilsViews.getActiveView();
        MessageListener controller = (MessageListener) UtilsViews.getController(activeView);
        if (controller != null) {
            controller.receiveMessage(msgObj);
            return;
        }
     }
     private static void handleConnectionError(String response) {
        updateConnectionText("Connection refused", Color.RED);
        pauseDuring(1500, () -> {
                ctrlConfig.txtMessage.setText("");
        });
     }

    private static void pauseDuring(long milliseconds, Runnable action) {
        PauseTransition pause = new PauseTransition(Duration.millis(milliseconds));
        pause.setOnFinished(event -> Platform.runLater(action));
        pause.play();
    }

     public static void updateConnectionText(String text, Color color) {
        if (ctrlConfig != null && ctrlConfig.txtMessage != null) {
            ctrlConfig.txtMessage.setTextFill(color);
            ctrlConfig.txtMessage.setText(text);
        }
        pauseDuring(1500, () -> {
            ctrlConfig.txtMessage.setText("");
        });
    }
}
