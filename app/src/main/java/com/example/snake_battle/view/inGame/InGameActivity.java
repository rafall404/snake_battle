package com.example.snake_battle.view.inGame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.snake_battle.R;
import com.example.snake_battle.model.CollisionDetector;
import com.example.snake_battle.model.domainModel.elements.Board;
import com.example.snake_battle.model.domainModel.elements.game_elements.Snake;
import com.example.snake_battle.view.game_setup_main.GameTypeActivity;
import com.example.snake_battle.view.game_setup_main.NewGameActivity;
import com.example.snake_battle.viewModel.GameSetupVM;
import com.example.snake_battle.viewModel.InGameVM;

import java.util.concurrent.TimeUnit;

public class InGameActivity extends AppCompatActivity {

    private Thread gameUpdater;
    private BoardView boardView;

    private InGameVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_game);

        viewModel = new ViewModelProvider(this).get(InGameVM.class);
        viewModel.addSnake();

        LinearLayout root = findViewById(R.id.game_root);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey(NewGameActivity.EXTRA_MAP_SIZE)) {
            int mapSize = bundle.getInt(NewGameActivity.EXTRA_MAP_SIZE);
            viewModel.setMapScale(mapSize);
        }
        if (bundle != null && bundle.containsKey(NewGameActivity.EXTRA_GAME_SPEED)) {
            int gameSpeed = bundle.getInt(NewGameActivity.EXTRA_GAME_SPEED);
            viewModel.setGameSpeed(gameSpeed);
        }


        // BoardView init
        boardViewInit(root);

        // gameUpdater init
        gameUpdaterInit();
    }

    public void onUpButtonClick(View view) {
        viewModel.changeSnakeDirection(2);
    }

    public void onRightButtonClick(View view) {
        viewModel.changeSnakeDirection(1);
    }

    public void onDownButtonClick(View view) {
        viewModel.changeSnakeDirection(0);
    }

    public void onLeftButtonClick(View view) {
        viewModel.changeSnakeDirection(3);
    }

    public void boardViewInit(LinearLayout root){
        boardView = new BoardView(this, viewModel);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1.0f;
        boardView.setLayoutParams(params);
        root.addView(boardView, 0);
    }

    public void gameUpdaterInit(){
        gameUpdater = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        TimeUnit.MILLISECONDS.sleep(250);
                        viewModel.update();
                        boardView.invalidate();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameUpdater.start();
    }
}