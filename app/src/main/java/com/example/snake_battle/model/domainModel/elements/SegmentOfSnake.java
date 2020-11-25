package com.example.snake_battle.model.domainModel.elements;

public class SegmentOfSnake {
    private Point point;

    public SegmentOfSnake(int x, int y){
        point =  new Point(x, y);
    }
    public SegmentOfSnake(Point point){
        this.point =  point;

    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
