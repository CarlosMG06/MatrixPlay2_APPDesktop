package com.desktop;

public class GameBall {
    private final int RADIUS;
    private double posX;
    private double posY;

    public GameBall(double posX, double posY, int RADIUS) {
        this.posX = posX;
        this.posY = posY;
        this.RADIUS = RADIUS;
    }

    public double getPosX() {
        return posX;
    }
    public double getPosY() {
        return posY;
    }
    public void setPos(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getRadius() {
        return RADIUS;
    }
}
