package com.desktop;

public class GameBar {
    private double posX;
    private int posY;
    private int width;
    private int height;

    public GameBar(double posX, int posY, int WIDTH, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = WIDTH;
        this.height = height;
    }

    public void setGameBar(double posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public double getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int moveUp() {
        if (posY - 1 < 0) {
            return (int) posY;
        }
        return (int) posY - 1;
    }

    public int moveDown() {
        if (posY + 1 > 64) {
            return (int) posY;
        }
        return (int) posY + 1;
    }
}
