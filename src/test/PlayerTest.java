package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Player;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player;

    @BeforeEach
    void setUp() {
        player = new Player("B1");
    }

    @Test
    void getName() {
        assertEquals(player.getName(), "B1");
    }

    @Test
    void getPoints() {
        assertEquals(player.getPoints(), 1000);
    }

    @Test
    void addPoints() {
        player.addPoints(100);
        assertEquals(player.getPoints(), 1100);
        player.addPoints(-400);
        assertEquals(player.getPoints(), 700);
        player.addPoints(-750);
        assertEquals(player.getPoints(), 0);
    }
}