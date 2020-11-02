package com.example.snake_battle.model.domainModel;

public class Score {

    private int id;
    private String score;
    private String nickname;

    public Score(String score, String nickname) {
        this.score = score;
        this.nickname = nickname;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}