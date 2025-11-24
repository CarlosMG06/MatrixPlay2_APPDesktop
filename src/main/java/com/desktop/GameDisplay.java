package com.desktop;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.json.JSONObject;

import com.desktop.GameBar.Direction;

public class GameDisplay extends Canvas {
    private final Font pixelTypeface;
    private final GraphicsContext gc;
    
    private int p1Points = 0;
    private int p2Points = 0;
    
    private double scaleX = 1.0;
    private double scaleY = 1.0;
    
    private GameBar p1Bar;
    private GameBar p2Bar;
    private GameBall ball;
    
    private final double recWidth = 3.0;
    private final double recHeight = 16.0;
    private final double ballSize = 2.0;

    public GameDisplay(double width, double height) {
        super(width, height);
        this.gc = getGraphicsContext2D();
        
        this.pixelTypeface = Font.loadFont(getClass().getResourceAsStream("/fonts/m6x11.ttf"), 15);
        
        p1Bar = new GameBar(0, 32, (int)recWidth, (int)recHeight, Direction.STILL);
        p2Bar = new GameBar(61, 32, (int)recWidth, (int)recHeight, Direction.STILL);
        ball = new GameBall(0, 0, (int)ballSize);
        
        widthProperty().addListener((obs, oldVal, newVal) -> onSizeChanged());
        heightProperty().addListener((obs, oldVal, newVal) -> onSizeChanged());
        
        onSizeChanged();
    }

    private void onSizeChanged() {
        double w = getWidth();
        double h = getHeight();
        scaleX = w / 64.0;
        scaleY = h / 64.0;

        double scaledRecWidth = recWidth * scaleX;
        double scaledRecHeight = recHeight * scaleY;
        double scaledBallSize = ballSize * scaleX;

        p1Bar = new GameBar(0, 0, (int)scaledRecWidth, (int)scaledRecHeight, Direction.STILL);
        p2Bar = new GameBar(61, 0, (int)scaledRecWidth, (int)scaledRecHeight, Direction.STILL);
        
        ball = new GameBall(0, 0, (int)scaledBallSize);

        draw();
    }

    private void drawPoints() {
        gc.setFill(Color.WHITE);
        gc.setFont(pixelTypeface);
        gc.setFont(Font.font(gc.getFont().getFamily(), 15 * scaleX));

        // p1 points
        gc.fillText(
            String.valueOf(p1Points),
            getWidth() * 0.25,
            getHeight() * 0.2
        );

        // p2 points
        gc.fillText(
            String.valueOf(p2Points),
            getWidth() * 0.65,
            getHeight() * 0.2
        );
    }

    private void drawRects() {
        gc.setFill(Color.web("#50589C"));

        gc.fillRect(
            p1Bar.getPosX() * scaleX,
            p1Bar.getPosY() * scaleY,
            p1Bar.getWidth(),
            p1Bar.getHeight()
        );
        
        gc.fillRect(
            p2Bar.getPosX() * scaleX,
            p2Bar.getPosY() * scaleY,
            p2Bar.getWidth(),
            p2Bar.getHeight()
        );
    }

    private void drawBall() {
        gc.setFill(Color.web("#3C467B"));
        double ballRadius = ball.getRadius();
        gc.fillRect(
            ball.getPosX() * scaleX,
            ball.getPosY() * scaleY,
            ballRadius,
            ballRadius
        );
    }

    private void drawWhiteLine() {
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(1);

        gc.strokeLine(
            getWidth() * 0.5,
            0,
            getWidth() * 0.5,
            getHeight()
        );
    }

    public void draw() {
        gc.clearRect(0, 0, getWidth(), getHeight());
        
        gc.setFill(Color.web("#636CCB"));
        gc.fillRect(0, 0, getWidth(), getHeight());
        
        drawPoints();
        drawBall();
        drawRects();
        drawWhiteLine();
    }

    public void setDatos(JSONObject json) {
        // Actualizar posiciones de las barras
        p1Bar.setPosY(json.optInt("p1PossY"));
        p2Bar.setPosY(json.optInt("p2PossY"));

        // Actualizar posición de la bola
        ball.setPos(
            json.optDouble("ballX"),
            json.optDouble("ballY")
        );

        p1Points = json.optInt("p1Points");
        p2Points = json.optInt("p2Points");

        draw();
    }
    
    public GameBar getP1Bar() {
        return p1Bar;
    }
    
    public GameBar getP2Bar() {
        return p2Bar;
    }
}