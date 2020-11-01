package com.example.snake_battle.model.elements.game_elements;

import com.example.snake_battle.model.elements.Point;

import java.util.LinkedList;

public interface GameElement {
    void update(long time);
    LinkedList<Point> getLocation();
    void changeHeadDirection(int dir);
}
