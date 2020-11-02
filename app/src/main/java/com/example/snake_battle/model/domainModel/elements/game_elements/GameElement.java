package com.example.snake_battle.model.domainModel.elements.game_elements;

import com.example.snake_battle.model.domainModel.elements.Point;

import java.util.LinkedList;

public interface GameElement {
    void update(long time);
    LinkedList<Point> getLocation();
    void changeHeadDirection(int dir);
}
