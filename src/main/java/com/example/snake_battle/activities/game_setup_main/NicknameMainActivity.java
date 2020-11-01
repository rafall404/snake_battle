package com.example.snake_battle.activities.game_setup_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.snake_battle.R;
import com.example.snake_battle.activities.scoreboard.ScoreBoardActivity;

public class NicknameMainActivity extends AppCompatActivity {

    TextView nicknameHello;
    EditText nicknameInput;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_nickname);

        nicknameHello = findViewById(R.id.nicknameHello);
        nicknameInput = findViewById(R.id.nicknameInput);

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onNextButton(View view) {
        Context context = getApplicationContext();
        Class destination = GameTypeActivity.class;

        Intent intent = new Intent(context, destination);
        startActivity(intent);

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