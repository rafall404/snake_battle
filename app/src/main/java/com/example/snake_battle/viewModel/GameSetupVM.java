package com.example.snake_battle.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class GameSetupVM extends ViewModel {

    private static GameSetupVM instance = null; // TODO learn about Hilt and use incjection

    private MutableLiveData<String> nickname;

    private String gameType;
    private int mapSize = 0;
    /*
      -1=small
      0 =medium
      1 =large
      */
    private int gameSpeed = 0;
    /*
      -1=slow
      0 =normal
      1 =fast
      */

    private GameSetupVM(){
        nickname =  new MutableLiveData<>();
        nickname.setValue("");
    }

    public static GameSetupVM getInstance(){
        if(instance == null){
            instance = new GameSetupVM();
        }

        return instance;
    }

    public void setNickname(String s){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                nickname.postValue(s);
            }
        }, 0);
    }

    public LiveData<String> getNickname(){
        return nickname;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }


    public int getMapSize() {
        return mapSize;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
}
