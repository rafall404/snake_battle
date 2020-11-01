package com.example.snake_battle.model.elements;

public class Point {
    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point transform(int a, int b) {
        return new Point(x + a, y + b);
    }
}
