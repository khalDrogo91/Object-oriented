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

    @Test
    public void thatPlayerGetsRedStrike() {
        Game game = setUpGame();
        game.outcome("Red strike");
        assertEquals(3, game.getPlayer("A").getScore());
    }

    @Test
    public void thatPlayerLosesAPointWhenStrikerStrike(){
        Game game = setUpGame();
        game.getPlayer("A").setScore(2);
        game.outcome("Striker strike");
        assertEquals(1, game.getPlayer("A").getScore());
    }

    @Test
    public void thatPlayerLosesAPointWhenCoinGoesOutOfPlay(){
        Game game = setUpGame();
        game.getPlayer("A").setScore(2);
        game.outcome("Defunct coin");
        assertEquals(0, game.getPlayer("A").getScore());
        assertEquals(8, game.getCoin().getBlackCoinsCount());
        assertEquals(1, game.getPlayer("A").getFoulCount());
    }

    @Test
    public void thatForThreeUnsuccessfulTurnPlayerLosesAPoint(){
        Game game = setUpGame();
        game.getPlayer("A").setScore(4);
        game.outcome("Striker strike");
        game.outcome("strike");//B
        game.outcome("Striker strike");
        game.outcome("strike");//B
        game.outcome("Striker strike");
        assertEquals(4, game.getPlayer("A").getFoulCount());
        assertEquals(0, game.getPlayer("A").getScore());
    }

    @Test
    public void thatFoulCountIncreasesWhenPlayerLosesAPoint(){
        Game game = setUpGame();
        game.getPlayer("A").setScore(2);
        game.outcome("Striker strike");
        assertEquals(1, game.getPlayer("A").getFoulCount());
    }

    @Test
    public void thatForThreeFoulPlayerLosesAdditionalPoint(){
        Game game = setUpGame();
        game.getPlayer("A").setScore(2);
        game.getPlayer("A").raiseFoulCount();
        game.getPlayer("A").raiseFoulCount();
        game.outcome("strike"); //A
        game.outcome("strike");///B
        game.outcome("Striker strike");///A
        assertEquals(1, game.getPlayer("A").getScore());
    }

    private Game setUpGame() {
        Player a = new Player("A");
        Player b = new Player("B");
        return new Game(a, b);
    }



}