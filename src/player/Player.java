package player;

import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_main.GUI;

import java.awt.*;

public class Player
{

    private int point;
    private String playerName;
    private GUI_Player gui_player;

    public Player(GUI gui)
    {
        try
        {
            point = 1000;
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
    }
    public Player(String playerName)
    {
        this.playerName=playerName;
        point = 1000;
        GUI_Car car = new GUI_Car();
        car.setPrimaryColor(Color.RED);
        gui_player = new GUI_Player(playerName, point, car);
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
        if(this.point < 0) {
            this.point = 0;
        }
        this.gui_player.setBalance(this.point);
    }

    public GUI_Player getGuiPlayer()
    {
        return gui_player;
    }

}
