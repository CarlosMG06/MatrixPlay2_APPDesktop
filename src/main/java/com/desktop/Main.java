package com.desktop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static String clientName = "";
    public static String rivalName = "";
    public static int playerNumber;

    public static void main(String[] args) {

        // Iniciar app JavaFX   
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        try {
            final int windowWidth = 512;
            final int windowHeight = 512;

            UtilsViews.parentContainer.setStyle("-fx-font: 14 arial;");
            UtilsViews.addView(getClass(), "ViewConfig", "/views/viewConfig.fxml"); 
            UtilsViews.addView(getClass(), "ViewWaiting", "/views/viewWaiting.fxml");
            UtilsViews.addView(getClass(), "ViewCountdown", "/views/viewCountdown.fxml");
            UtilsViews.addView(getClass(), "ViewGame", "/views/viewGame.fxml");
            UtilsViews.addView(getClass(), "ViewResults", "/views/viewResults.fxml");
            
            UtilsViews.setView("ViewConfig");

            Scene scene = new Scene(UtilsViews.parentContainer);
            
            stage.setScene(scene);
            stage.onCloseRequestProperty(); // Call close method when closing window
            stage.setTitle("JavaFX - NodeJS");
            stage.setMinWidth(windowWidth);
            stage.setMinHeight(windowHeight);
            stage.show();

            // Add icon only if not Mac
            if (!System.getProperty("os.name").contains("Mac")) {
                Image icon = new Image("file:/icons/icon.png");
                stage.getIcons().add(icon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() { 
        if (WSManager.client != null) {
            WSManager.client.forceExit();
        }
        System.exit(1); // Kill all executor services
    }
}
