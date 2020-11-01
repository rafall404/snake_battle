package com.example.snake_battle.activities.game_setup_main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.snake_battle.R;

public class GameTypeActivity extends AppCompatActivity {

    final static String gameType = "gameType";
    private String chosenGameType;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_type);


        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    
    public void onSinglePlayerButton(View view) {
        chosenGameType = "singlePlayer";
        Context context = getApplicationContext();
        Class destination = NewGameActivity.class;

        Intent intent = new Intent(context, destination);
        intent.putExtra(gameType, chosenGameType);

        startActivity(intent);
    }

    public void onCreateLobbyButton(View view) {
        chosenGameType = "createLobby";
    }

    public void onJoinLobbyButton(View view) {
        chosenGameType = "joinLobby";
    }
}