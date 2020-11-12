package com.example.snake_battle.viewModel;

import androidx.lifecycle.ViewModel;

import com.example.snake_battle.model.CollisionDetector;
import com.example.snake_battle.model.domainModel.elements.Board;
import com.example.snake_battle.model.domainModel.elements.game_elements.GameElement;
import com.example.snake_battle.model.domainModel.elements.game_elements.Snake;

import java.util.List;

public class InGameVM extends ViewModel {

    private int mapScale; // 5-big, 10-medium, 15-small
    private int gameSpeed;

    private Board board;

    public InGameVM(){
        board = new Board(new CollisionDetector());
        mapScale = 10;
    }

    // InGameActivity
    public void addSnake(){
        board.addSnake(new Snake(1,50,50, 10,board));
    }
    public void update(){
        board.updateAllElements(System.currentTimeMillis());
    }
    public void changeSnakeDirection(int dir){
        board.changeSnakeDirection(dir);
    }
    public void setMapScale(int mapScale) {
        this.mapScale = mapScale;
    }
    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    //BoardView
    public List<GameElement> getGameElements(){
        return board.getGameElements();
    }
    public int getMapScale() {
        return mapScale;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

}
