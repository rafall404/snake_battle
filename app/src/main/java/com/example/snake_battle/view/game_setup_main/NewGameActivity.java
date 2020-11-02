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
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.snake_battle.R;
import com.example.snake_battle.view.game.InGameActivity;
import com.example.snake_battle.view.scoreboard.ScoreBoardActivity;

public class NewGameActivity extends AppCompatActivity {

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
    private ImageView iland;

    private RadioGroup sizeOfMap;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game);

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);


        iland = findViewById(R.id.ilandViewImage);
        // get scale image
        sizeOfMap = findViewById(R.id.radioGroup);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey(GameTypeActivity.gameType)) {
            String message = bundle.getString(GameTypeActivity.gameType);
            gameType = message;
        }
    }

    public void onRadioButtonClickedMapSize(View view) {

        switch (view.getId()) {
            case R.id.radio_small:
                mapSize = -1;
                // here resize iland image view
                iland.setScaleX(0.5f);
                iland.setScaleY(0.5f);
                break;

            case R.id.radio_medium:
                mapSize = 0;
                iland.setScaleX(1);
                iland.setScaleY(1);

                break;

            case R.id.radio_large:
                mapSize = 1;
                iland.setScaleX(1.5f);
                iland.setScaleY(1.5f);

                break;
        }

    }

    public void onRadioButtonClickedGameSpeed(View view) {
        switch ((view.getId())) {
            case R.id.radioSlow:
                gameSpeed = -1;
                break;

            case R.id.radioNormal:
                gameSpeed = 0;
                break;

            case R.id.radioFast:
                gameSpeed = 1;
                break;
        }

    }

    public void onNextButton(View view) {
        if (gameType.equals("singlePlayer")) {
            Context context = getApplicationContext();
            Class destination = InGameActivity.class;

            Intent intent = new Intent(context, destination);
            startActivity(intent);
        } else {
            // create lobby stuff

        }
    }

    //menu
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