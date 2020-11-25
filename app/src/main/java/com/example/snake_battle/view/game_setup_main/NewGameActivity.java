package com.example.snake_battle.view.game_setup_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.snake_battle.R;
import com.example.snake_battle.view.inGame.InGameActivity;
import com.example.snake_battle.view.scoreboard.ScoreBoardActivity;
import com.example.snake_battle.viewModel.GameSetupVM;

public class NewGameActivity extends AppCompatActivity {
    public static final String EXTRA_MAP_SIZE = "mapSize";
    public static final String EXTRA_GAME_SPEED = "gameSpeed";

    private ImageView iland;
    private Toolbar toolbar;

    private GameSetupVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game);

        iland = findViewById(R.id.ilandViewImage);
        // menu
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //view-model
        viewModel = GameSetupVM.getInstance();
    }

    public void onRadioButtonClickedMapSize(View view) {

        switch (view.getId()) {
            case R.id.radio_small:
                viewModel.setMapSize(20); // usage of scae in view.inGame.BoardView

                iland.setScaleX(0.5f);
                iland.setScaleY(0.5f);
                break;
            case R.id.radio_medium:
                viewModel.setMapSize(10);

                iland.setScaleX(1);
                iland.setScaleY(1);
                break;
            case R.id.radio_large:
                viewModel.setMapSize(8);

                iland.setScaleX(1.5f);
                iland.setScaleY(1.5f);
                break;
        }

    }

    public void onRadioButtonClickedGameSpeed(View view) {
        switch ((view.getId())) {
            case R.id.radioSlow:
                viewModel.setGameSpeed(400);
                break;

            case R.id.radioNormal:
                viewModel.setGameSpeed(250);
                break;

            case R.id.radioFast:
                viewModel.setGameSpeed(150);
                break;
        }

    }

    public void onNextButton(View view) {
        if (viewModel.getGameType().equals("singlePlayer")) {
            Intent intent = new Intent(getApplicationContext(), InGameActivity.class);

            intent.putExtra(EXTRA_MAP_SIZE, viewModel.getMapSize());
            intent.putExtra(EXTRA_GAME_SPEED, viewModel.getGameSpeed());

            startActivity(intent);

        } else {
            // create lobby stuff

        }
    }

    //menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_child, menu);
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