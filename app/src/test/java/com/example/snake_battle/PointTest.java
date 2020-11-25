package com.example.snake_battle;

import com.example.snake_battle.model.domainModel.elements.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointTest {
    private Point point;

    @BeforeEach
    public void init(){
        point = new Point(23, 50);
    }

    @Test
    public void transformTest(){
        Point newPoint = point.transform(-1, 3);

        assertEquals(22,newPoint.x);
        assertEquals(53, newPoint.y);
    }

}
