package game;

import dice.DiceCup;
import gui_fields.GUI_Field;
import gui_fields.GUI_Jail;
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
    private boolean gameOver;

    public Game()
    {
        input = new Scanner(System.in);
        dice = new DiceCup();
        GUI.setNull_fields_allowed(true);

        fields = new GUI_Field[40];
        createField();
        gui = new GUI(fields, Color.WHITE);

        gameOver = false;

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
        gui.showMessage("Okay " + player1.getName() + ", you start.");
        currentPlayer = player1;
    }

    public Game(String playerName1, String playerName2)
    {
        input = new Scanner(System.in);
        dice = new DiceCup();
        GUI.setNull_fields_allowed(true);

        fields = new GUI_Field[40];
        createField();
        gui = new GUI(fields, Color.WHITE);

        gameOver = false;

        player1 = new Player(playerName1);
        player2 = new Player(playerName2);
        gui.addPlayer(player1.getGuiPlayer());
        gui.addPlayer(player2.getGuiPlayer());
        currentPlayer = player1;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void close() {
        this.gui.close();
    }

    public void rollDice()
    {
        dice.rollDice();
        gui.setDice(dice.getDice1(), dice.getDice2());
    }

    public void turn(Player player)
    {
        gui.showMessage("It is " + player.getName() + "'s turn");

        rollDice();
        movePlayer(player, dice.getSum());
        gameOver();

        if(dice.getSum() != 10)
        {
            changePlayer();
        }
    }

    public void gameOver()
    {
        if (currentPlayer.getPoint() >= 3000)
        {
            this.gameOver = true;
        }

    }
    public void endGame()
    {
        this.gameOver = true;
        gui.showMessage("The game is over, " + currentPlayer.getName() + " has won!!");
        gui.close();
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

    public void movePlayer(Player player, int roll)
    {
        Player otherPlayer;
        if (player==player1)
        {
            otherPlayer=player2;
        }
        else
        {
            otherPlayer=player1;
        }

        for (int i = 0; i<12; i++)
        {
            if(fields[i].hasCar(otherPlayer.getGuiPlayer()))
            {
                fields[i].removeAllCars();
                fields[i].setCar(otherPlayer.getGuiPlayer(), true);
            }
            else
            {
                fields[i].removeAllCars();
            }
        }

        fields[roll-1].setCar(player.getGuiPlayer(), true);
        player.addPoint(Integer.parseInt(fields[roll-1].getSubText()));
        gui.showMessage(fields[roll-1].getDescription());
    }

    public void createField()
    {
        // Get the absolut file path for images in a way that works cross platform
        String IMAGE_DIR_PATH = System.getProperty("user.dir") + File.separator +"img"  + File.separator;

        fields[0] = new GUI_Shipping(IMAGE_DIR_PATH+"start.png","Start", "  ", "You stand at the start of your journey. Good Luck!", "rent", Color.CYAN , Color.BLACK);
        fields[1] = new GUI_Shipping(IMAGE_DIR_PATH+"tower.png","Tower", "+250", "You found a gold stash in the nearby tower, You gain $250. Lucky you!", "+250", Color.GREEN , Color.BLACK);
        fields[2] = new GUI_Shipping(IMAGE_DIR_PATH+"crater.jpg","Crater", "-100", "One of your bags falls into the crater, you lose $100", "-100", Color.ORANGE , Color.BLACK);
        fields[3] = new GUI_Shipping(IMAGE_DIR_PATH+"palacegates.png","Palace gates", "+100", " You sell your goods on the market beside palace gates, you earned $100", "+100", Color.GREEN , Color.BLACK);
        fields[4] = new GUI_Shipping(IMAGE_DIR_PATH+"colddesert.jpg","Cold desert", "-20", "You had to pay $20 to a beduin to access the oasis in a cold desert.", "-20", Color.ORANGE , Color.BLACK);
        fields[5] = new GUI_Shipping(IMAGE_DIR_PATH+"walledcity.jpg","Walled City", "+180", "You enter the rich walled city. They give you $180! Nice people.", "+180", Color.GREEN , Color.BLACK);
        fields[6] = new GUI_Shipping(IMAGE_DIR_PATH+"monastery.jpg","Monastery", "0", "You visit a monastery. Nothing happens here. Boring.", "0", Color.YELLOW , Color.BLACK);
        fields[7] = new GUI_Shipping(IMAGE_DIR_PATH+"blackcave.png","Black Cave", "-70", "You get robbed while sleeping in a black cave, angry bandits took $70", "-70", Color.ORANGE , Color.BLACK);
        fields[8] = new GUI_Shipping(IMAGE_DIR_PATH+"hutsinthemountain.jpg","Huts in the mountain", "+60", "You help elderly people living in the mountains with wood chopping. As a reward they give you $60", "+60", Color.GREEN , Color.BLACK);
        fields[9] = new GUI_Shipping(IMAGE_DIR_PATH+"werewall.jpg","The Werewall", "-80", "The werewolves demand payment from you for not eating you alive. You pay them $80 and run as fast as you can from the Werewall. You get an extra turn", "-80", Color.YELLOW , Color.BLACK);
        fields[10] = new GUI_Shipping(IMAGE_DIR_PATH+"thepit.jpg","The Pit", "-50", "While falling into the pit your gold stash opens and you lose $50. Watch out next time maybe?", "-50", Color.ORANGE , Color.BLACK);
        fields[11] = new GUI_Shipping(IMAGE_DIR_PATH+"goldmine.jpg","Goldmine", "+650", "While visiting an old goldmine you find a big chunk of gold! You sell it for $650 in the nearby city. You're rich now. Wow.", "+650", Color.green , Color.BLACK);
    }


}
