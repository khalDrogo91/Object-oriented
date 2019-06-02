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
        strikingPlayer.setCurrentTurnStatus(false);
        strikingPlayer.raiseFoulCount();
    }

    private void strikerStrike(Player strikingPlayer) {
        strikingPlayer.setScore(strikingPlayer.getScore() - 1);
        strikingPlayer.setCurrentTurnStatus(false);
        strikingPlayer.raiseFoulCount();
    }

    private void redStrike(Coin coinsCount, Player strikingPlayer) {
        coinsCount.setRedCoinCount(coinsCount.getRedCoinCount() - 1);
        strikingPlayer.setScore(strikingPlayer.getScore() + 3);
        strikingPlayer.setCurrentTurnStatus(true);
    }

    private void multiStrike(Player strikingPlayer) {
        strikingPlayer.setScore(strikingPlayer.getScore() + 2);
        strikingPlayer.setCurrentTurnStatus(true);
    }

    private void strike(Coin blackCoinsCount, Player strikingPlayer) {
        blackCoinsCount.setBlackCoinsCount(blackCoinsCount.getBlackCoinsCount() - 1);
        strikingPlayer.setScore(strikingPlayer.getScore() + 1);
        strikingPlayer.setCurrentTurnStatus(true);
    }

    public void applyTurnRule(Player strikingPlayer) {
        int size = strikingPlayer.getSuccessiveTurnStatuses().size();
        if (size >= 3) {
            boolean elementOne = strikingPlayer.getSuccessiveTurnStatuses().get(size - 1);
            boolean elementTwo = strikingPlayer.getSuccessiveTurnStatuses().get(size - 2);
            boolean elementThree = strikingPlayer.getSuccessiveTurnStatuses().get(size - 3);

            if (elementOne == false && elementTwo == false && elementThree == false) {
                strikingPlayer.setScore(strikingPlayer.getScore() - 1);
                strikingPlayer.raiseFoulCount();
            }
        }
    }

    public void applyFoulRule(Player strikingPlayer) {
        if (isThreeTimesFoul(strikingPlayer)){
            strikingPlayer.setScore(strikingPlayer.getScore() - 1);
            strikingPlayer.setFoulCount(0);
        }
    }

    private boolean isThreeTimesFoul(Player strikingPlayer) {
        return strikingPlayer.getFoulCount() == 3;
    }
}