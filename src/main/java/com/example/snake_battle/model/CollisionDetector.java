package com.example.snake_battle.model;

import com.example.snake_battle.model.elements.Board;
import com.example.snake_battle.model.elements.game_elements.GameElement;
import com.example.snake_battle.model.elements.Point;

public class CollisionDetector {

    public GameElement checkCollision(Board board, Point loc) {

        for (GameElement ge: board.getGameElements()) {
            if(ge.getLocation().contains(loc))
                return ge;
        }
        return null;

    }





}
