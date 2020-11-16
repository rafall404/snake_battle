package com.example.snake_battle.nettworking;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface JokesApi {

    @Headers("x-rapidapi-key: " + "1376873714mshed088a8241a5a40p17e1f9jsn8745f8c33bbb")
    @GET("joke")
    Call<JokeResponse> getJoke();
}
