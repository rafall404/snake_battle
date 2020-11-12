package com.example.snake_battle.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.snake_battle.model.domainModel.Score;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardVM extends ViewModel {
    private MutableLiveData<List<Score>> scores;

    public ScoreBoardVM(){
        scores = new MutableLiveData<>();
        List<Score> list = new ArrayList<>();
        scores.setValue(list);

        for (int i = 0; i < 50; i++) {

            scores.getValue().add(new Score(""+i, "Rafal "+i));
        }
    }

    public LiveData<List<Score>> getAllScores(){
        return scores;
    }

}
