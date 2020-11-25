package com.example.snake_battle.model.domainModel.elements.game_elements;

import com.example.snake_battle.model.domainModel.elements.Board;
import com.example.snake_battle.model.domainModel.elements.Point;
import com.example.snake_battle.model.domainModel.elements.SegmentOfSnake;

import java.util.LinkedList;

public class Snake implements GameElement {

    private Board board;

    private int headDirection;

    /*
          0=up
          1=right
          2=down
          3=left
          */
    private LinkedList<SegmentOfSnake> segments;

    private long lastUpdate = -1;
    private long delay = 1000;

    public Snake(int headDirection, int xAxis, int yAxis, int initLen, Board board) {
        this.board = board;
        this.headDirection = headDirection;
        //head
        segments = new LinkedList<>();
        segments.add(new SegmentOfSnake(xAxis, yAxis));

        for(int i=0; i<initLen; i++) {
            expand();
        }
    }

    public LinkedList<SegmentOfSnake> getSegments() {
        return segments;
    }

    public int getHeadDirection() {
        return headDirection;
    }
    public void expand() {
        segments.add(new SegmentOfSnake(segments.getLast().getPoint()));
    }

    public void move() {
        Point next = null;
        for (int i = 0; i < segments.size(); i++) {
            SegmentOfSnake seg = segments.get(i);

            if (i == 0) {
                if (headDirection == 0) {
                    next = seg.getPoint().transform(0, 1);
                } else if (headDirection == 1) {
                    next = seg.getPoint().transform(1, 0);
                } else if (headDirection == 2) {
                    next = seg.getPoint().transform(0, -1);
                } else {
                    next = seg.getPoint().transform(-1, 0);
                }
                board.moveTo(this, next);

                Point tmp = next;
                next = seg.getPoint();
                seg.setPoint(tmp);

            } else {
                Point tmp = seg.getPoint();
                seg.setPoint(next);
                next = tmp;
            }

        }
    }

    @Override
    public void update(long time) {
        if (lastUpdate == -1)
            lastUpdate = time - 1;

        long tmp = time;
        while (tmp > lastUpdate) {
            move();
            tmp -= delay;
        }
        lastUpdate = time;
    }

    @Override
    public LinkedList<Point> getLocation() {
        LinkedList<Point> location = new LinkedList<>();
        for (int i = 0; i < segments.size(); i++) {
            location.add(segments.get(i).getPoint());
        }
        return location;
    }

    @Override
    public void changeHeadDirection(int dir) {
        headDirection = dir;
    }


}
