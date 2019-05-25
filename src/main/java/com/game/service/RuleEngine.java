package com.game.service;

import com.game.model.Coin;
import com.game.model.OutcomeType;
import com.game.model.Player;

public class RuleEngine {

    public void apply(OutcomeType outcomeType, Coin coinsCount, Player strikingPlayer) {
        switch (outcomeType) {
            case STRIKE:
                strike(coinsCount, strikingPlayer);
                break;
            case MULTISTRIKE:
                multiStrike(strikingPlayer);
                break;
            case RED_STRIKE:
                redStrike(coinsCount, strikingPlayer);
                break;
            case STRIKER_STRIKE:
                strikerStrike(strikingPlayer);
                break;
            case DEFUNCT_COIN:
                DefunctCoinStrike(coinsCount, strikingPlayer);
                break;
        }
    }

    private void DefunctCoinStrike(Coin coinsCount, Player strikingPlayer) {
        strikingPlayer.setScore(strikingPlayer.getScore() - 2);
        coinsCount.setBlackCoinsCount(coinsCount.getBlackCoinsCount() - 1);
    }

    private void strikerStrike(Player strikingPlayer) {
        strikingPlayer.setScore(strikingPlayer.getScore() - 1);
    }

    private void redStrike(Coin coinsCount, Player strikingPlayer) {
        coinsCount.setRedCoinCount(coinsCount.getRedCoinCount() - 1);
        strikingPlayer.setScore(strikingPlayer.getScore() + 3);
    }

    private void multiStrike(Player strikingPlayer) {
        strikingPlayer.setScore(strikingPlayer.getScore() + 2);
    }

    private void strike(Coin blackCoinsCount, Player strikingPlayer) {
        blackCoinsCount.setBlackCoinsCount(blackCoinsCount.getBlackCoinsCount() - 1);
        strikingPlayer.setScore(strikingPlayer.getScore() + 1);
    }

}