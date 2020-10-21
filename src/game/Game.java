package game;

import dice.DiceCup;
import gui_fields.GUI_Field;
import gui_fields.GUI_Shipping;
import gui_main.GUI;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class Game
{

    private final Player player1;
    private final Player player2;
    private final DiceCup dice;
    private final Scanner input;
    private GUI gui;
    private GUI_Field[] fields;
    private Player currentPlayer;

    public Game()
    {
        input = new Scanner(System.in);
        dice = new DiceCup();
        GUI.setNull_fields_allowed(true);

        fields = new GUI_Field[40];
        createField();
        gui = new GUI(fields);

        String welcome_msg = "Welcome to the amazing dice game, the rules are as following\n"
                + "Rule 0: Each player rolls two dice and add their total sum of eyes to the players points.\n"
                + "Rule 1: The player loses all his points if the player rolls  two 1s and the turn changes to the opposing players turn.\n"
                + "Rule 2: The player gets an extra turn if the player rolls two identical dice.\n"
                + "Rule 3: The player can win the game by rolling two 6s if the player also hit two 6s in the previous roll.\n"
                + "Rule 4: The player must roll two identical dice to win the game after scoring 40 points.\n";
        gui.showMessage(welcome_msg);

        player1 = new Player(gui);
        player2 = new Player(gui);
        gui.addPlayer(player1.getGuiPlayer());
        gui.addPlayer(player2.getGuiPlayer());

        this.movePlayer(player1);
        gui.showMessage("Okay " + player1.getName() + ", you start.");
        currentPlayer = player1;
        //this.movePlayer(player2);
    }


    public void rollDice()
    {
        dice.rollDice();
        System.out.println(dice.getDice1());
        System.out.println(dice.getDice2());
        gui.setDice(dice.getDice1(), dice.getDice2());
    }

    public void turn(Player player)
    {

        if (player.getPoint() >= 40)
        {
            System.out.println(player.getName() + " has to roll two identical to win the game.");
        }

        System.out.println(player.getName() + " write 'roll' to dice");
        if ("roll".equalsIgnoreCase(input.nextLine()))
        {

            if (dice.twoOne())
            {
                System.out.println("You rolled two 1's and lost all of your points.");
                player.resetPoint();
                return;
            }

            // player wins if they roll two sixes two turns in a row
            if (dice.getDice1() == 6 && dice.getDice2() == 6 && player.getlastdice1() == 6 && player.getLastdice2() == 6)
            {
                System.out.println("Game over");
                System.out.println(player.getName() + " has won the game");
                return;
            } else
            {
                player.setlastdice1(dice.getDice1());
                player.setlastdice2(dice.getDice2());
            }

            if (player.getPoint() < 40 && dice.isSimiliar())
            {
                System.out.println(player.getName() + " gets an extra turn!");
                player.addPoint(dice.getSum());
                System.out.println(player.getName() + " has " + player.getPoint() + " points.");
                return;
            } else if (player.getPoint() >= 40 && dice.isSimiliar())
            {
                System.out.println(player.getName() + " has won the game.");
                return;
            }
            player.addPoint(dice.getSum());


            if (player.getPoint() < 40)
            {
                System.out.println(player.getName() + " has " + player.getPoint() + " points.");
            } else
            {
                System.out.println(player.getName() + " has over 40 points, roll two identical to win");
            }

            System.out.println();

        } else
        {
            System.out.println("Please write roll :)");
            System.out.println();
        }

    }

    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    public void changePlayer()
    {
        if (currentPlayer==player1)
        {
            currentPlayer = player2;
        }
        else
        {
            currentPlayer = player1;
        }
    }

    public void movePlayer(Player player)
    {
        fields[0].setCar(player.getGuiPlayer(), true);
        gui.showMessage("Moving the car");
        fields[0].removeAllCars();
        fields[1].setCar(player.getGuiPlayer(), true);
    }

    public void createField()
    {
        // Get the absolut file path for images in a way that works cross platform
        String IMAGE_DIR_PATH = System.getProperty("user.dir") + File.separator +"img"  + File.separator;

        GUI_Shipping stuff = new GUI_Shipping(IMAGE_DIR_PATH+"images.jpg","titel", "subText", "description", "rent", Color.PINK , Color.CYAN);
        GUI_Shipping stuff2 = new GUI_Shipping(IMAGE_DIR_PATH+"face.png","titel", "subText", "description", "rent", Color.PINK , Color.CYAN);
        fields[0] = stuff;
        fields[1] = stuff2;
        System.out.println(stuff.getRent());

    }


}
