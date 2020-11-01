package com.example.snake_battle.model.elements;

import com.example.snake_battle.model.CollisionDetector;
import com.example.snake_battle.model.elements.game_elements.Food;
import com.example.snake_battle.model.elements.game_elements.GameElement;
import com.example.snake_battle.model.elements.game_elements.Snake;

import java.util.LinkedList;
import java.util.List;

public class Board {
    int height;
    int width;

    private LinkedList<GameElement> elements;
    private CollisionDetector cdetector;

    public Board(CollisionDetector cdetector) {
        this.cdetector = cdetector;
        elements = new LinkedList<>();
    }

    public void addSnake(Snake snake) {
        elements.add(snake);
    }

    public void addFood(Food food){
        elements.add(food);
    }

    public int getSizeOfElements(){
        return elements.size();
    }

    public List<GameElement> getGameElements(){
        return elements;
    }

    public GameElement getElement(int index){
        return elements.get(index);
    }

    public void updateAllElements(long time) {
        for (int i = 0; i < elements.size(); i++) {
            elements.get(i).update(time);
        }
    }

    public void moveTo(Snake s, Point loc) {
        GameElement el = cdetector.checkCollision(this, loc);
        if(el instanceof Snake){
            // dwanadziesienc.mp3
        } else if(el instanceof Food) {
            s.expand();
        } else {
            // kon zwalony
        }
    }

    public void changeSnakeDirection( int dir){
        for (GameElement e: elements) {
            if(e instanceof Snake){// Later need to be changed to id or sth for multipalyer
                e.changeHeadDirection(dir);
            }
        }
    }
}
