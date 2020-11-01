package com.example.snake_battle.activities.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.snake_battle.model.elements.Board;
import com.example.snake_battle.model.elements.game_elements.GameElement;
import com.example.snake_battle.model.elements.Point;

public class BoardView extends View {

    private Board board;
    private int scale = 10;

    public BoardView(Context context, Board board) {
        super(context);
        this.board = board;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);

        for(GameElement el: board.getGameElements()) {
            for(Point p: el.getLocation())
                canvas.drawCircle(p.x * scale, p.y * scale, scale, paint);

        }
    }
}
