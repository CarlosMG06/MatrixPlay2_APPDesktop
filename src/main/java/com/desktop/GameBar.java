package com.desktop;

public class GameBar {
    private final double POS_X;
    private double posY;
    private int width;
    private final int THICKNESS;
    // private final int MOVE_SPEED;

    public GameBar(double posX, double posY, int width, int THICKNESS) {
        this.POS_X = posX;
        this.posY = posY;
        this.width = width;
        this.THICKNESS = THICKNESS;
        // this.MOVE_SPEED = MOVE_SPEED;
    }

    public double getPosX() {
        return POS_X;
    }

    public double getPosY() {
        return posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getThickness() {
        return THICKNESS;
    }

    // public int getMoveSpeed() {
    //     return MOVE_SPEED;
    // }

}
