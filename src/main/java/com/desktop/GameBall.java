package com.desktop;

public class GameBall {
    private final double START_POS_X;
    private final double START_POS_Y;
    private final int RADIUS;
    private double posX;
    private double posY;
    // private enum Direction {UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT};
    // private Direction dir;

    public GameBall(double posX, double posY, int RADIUS) {
        this.START_POS_X = posX;
        this.START_POS_Y = posY;
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

    public int getRadius() {
        return RADIUS;
    }

    public void resetPos() {
        posX = START_POS_X;
        posY = START_POS_Y;
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
