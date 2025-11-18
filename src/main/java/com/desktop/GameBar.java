package com.desktop;

public class GameBar {
    private final double POS_X;
    private double posY;
    private final int WIDTH;
    private int height;
    // private final int MOVE_SPEED;

    public GameBar(double posX, double posY, int WIDTH, int height) {
        this.POS_X = posX;
        this.posY = posY;
        this.WIDTH = WIDTH;
        this.height = height;
        // this.MOVE_SPEED = MOVE_SPEED;
    }

    public double getPosX() {
        return POS_X;
    }

    public double getPosY() {
        return posY;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return WIDTH;
    }

    public double moveUp() {
        return posY + 0.01;
    }

    public double moveDown() {
        return posY - 0.01;
    }
}
