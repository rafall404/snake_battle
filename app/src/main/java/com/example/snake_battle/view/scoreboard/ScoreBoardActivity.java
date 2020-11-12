package com.example.snake_battle.view.scoreboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.snake_battle.R;
import com.example.snake_battle.model.domainModel.Score;
import com.example.snake_battle.viewModel.ScoreBoardVM;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ScoreBoardActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;

    ScoreBoardVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_board);

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // recycler view
        recyclerView = findViewById(R.id.rv);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // view-model
        viewModel = new ViewModelProvider(this).get(ScoreBoardVM.class);
        viewModel.getAllScores().observe(this, new Observer<List<Score>>() {
            @Override
            public void onChanged(List<Score> scores) {
                ItemsAdapter adapter = new ItemsAdapter(scores);
                recyclerView.setAdapter(adapter);
            }
        });

        ItemsAdapter adapter = new ItemsAdapter(viewModel.getAllScores().getValue());
        recyclerView.setAdapter(adapter);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Context context = getApplicationContext();
        Class destination;
        Intent intent;
        switch (item.getItemId()) {
            case R.id.share:
                String share = "You shared your stuff";

                Snackbar snackbar = Snackbar.make(item.getActionView(), share, Snackbar.LENGTH_LONG);
                snackbar.show();

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