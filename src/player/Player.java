package player;

import gui_fields.GUI_Car;
import gui_fields.GUI_Player;
import gui_main.GUI;

import java.awt.*;

public class Player
{

    private Account account;
    private String playerName;
    private GUI_Player gui_player;

    public Player(GUI gui)
    {
        try
        {
            account = new Account(1000);
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
            gui_player = new GUI_Player(playerName, account.getBalance(), car);
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
        account = new Account(1000);
        GUI_Car car = new GUI_Car();
        car.setPrimaryColor(Color.RED);
        gui_player = new GUI_Player(playerName, account.getBalance(), car);
    }

    public String getName()
    {
        return playerName;
    }

    public int getPoints()
    {
        return account.getBalance();
    }

    public void addPoints(int points)
    {
        if (points > 0)
        {
            account.deposit(points);
        }
        else
        {
            account.withdraw(points);
        }
        this.gui_player.setBalance(account.getBalance());
    }

    public GUI_Player getGuiPlayer()
    {
        return gui_player;
    }

}
