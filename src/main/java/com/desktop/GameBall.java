package com.desktop;

public class GameBall {
    private final int RADIUS;
    private double posX;
    private double posY;
    // private enum Direction {UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT};
    // private Direction dir;

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

    // public void setInitialDir(Direction dir) {
    //     this.dir = dir;
    // }
    
    // public void bounceVertical() {
    //     switch(dir) {
    //         case UPLEFT -> dir = Direction.DOWNLEFT;
    //         case UPRIGHT -> dir = Direction.DOWNRIGHT;
    //         case DOWNLEFT -> dir = Direction.UPLEFT;
    //         case DOWNRIGHT -> dir = Direction.UPRIGHT;
    //     }
    // }
    
    // public void bounceHorizontal() {
    //     switch(dir) {
    //         case UPLEFT -> dir = Direction.UPRIGHT;
    //         case UPRIGHT -> dir = Direction.UPLEFT;
    //         case DOWNLEFT -> dir = Direction.DOWNRIGHT;
    //         case DOWNRIGHT -> dir = Direction.DOWNLEFT;
    //     }
    // }
}
