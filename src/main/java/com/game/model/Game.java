package com.game.model;

import com.game.service.RuleEngine;

import static com.game.model.OutcomeType.*;

public class Game {

    private Player players[] = new Player[2];
    private RuleEngine rules = new RuleEngine();
    private int blackCoinsCount = 9;
    private int redCoinCount = 1;

    public Game(Player a, Player b) {
        players[0] = a;
        players[1] = b;
        setDefaultTurns();
    }

    private void setDefaultTurns() {
        players[0].setTurn(true);
        players[1].setTurn(false);
    }

    public int getBlackCoinsCount() {
        return this.blackCoinsCount;
    }

    public int getRedCoinCount() {
        return this.redCoinCount;
    }

    public int getPlayersCount() {
        return this.players.length;
    }

    public Player getPlayer(String name) {
        Player player = null;
        for (int i = 0; i < players.length; i++) {
            if (players[i].getName().equalsIgnoreCase(name)) {
                player = players[i];
            }
        }
        return player;
    }

    public void outcome(String type) {


        rules.apply(getOutcomeValue(type), blackCoinsCount, getStrikingPlayer());

        if ("STRIKE".equalsIgnoreCase(type)) {
            blackCoinsCount--;
            players[0].setScore(players[0].getScore() + 1);
        }

        toggleTurns();
    }

    private void toggleTurns() {
        for (int i = 0; i < players.length; i++) {
            if (players[i].isHasTurn() == true) {
                players[i].setTurn(false);
            } else if (players[i].isHasTurn() == false) {
                players[i].setTurn(true);
            }
        }
    }

    public Player getStrikingPlayer() {
        Player player = null;
        for (int i = 0; i < players.length; i++) {
            if (players[i].isHasTurn() == true) {
                player = players[i];
            }
        }
        return player;
    }
}
