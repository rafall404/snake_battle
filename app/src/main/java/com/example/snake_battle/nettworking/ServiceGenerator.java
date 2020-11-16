package com.example.snake_battle.nettworking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder =  new Retrofit.Builder()
            .baseUrl("https://joke3.p.rapidapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static JokesApi jokesApi = retrofit.create(JokesApi.class);

    public static JokesApi getJokesApi(){
        return jokesApi;
    }
}
