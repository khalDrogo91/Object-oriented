package com.game.model;

import java.util.Objects;

public class Player {

    private String name;
    private boolean hasTurn = false;
    private int score = 0;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, boolean turn) {
        this.name = name;
        this.hasTurn = turn;
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

}
