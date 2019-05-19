package com.game;

import com.game.model.Game;
import com.game.model.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RunnerTest {

    @Test
    public void thatPlayerHasTurn() {
        Player a = new Player("A", true);
        Game game = setUpGame();
        assertEquals(true, a.isHasTurn());
        assertEquals(a, game.getStrikingPlayer());

    }

    @Test
    public void thatGameSetUpIsDone() {
        Player a = new Player("A");
        Player b = new Player("B");
        Game game = new Game(a, b);
        assertEquals(9, game.getCoin().getBlackCoinsCount());
        assertEquals(1, game.getCoin().getRedCoinCount());
        assertEquals(2, game.getPlayersCount());
    }

    @Test
    public void thatPlayerGetsAPointForOutcomeStrike() {
        Game game = setUpGame();
        game.outcome("strike");
        assertEquals(1, game.getPlayer("A").getScore());
        assertEquals(0, game.getPlayer("B").getScore());
        assertEquals(8, game.getCoin().getBlackCoinsCount());
    }

    @Test
    public void defaultTurns() {
        Game game = setUpGame();
        assertEquals(true, game.getPlayer("A").isHasTurn());
        assertEquals(false, game.getPlayer("B").isHasTurn());
    }

    @Test
    public void thatOutcomeTogglesTheTurns() {
        Game game = setUpGame();
        assertEquals(true, game.getPlayer("A").isHasTurn());

        game.outcome("strike");
        assertEquals(false, game.getPlayer("A").isHasTurn());
        assertEquals(true, game.getPlayer("B").isHasTurn());
    }

    @Test
    public void thatPlayerMultiStrikes() {
        Game game = setUpGame();
        game.outcome("MultiStrike");
        assertEquals(2, game.getPlayer("A").getScore());
    }

    private Game setUpGame() {
        Player a = new Player("A");
        Player b = new Player("B");
        return new Game(a, b);
    }

}