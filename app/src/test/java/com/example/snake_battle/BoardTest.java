package com.example.snake_battle;


import com.example.snake_battle.model.CollisionDetector;
import com.example.snake_battle.model.domainModel.elements.Board;
import com.example.snake_battle.model.domainModel.elements.game_elements.Food;
import com.example.snake_battle.model.domainModel.elements.game_elements.Snake;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void init(){
        CollisionDetector collisionDetector = new CollisionDetector();

        board = new Board(collisionDetector);
    }

    @Test
    public void addSnakeTest(){
        Snake snake = new Snake(1,23,23,10, board);
        board.addSnake(snake);
        assertTrue(board.getGameElements().get(0) != null);
    }

    @Test
    public void addFoodTest(){
        Food food = new Food(11,11,1);
        board.addFood(food);
        assertTrue(board.getGameElements().get(0) != null);
    }

    @Test
    public void getSizeOfElements(){
        Food food = new Food(11,11,1);
        Snake snake = new Snake(1,23,23,10, board);
        board.addFood(food);
        board.addSnake(snake);
        assertEquals(2, board.getSizeOfElements());
    }

    @Test
    public void changeSnakeDirection(){
        Snake snake = new Snake(1,40,40,10, board);
        board.addSnake(snake);
        board.changeSnakeDirection(3);

        assertEquals(3, snake.getHeadDirection());
    }
}
