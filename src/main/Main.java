package main;
import game.Game;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.turn(game.getCurrentPlayer());
    }

}
