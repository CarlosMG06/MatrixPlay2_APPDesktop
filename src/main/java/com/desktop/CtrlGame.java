package com.desktop;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class CtrlGame implements Initializable {

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
    
    public void updateGameData(JSONObject data) {
        display.setDatos(data);
    }
}
