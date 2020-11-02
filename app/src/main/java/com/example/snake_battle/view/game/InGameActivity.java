package com.example.snake_battle.view.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.snake_battle.R;
import com.example.snake_battle.model.CollisionDetector;
import com.example.snake_battle.model.domainModel.elements.Board;
import com.example.snake_battle.model.domainModel.elements.game_elements.Snake;

import java.util.concurrent.TimeUnit;

public class InGameActivity extends AppCompatActivity {

    private Thread gameUpdater;
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_game);

        board = new Board(new CollisionDetector());
        board.addSnake(new Snake(1, 50, 50, 10, board));

        LinearLayout root = findViewById(R.id.game_root);

        BoardView boardView = new BoardView(this, board);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1.0f;
        boardView.setLayoutParams(params);

        root.addView(boardView, 0);

        gameUpdater = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        TimeUnit.MILLISECONDS.sleep(250);
                        board.updateAllElements(System.currentTimeMillis());
                        boardView.invalidate();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        gameUpdater.start();
    }

    public void onUpButtonClick(View view) {
        board.changeSnakeDirection(2);
    }

    public void onRightButtonClick(View view) {
        board.changeSnakeDirection(1);
    }

    public void onDownButtonClick(View view) {
        board.changeSnakeDirection(0);
    }

    public void onLeftButtonClick(View view) {
        board.changeSnakeDirection(3);
    }


}