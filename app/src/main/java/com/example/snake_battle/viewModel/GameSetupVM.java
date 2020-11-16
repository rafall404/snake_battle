package com.example.snake_battle.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.snake_battle.model.domainModel.Joke;
import com.example.snake_battle.nettworking.JokeResponse;
import com.example.snake_battle.nettworking.JokesApi;
import com.example.snake_battle.nettworking.ServiceGenerator;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private Joke joke;

    private GameSetupVM(){
        nickname =  new MutableLiveData<>();
        nickname.setValue("");
        // REST GET request
        requestJoke();
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

    public void requestJoke() {
        JokesApi jokesApi = ServiceGenerator.getJokesApi();
        Call<JokeResponse> call = jokesApi.getJoke();

        call.enqueue(new Callback<JokeResponse>() {
            @Override
            public void onResponse(Call<JokeResponse> call, Response<JokeResponse> response) {
                if (response.code() == 200) {
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+response.body().getJoke());

                    joke = response.body().getJoke();
                    System.out.println(joke.getContent());
                }
            }
            @Override
            public void onFailure(Call<JokeResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

                throw new RuntimeException(t);
            }
        });
    }

    public Joke getJoke() {
        requestJoke();
        return joke;
    }

}
