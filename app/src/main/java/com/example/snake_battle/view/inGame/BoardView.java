package com.example.snake_battle.view.inGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.snake_battle.model.domainModel.elements.Board;
import com.example.snake_battle.model.domainModel.elements.game_elements.GameElement;
import com.example.snake_battle.model.domainModel.elements.Point;
import com.example.snake_battle.viewModel.InGameVM;

public class BoardView extends View {

    private int scale; // 15-small, 10-medium, 5-big

    private InGameVM viewModel;

    public BoardView(Context context, InGameVM viewModel) {
        super(context);
        this.viewModel = viewModel;
        scale = viewModel.getMapScale();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);

        for(GameElement el: viewModel.getGameElements()) {
            for(Point p: el.getLocation())
                canvas.drawCircle(p.x * scale, p.y * scale, scale, paint);
        }
    }
}
