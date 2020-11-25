package com.example.snake_battle.model.domainModel.elements.game_elements;

import com.example.snake_battle.model.domainModel.elements.Point;

import java.util.LinkedList;

public class Food implements GameElement {

    private Point point;

    private int foodType;
    // 1=goodFood , -1=badFood

    public Food(int x, int y, int foodType) {
        point = new Point(x, y);
        this.foodType = foodType;
    }

    public Food(Point point, int foodType) {
        this.point = point;
        this.foodType = foodType;
    }

    @Override
    public void update(long time) {

    }

    @Override
    public LinkedList<Point> getLocation() {
        LinkedList<Point> location = new LinkedList<>();
        location.add(getPoint());
        return location;
    }

    @Override
    public void changeHeadDirection(int dir) {

    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getFoodType() {
        return foodType;
    }

    public void setFoodType(int foodType) {
        this.foodType = foodType;
    }
}
