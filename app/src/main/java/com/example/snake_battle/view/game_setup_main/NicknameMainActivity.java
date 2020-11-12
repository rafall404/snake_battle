package com.example.snake_battle.view.game_setup_main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.snake_battle.R;
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
        // toolbar
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        // ViewModel
        viewModel = GameSetupVM.getInstance();
        viewModel.getNickname().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                nicknameHello.setText("Hello " + s+"!");
            }
        });

        preferences = getSharedPreferences("prefs", MODE_PRIVATE);
    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nickname", viewModel.getNickname().getValue());
        editor.apply();
    }
    @Override
    protected void onResume() {
        super.onResume();
        String savedNickname = preferences.getString("nickname", " ");
        viewModel.setNickname(savedNickname);
        nicknameHello.setText(savedNickname);
        nicknameInput.setText(savedNickname);
    }


    public void addNickname(){
        viewModel.setNickname(nicknameInput.getText().toString());
    }

    // on clicks
    public void onSetClick(View view) {
        addNickname();
    }

    public void onNextButton(View view) {
        String nick = viewModel.getNickname().getValue();
        String toastMessage = null;

        Context context = getApplicationContext();

        if(nick.equals("")){
            toastMessage = "you need to set a nickname";
            Toast toast = Toast.makeText(context, toastMessage, Toast.LENGTH_LONG);
            toast.show();
        } else if(nick.contains(" ")){
            toastMessage = "nickname can not conatin spaces";
            Toast toast = Toast.makeText(context, toastMessage, Toast.LENGTH_LONG);
            toast.show();
        }else{
            Class destination = GameTypeActivity.class;
            Intent intent = new Intent(context, destination);
            startActivity(intent);
        }

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