package test;

import game.Game;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Player;


class GameTest
{
    Game game;
    @BeforeEach
    public void beforeEachTestMethod() {
        game = new Game("player1","player2");
    }

    @AfterEach
    public void afterEachTestMethod() {
        game.close();
    }

    @Test
    void isGameOver()
    {
        assertFalse(game.isGameOver());
        game.getCurrentPlayer().addPoints(3000);
        game.gameOver();
        assertTrue(game.isGameOver());
    }

    @Test
    void rollDice()
    {
        game.rollDice();
        assertEquals("","");
    }

    @Test
    void gameOver()
    {
        assertFalse(game.isGameOver());
        game.getCurrentPlayer().addPoints(3000);
        game.gameOver();
        assertTrue(game.isGameOver());
    }

    @Test
    void getCurrentPlayer()
    {
        Player p1 = game.getCurrentPlayer();
        game.changePlayer();
        Player p2 = game.getCurrentPlayer();
        game.changePlayer();
        assertTrue(p1== game.getCurrentPlayer());
        game.changePlayer();
        assertTrue(p2== game.getCurrentPlayer());
    }

    @Test
    void changePlayer()
    {
        Player p1 = game.getCurrentPlayer();
        game.changePlayer();
        Player p2 = game.getCurrentPlayer();
        game.changePlayer();
        assertTrue(p1== game.getCurrentPlayer());
        game.changePlayer();
        assertTrue(p2== game.getCurrentPlayer());
    }

}