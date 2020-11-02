package com.example.snake_battle.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class NicknameVM extends ViewModel {

    private MutableLiveData<String> nickname;

    public NicknameVM(){
        nickname =  new MutableLiveData<>();
        nickname.setValue("");
    }

    public void setNickname(String s){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                nickname.postValue(s);
            }
        }, 500);
    }

    public LiveData<String> getNickname(){
        return nickname;
    }

    public String getNicknameString(){
        return nickname.toString();
    }

    public void deleteNickname(){
        nickname.setValue("");
    }

}
