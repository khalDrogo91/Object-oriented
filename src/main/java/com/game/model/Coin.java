package com.game.model;

public class Coin {

    private int blackCoinsCount = 9;
    private int redCoinCount = 1;

    public int getBlackCoinsCount() {
        return this.blackCoinsCount;
    }

    public int getRedCoinCount() {
        return this.redCoinCount;
    }

    public void setBlackCoinsCount(int blackCoinsCount) {
        this.blackCoinsCount = blackCoinsCount;
    }
}
