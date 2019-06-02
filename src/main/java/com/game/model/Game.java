package com.game.model;

import com.game.service.RuleEngine;

import static com.game.model.OutcomeType.getOutcomeValue;

public class Game {

    private Player players[] = new Player[2];
    private RuleEngine rules = new RuleEngine();
    private Coin coin = new Coin();

    public String getGameScoreStatus() {
        return gameScoreStatus;
    }

    private String gameScoreStatus;

    public Game(Player a, Player b) {
        players[0] = a;
        players[1] = b;
        setDefaultTurns();
    }

    private void setDefaultTurns() {
        players[0].setTurn(true);
        players[1].setTurn(false);
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

    public Coin getCoin() {
        return coin;
    }

    public void outcome(String type) {
        rules.apply(getOutcomeValue(type), coin, getStrikingPlayer());
        rules.applyTurnRule(getStrikingPlayer());
        rules.applyFoulRule(getStrikingPlayer());
        checkTheWinner();
        checkForDraw();
        toggleTurns();
    }

    private void checkForDraw() {
        if (coin.getBlackCoinsCount() == 0 && coin.getRedCoinCount() == 0) {
            if (!players[0].isWinner() && !players[1].isWinner()) {
                gameScoreStatus = "DRAW";
                System.out.println(gameScoreStatus);
            }
        }
    }

    private String finalScore() {
        if (players[0].isWinner()) {
            gameScoreStatus = "Player 1 won the game." + "Final Score: " + players[0].getScore() + "-" + players[1].getScore();
            System.out.println(gameScoreStatus);
        } else if (players[1].isWinner()) {
            gameScoreStatus = "Player 2 won the game." + "Final Score: " + players[1].getScore() + "-" + players[0].getScore();
            System.out.println(gameScoreStatus);
        }
        return gameScoreStatus;
    }

    private void checkTheWinner() {
        int difference = 0;
        if (players[0].getScore() > players[1].getScore()) {
            difference = players[0].getScore() - players[1].getScore();
            if (hasPlayerWonFivePointsInTotal(0) && isScoreDifferenceAtLeastThreePoints(difference, 3)) {
                players[0].setWinner(true);
                finalScore();
            }
        } else if (players[1].getScore() > players[0].getScore()) {
            difference = players[1].getScore() - players[0].getScore();
            if (hasPlayerWonFivePointsInTotal(1) && isScoreDifferenceAtLeastThreePoints(difference, 3)) {
                players[1].setWinner(true);
                finalScore();
            }
        }
    }

    private boolean isScoreDifferenceAtLeastThreePoints(int difference, int i) {
        return difference >= i;
    }

    private boolean hasPlayerWonFivePointsInTotal(int i) {
        return isScoreDifferenceAtLeastThreePoints(players[i].getScore(), 5);
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

    public Player getWinner() {
        Player player = null;
        for (int i = 0; i < players.length; i++) {
            if (players[i].isWinner() == true) {
                player = players[i];
            }
        }
        return player;
    }
}
