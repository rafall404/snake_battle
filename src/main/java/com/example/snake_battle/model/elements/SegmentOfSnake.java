package com.example.snake_battle.model.elements;

public class SegmentOfSnake {
    private Point point;

    public SegmentOfSnake(int x, int y){
        point =  new Point(x, y);
    }
    public SegmentOfSnake(Point point){
        this.point =  point;

    }

    public void move(int dir) {
        switch(dir) {
            case 0:
                point.transform(point.x, point.y +1);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                throw new RuntimeException("move method exception - out of switch cases");
        }
    }


    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
