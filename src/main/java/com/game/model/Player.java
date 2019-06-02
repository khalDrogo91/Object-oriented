package com.game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {

    private String name;
    private boolean hasTurn = false;
    private int score = 0;
    private List<Boolean> turnStatuses;
    private int foulCount = 0;


    public Player(String name) {
        this.name = name;
        turnStatuses = new ArrayList<>();
    }

    public Player(String name, boolean turn) {
        this.name = name;
        this.hasTurn = turn;
        turnStatuses = new ArrayList<>();
    }

    public boolean isHasTurn() {
        return hasTurn;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTurn(boolean value) {
        this.hasTurn = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return hasTurn == player.hasTurn &&
                score == player.score &&
                Objects.equals(name, player.name);
    }

    public List<Boolean> getSuccessiveTurnStatuses() {
        return turnStatuses;
    }

    public void setCurrentTurnStatus(boolean status) {
        turnStatuses.add(status);
    }

    public void raiseFoulCount() {
        foulCount++;
    }

    public int getFoulCount() {
        return foulCount;
    }

    public void setFoulCount(int count) {
        foulCount = count;
    }
}
