package com.example.snake_battle.view.game_setup_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.snake_battle.R;
import com.example.snake_battle.view.scoreboard.ScoreBoardActivity;
import com.example.snake_battle.viewModel.GameSetupVM;

public class GameTypeActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private GameSetupVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_type);
        // toolbar
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        // view-model
        viewModel = GameSetupVM.getInstance();
    }

    // ---------BUTTONS
    public void onSinglePlayerButton(View view) {
        viewModel.setGameType("singlePlayer");

        Intent intent = new Intent(getApplicationContext(), NewGameActivity.class);
        startActivity(intent);
    }
    public void onCreateLobbyButton(View view) {
        viewModel.setGameType("createLobby");

        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "option not available", Toast.LENGTH_LONG);
        toast.show();
    }
    public void onJoinLobbyButton(View view) {
        viewModel.setGameType("joinLobby");

        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "option not available", Toast.LENGTH_LONG);
        toast.show();
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