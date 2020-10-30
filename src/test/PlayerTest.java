package test;

import gui_fields.GUI_Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import player.Player;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest
{
    Player player;
    @BeforeEach
    public void beforeEachTestMethod() {
        player = new Player("player1");
    }

    @AfterEach
    public void afterEachTestMethod() {
    }

    @Test
    void getName()
    {
        assertEquals("player1", player.getName());
    }

    @Test
    void getPoint()
    {
        assertEquals(1000, player.getPoint());
    }

    @Test
    void resetPoint()
    {
        assertEquals(1000, player.getPoint());
        player.resetPoint();
        assertEquals(0, player.getPoint());
    }

    @Test
    void addPoint()
    {
        int sum = 1000;
        assertEquals(1000, player.getPoint());
        for (int i = 0; i < 3000; i++)
        {
            player.addPoint(i);
            sum +=i;
            assertEquals(sum, player.getPoint());

        }

        player.resetPoint();
        assertEquals(0, player.getPoint());
        for (int i = 0; i < 3000; i++)
        {
            player.addPoint(-i);
            assertEquals(0, player.getPoint());

        }
    }

    @Test
    void getGuiPlayer()
    {
        assertThat(player.getGuiPlayer(), instanceOf(GUI_Player.class));
    }
}