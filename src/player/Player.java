package player;

import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_main.GUI;

import java.awt.*;

public class Player
{

    private int point;
    private int lastdice1;
    private int lastdice2;
    private String playerName;
    private GUI_Player gui_player;

    public Player(GUI gui)
    {
        try
        {
            point = 0;
            playerName = gui.getUserString("Input player name");
            String col = gui.getUserSelection("Choose color for " + playerName, "BLACK",
                    "BLUE",
                    "CYAN",
                    "DARK_GRAY",
                    "GRAY",
                    "GREEN",
                    "LIGHT_GRAY",
                    "MAGENTA",
                    "ORANGE",
                    "PINK",
                    "RED",
                    "WHITE",
                    "YELLOW"
            );
            Color i = (Color) Color.class.getDeclaredField(col).get(null);
            GUI_Car car = new GUI_Car();
            car.setPrimaryColor(i);
            gui_player = new GUI_Player(playerName, point, car);
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
        lastdice1 = 0;
        lastdice2 = 0;
    }

    public String getName()
    {
        return playerName;
    }

    public int getPoint()
    {
        return point;
    }

    public void resetPoint()
    {
        point = 0;
    }

    public void addPoint(int point)
    {
        this.point += point;
        this.gui_player.setBalance(this.point);
    }

    public GUI_Player getGuiPlayer()
    {
        return gui_player;
    }

    public int getlastdice1()
    {
        return lastdice1;
    }

    public int getLastdice2()
    {
        return lastdice2;
    }

    public void setlastdice1(int eyes)
    {
        lastdice1 = eyes;
    }

    public void setlastdice2(int eyes)
    {
        lastdice2 = eyes;
    }


}
