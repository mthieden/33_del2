package game;

import dice.DiceCup;
import gui_fields.GUI_Field;
import gui_main.GUI;
import player.Player;

import java.util.Scanner;

public class Game
{

    private final Player player1;
    private final Player player2;
    private final DiceCup dice;
    private final Scanner input;
    private GUI gui;
    private GUI_Field[] fields;

    public Game()
    {
        input = new Scanner(System.in);
        dice = new DiceCup();
        GUI.setNull_fields_allowed(true);

        fields = new GUI_Field[40];
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

        //this.movePlayer(player1);
        gui.showMessage("Okay " + player1.getName() + ", you start.");
        //this.movePlayer(player2);
    }


    public void turn1()
    {

        if (player1.getPoint() >= 40)
        {
            System.out.println(player1.getName() + " has to roll two identical to win the game.");
        }

        System.out.println(player1.getName() + " write 'roll' to dice");
        if ("roll".equalsIgnoreCase(input.nextLine()))
        {
            dice.rollDice();
            System.out.println(dice.getDice1());
            System.out.println(dice.getDice2());
            gui.setDice(dice.getDice1(), dice.getDice2());

            if (dice.twoOne())
            {
                System.out.println("You rolled two 1's and lost all of your points.");
                player1.resetPoint();
                turn2();
                return;
            }

            // player wins if they roll two sixes two turns in a row
            if (dice.getDice1() == 6 && dice.getDice2() == 6 && player1.getlastdice1() == 6 && player1.getLastdice2() == 6)
            {
                System.out.println("Game over");
                System.out.println(player1.getName() + " has won the game");
                return;
            } else
            {
                player1.setlastdice1(dice.getDice1());
                player1.setlastdice2(dice.getDice2());
            }

            if (player1.getPoint() < 40 && dice.isSimiliar())
            {
                System.out.println(player1.getName() + " gets an extra turn!");
                player1.addPoint(dice.getSum());
                System.out.println(player1.getName() + " has " + player1.getPoint() + " points.");
                turn1();
                return;
            } else if (player1.getPoint() >= 40 && dice.isSimiliar())
            {
                System.out.println(player1.getName() + " has won the game.");
                return;
            }
            player1.addPoint(dice.getSum());


            if (player1.getPoint() < 40)
            {
                System.out.println(player1.getName() + " has " + player1.getPoint() + " points.");
            } else
            {
                System.out.println(player1.getName() + " has over 40 points, roll two identical to win");
            }

            System.out.println();
            turn2();

        } else
        {
            System.out.println("Please write roll :)");
            System.out.println();
            turn1();
        }

    }

    private void turn2()
    {

        if (player2.getPoint() >= 40)
        {
            System.out.println(player2.getName() + " has to roll two identical to win the game.");
        }

        System.out.println(player2.getName() + " write 'roll' to dice");

        if ("roll".equalsIgnoreCase(input.nextLine()))
        {
            dice.rollDice();
            System.out.println(dice.getDice1());
            System.out.println(dice.getDice2());
            gui.setDice(dice.getDice1(), dice.getDice2());

            if (dice.twoOne())
            {
                System.out.println("You rolled two 1's and lost all of your points.");
                player2.resetPoint();
                turn1();
                return;
            }

            // player wins if they roll two sixes two turns in a row
            if (dice.getDice1() == 6 && dice.getDice2() == 6 && player2.getlastdice1() == 6 && player2.getLastdice2() == 6)
            {
                System.out.println("Game over");
                System.out.println(player2.getName() + " has won the game");
                return;
            } else
            {
                player2.setlastdice1(dice.getDice1());
                player2.setlastdice2(dice.getDice2());
            }

            if (player2.getPoint() < 40 && dice.isSimiliar())
            {
                System.out.println(player2.getName() + " gets an extra turn!");
                player2.addPoint(dice.getSum());
                System.out.println(player2.getName() + " has " + player2.getPoint() + " points.");
                turn2();
                return;
            } else if (player2.getPoint() >= 40 && dice.isSimiliar())
            {
                System.out.println(player2.getName() + " has won the game");
                return;
            }
            player2.addPoint(dice.getSum());

            if (player2.getPoint() < 40)
            {
                System.out.println(player2.getName() + " has " + player2.getPoint() + " points.");
            } else
            {
                System.out.println(player2.getName() + " has over 40 points, roll two identical to win");
            }


            System.out.println();
            turn1();

        } else
        {
            System.out.println("Please write roll :)");
            System.out.println();
            turn2();
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
       
    }


}
