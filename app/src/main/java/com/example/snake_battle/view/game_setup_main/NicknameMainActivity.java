package com.example.snake_battle.view.game_setup_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.snake_battle.R;
import com.example.snake_battle.view.account.RegisterActivity;
import com.example.snake_battle.view.scoreboard.ScoreBoardActivity;
import com.example.snake_battle.viewModel.GameSetupVM;

public class NicknameMainActivity extends AppCompatActivity {

    TextView nicknameHello;
    EditText nicknameInput;

    Toolbar toolbar;

    GameSetupVM viewModel;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_nickname);
        //views
        nicknameHello = findViewById(R.id.nicknameHello);
        nicknameInput = findViewById(R.id.nicknameInput);

        nicknameInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && nicknameHello.getText().length() > 0) {
                nicknameHello.setVisibility(View.VISIBLE);
            }});
        nicknameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nicknameHello.setText("Hello "+nicknameInput.getText().toString()+" !");
            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.setNickname(nicknameInput.getText().toString());
            }
        });
            // toolbar
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        // ViewModel
        viewModel = GameSetupVM.getInstance();


        viewModel.getColor().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                nicknameHello.setTextColor(integer);
            }
        });


        preferences = getSharedPreferences("prefs", MODE_PRIVATE);

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nickname", viewModel.getNickname());
        editor.apply();
    }
    @Override
    protected void onResume() {
        super.onResume();
        String savedNickname = preferences.getString("nickname", "user");
        viewModel.setNickname(savedNickname);
        nicknameHello.setText(savedNickname);
        nicknameInput.setText(savedNickname);
    }


    public void onNextIV(View view) {
        String toastMessage = null;
        Context context = getApplicationContext();

        boolean valid = viewModel.validatenicknamename();

        if(valid==false){
            toastMessage = "nickname not setted or contain space, please fix it";
            Toast toast = Toast.makeText(context, toastMessage, Toast.LENGTH_LONG);
            toast.show();
        } else{
            Class destination = GameTypeActivity.class;
            Intent intent = new Intent(context, destination);
            startActivity(intent);
        }
    }

    public void onWormIV(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), viewModel.getJoke().getContent(), Toast.LENGTH_LONG);
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




    public void onLoginGoogle(View view) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    public void onLoginEmail(View view) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

}