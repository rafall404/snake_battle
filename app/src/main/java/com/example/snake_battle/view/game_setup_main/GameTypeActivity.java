package com.example.snake_battle.view.game_setup_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.snake_battle.R;
import com.example.snake_battle.view.scoreboard.ScoreBoardActivity;

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

    // menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Context context = getApplicationContext();
        Class destination;
        Intent intent;
        switch(item.getItemId()) {
            case R.id.share:
                String share = "You shared your stuff";

                Toast toast = Toast.makeText(context, share, Toast.LENGTH_LONG);
                toast.show();
                return true;
            case R.id.action_activity1:
                destination = ScoreBoardActivity.class;

                intent = new Intent(context, destination);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}