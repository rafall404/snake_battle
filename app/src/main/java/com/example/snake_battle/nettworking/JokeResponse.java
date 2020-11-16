package com.example.snake_battle.nettworking;

import com.example.snake_battle.model.domainModel.Joke;

public class JokeResponse {
    private String id;
    private String content;

    public Joke getJoke(){
        return new Joke(id,content);
    }
}
