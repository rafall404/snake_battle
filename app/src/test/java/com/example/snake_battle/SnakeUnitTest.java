package com.example.snake_battle;

import com.example.snake_battle.model.CollisionDetector;
import com.example.snake_battle.model.domainModel.elements.Board;
import com.example.snake_battle.model.domainModel.elements.Point;
import com.example.snake_battle.model.domainModel.elements.game_elements.Snake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakeUnitTest {

    Snake snake;

    @BeforeEach
    public void init(){
        CollisionDetector collisionDetector = new CollisionDetector();
        Board board = new Board(collisionDetector);

        snake = new Snake(1,50,50, 12, board);
    }

    @Test
    public void constructor(){
        // head + 12
        assertEquals(13, snake.getSegments().size());
    }

    @Test
    public void expandSnakeLenght(){
        snake.expand();
        assertEquals(14,snake.getSegments().size());
    }

    @Test
    public void expandSnakeNewSegmentMatchLastSegmentLocation(){
        Point lastPoint = snake.getSegments().getLast().getPoint();
        snake.expand();
        Point newLastPoint = snake.getSegments().getLast().getPoint();

        assertEquals(lastPoint.x, newLastPoint.x);
        assertEquals(lastPoint.y, newLastPoint.y);
    }

    @Test
    public void move(){
        List<Point> pointsOfSnakeSegmentsBeforeMove = new LinkedList<>();

        for (int i = 0; i < snake.getSegments().size() ; i++) {
            pointsOfSnakeSegmentsBeforeMove.add(snake.getSegments().get(i).getPoint());
        }

        snake.move();

        //head
        int headDir = snake.getHeadDirection();
        if (headDir == 0) {
            assertEquals(pointsOfSnakeSegmentsBeforeMove.get(0).x,
                    snake.getSegments().get(0).getPoint().x);

            assertEquals(pointsOfSnakeSegmentsBeforeMove.get(0).y + 1,
                    snake.getSegments().get(0).getPoint().y);

        } else if (headDir == 1) {
            assertEquals(pointsOfSnakeSegmentsBeforeMove.get(0).x + 1,
                    snake.getSegments().get(0).getPoint().x);

            assertEquals(pointsOfSnakeSegmentsBeforeMove.get(0).y,
                    snake.getSegments().get(0).getPoint().y);

        } else if (headDir == 2) {
            assertEquals(pointsOfSnakeSegmentsBeforeMove.get(0).x,
                    snake.getSegments().get(0).getPoint().x);

            assertEquals(pointsOfSnakeSegmentsBeforeMove.get(0).y - 1,
                    snake.getSegments().get(0).getPoint().y);

        } else {
            assertEquals(pointsOfSnakeSegmentsBeforeMove.get(0).x,
                    snake.getSegments().get(0).getPoint().x);

            assertEquals(pointsOfSnakeSegmentsBeforeMove.get(0).y + 1,
                    snake.getSegments().get(0).getPoint().y);

        }
        // boady
        for (int i = 0; i < snake.getSegments().size() -1 ; i++) {
            assertEquals(snake.getSegments().get(i + 1).getPoint().y, pointsOfSnakeSegmentsBeforeMove.get(i).y);
            assertEquals(snake.getSegments().get(i + 1).getPoint().x, pointsOfSnakeSegmentsBeforeMove.get(i).x);
        }

    }

    @Test
    public void getsSnakeLocationTest(){
        LinkedList<Point> expectedLocation = new LinkedList<>();
        for (int i = 0; i < snake.getSegments().size(); i++) {
            expectedLocation.add(snake.getSegments().get(i).getPoint());
        }

        LinkedList<Point> actualLocation = snake.getLocation();

        for (int i = 0; i < snake.getSegments().size() ; i++) {
            Point expectedPoint = expectedLocation.get(i);
            Point actualPoint = actualLocation.get(i);

            assertEquals(expectedPoint.x, actualPoint.x);
            assertEquals(expectedPoint.y, actualPoint.y);
        }
    }




}
