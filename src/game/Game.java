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
        gui = new GUI(fields, Color.WHITE);

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

        GUI_Shipping stuff = new GUI_Shipping(IMAGE_DIR_PATH+"start.png","Start", "  ", "You stand at the start of your journey. Good Luck!", "rent", Color.CYAN , Color.BLACK);
        GUI_Shipping stuff1 = new GUI_Shipping(IMAGE_DIR_PATH+"tower.png","Tower", "+250", "You found a gold stash in the nearby tower, You gain 250$. Lucky you!", "+250", Color.GREEN , Color.BLACK);
        GUI_Shipping stuff2 = new GUI_Shipping(IMAGE_DIR_PATH+"crater.jpg","Crater", "-100", "One of your bags falls into the crater, you lose 100$", "-100", Color.ORANGE , Color.BLACK);
        GUI_Shipping stuff3 = new GUI_Shipping(IMAGE_DIR_PATH+"palacegates.png","Palace gates", "+100", " You sell your goods on the maked beside palace gates, you earned 100$", "+100", Color.GREEN , Color.BLACK);
        GUI_Shipping stuff4 = new GUI_Shipping(IMAGE_DIR_PATH+"colddesert.jpg","Cold desert", "-20", "You had to pay 20$ to a beduin to access the oasis on a cold dessert.", "-20", Color.ORANGE , Color.BLACK);
        GUI_Shipping stuff5 = new GUI_Shipping(IMAGE_DIR_PATH+"walledcity.jpg","Walled City", "+180", "You enter the rich walled city. They give you 180$! Nice people. ", "+180", Color.GREEN , Color.BLACK);
        GUI_Shipping stuff6 = new GUI_Shipping(IMAGE_DIR_PATH+"monastery.jpg","Monastery", "0", "You visit a monastery. Nothing happens here. Boring.", "0", Color.YELLOW , Color.BLACK);
        GUI_Shipping stuff7 = new GUI_Shipping(IMAGE_DIR_PATH+"blackcave.png","Black Cave", "-70", "You get robbed while sleeping in a black cave, angry bandits took 70$", "-70", Color.ORANGE , Color.BLACK);
        GUI_Shipping stuff8 = new GUI_Shipping(IMAGE_DIR_PATH+"hutsinthemountain.jpg","Huts in the mountain", "+60", "You help elderly people living in the mountains with wood chopping. In reward they give you 60$", "+60", Color.GREEN , Color.BLACK);
        GUI_Shipping stuff9 = new GUI_Shipping(IMAGE_DIR_PATH+"werewall.jpg","The Werewall", "-80", "The werewolfs  demand payment from you for not eating you alive. You pay them 80$ and run as fast as you can from the werewall. You get an extra turn", "-80", Color.YELLOW , Color.BLACK);
        GUI_Shipping stuff10 = new GUI_Shipping(IMAGE_DIR_PATH+"thepit.jpg","The Pit", "-50", "While falling into the pit your gold stash opens and you lose 50$. Watch out next time maybe? ", "-150", Color.ORANGE , Color.BLACK);
        GUI_Shipping stuff11 = new GUI_Shipping(IMAGE_DIR_PATH+"goldmine.jpg","Goldmine", "+650", "While visiting an old goldmine you find a big chunk of gold! You sell it for 650$ in the nearby city. You're rich now. Wow.", "+650", Color.green , Color.BLACK);
        fields[0] = stuff;
        fields[1] = stuff1;
        fields[2] = stuff2;
        fields[3] = stuff3;
        fields[4] = stuff4;
        fields[5] = stuff5;
        fields[6] = stuff6;
        fields[7] = stuff7;
        fields[8] = stuff8;
        fields[9] = stuff9;
        fields[10] = stuff10;
        fields[11] = stuff11;

        System.out.println(stuff.getRent());

    }


}
