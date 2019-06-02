package com.game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {

    private String name;
    private boolean hasTurn;
    private int score = 0;
    private List<Boolean> turnStatuses;
    private int foulCount = 0;
    private boolean isWinner;


    public Player(String name) {
        this.name = name;
        turnStatuses = new ArrayList<>();
        isWinner = false;
        hasTurn = false;
    }

    public Player(String name, boolean turn) {
        this.name = name;
        hasTurn = false;
        this.hasTurn = turn;
        turnStatuses = new ArrayList<>();
        isWinner = false;
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

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean b) {
        isWinner = b;
    }
}
