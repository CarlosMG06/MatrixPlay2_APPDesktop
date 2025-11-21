package com.desktop;

public class GameBar {
    private double posX;
    private double posY;
    private int width;
    private int height;
    public static enum Direction {UP, STILL, DOWN};
    private Direction dir;

    public GameBar(double posX, int posY, int WIDTH, int height, Direction dir) {
        this.posX = posX;
        this.posY = posY;
        this.width = WIDTH;
        this.height = height;
        this.dir = dir;
    }
    
    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }
    public void setPosY(double posY) {
        this.posY = posY;
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

    public Direction getDirection() {
        return dir;
    }
    public void setDirUp() {
        dir = Direction.UP;
    }
    public void setDirDown() {
        dir = Direction.DOWN;
    }
    public void setDirStill() {
        dir = Direction.STILL;
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
