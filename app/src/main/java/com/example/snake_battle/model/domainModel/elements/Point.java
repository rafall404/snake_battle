package com.example.snake_battle.model.domainModel.elements;

public class Point {


    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point transform(int a, int b) {
        return new Point(x + a, y + b);
    }
}
