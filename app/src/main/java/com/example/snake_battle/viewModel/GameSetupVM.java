package com.example.snake_battle.viewModel;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.snake_battle.model.domainModel.Joke;
import com.example.snake_battle.nettworking.JokeResponse;
import com.example.snake_battle.nettworking.JokesApi;
import com.example.snake_battle.nettworking.ServiceGenerator;
import com.example.snake_battle.view.game_setup_main.GameTypeActivity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameSetupVM extends ViewModel {

    private static GameSetupVM instance = null; // TODO learn about Hilt and use incjection

    private String nickname;


    private MutableLiveData<Integer> helloTVColor;

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

    private Joke joke;

    private GameSetupVM(){

        helloTVColor = new MutableLiveData<Integer>();
        changeColor();

        // REST GET request
        requestJoke();
    }

    public static GameSetupVM getInstance(){
        if(instance == null){
            instance = new GameSetupVM();
        }
        return instance;
    }


    public void changeColor(){
        Random rand = new Random();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                helloTVColor.postValue(color);
            }
        },0,2000);
    }

    public LiveData<Integer> getColor(){
        return helloTVColor;
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

    public void requestJoke() {
        JokesApi jokesApi = ServiceGenerator.getJokesApi();
        Call<JokeResponse> call = jokesApi.getJoke();

        call.enqueue(new Callback<JokeResponse>() {
            @Override
            public void onResponse(Call<JokeResponse> call, Response<JokeResponse> response) {
                if (response.code() == 200) {

                    joke = response.body().getJoke();

                }
            }
            @Override
            public void onFailure(Call<JokeResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");

                throw new RuntimeException(t);
            }
        });
    }

    public Joke getJoke() {
        requestJoke();
        return joke;
    }
    
    public boolean validatenicknamename(){
        boolean ifValid;
        
        if(nickname.equals("")){
            ifValid = false;
        } else if(nickname.contains(" ")){
            ifValid = false;
        }else{
            ifValid = true;
        }
        return ifValid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
