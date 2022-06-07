package tictactoe;

import tictactoe.exceptions.BadInputCommandException;
import tictactoe.players.AIPlayer;
import tictactoe.exceptions.*;
import tictactoe.players.DifficultyLevel;
import tictactoe.players.Player;
import tictactoe.players.User;

import java.util.Scanner;

public class GameLoop implements Runnable {

    private final GameGrid gameGrid;
    private GameState state;
    public static final Scanner scanner = new Scanner(System.in);;

    private Player player1;
    private Player player2;

    public GameLoop() {
        state = GameState.RUN;
        gameGrid = new GameGrid();
    }

    @Override
    public void run() {
        while(true) {
            inputCommand();
            gameGridClear();
            gameGrid.printCurrentState();
            while (state == GameState.RUN) {
                try {
                    if(gameGrid.whoHasNextMove() == 'X')
                        player1.makeMove(gameGrid);
                    else
                        player2.makeMove(gameGrid);
                    refreshStateGame();
                    gameGrid.printCurrentState();
                    printStateGame();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void printStateGame() {
        switch(state) {
            //case RUN: GameState.printRUN(); break;
            case O_WIN: GameState.printO_WIN();break;
            case X_WIN: GameState.printX_WIN();break;
            case DRAW: GameState.printDRAW();break;
        }
    }

    public void refreshStateGame() {
        state = gameGrid.checkWin();
    }

    public void gameGridClear() {
        gameGrid.gameGridInit("         ");
        state = gameGrid.checkWin();
    }

    private void inputCommand() {
        boolean success = false;
        while (!success) {
            try {
                System.out.println("Input command: ");
                String[] commands = scanner.nextLine().trim().split(" ");

                switch (commands.length) {
                    case 1:
                        if (commands[0].equals("exit")) System.exit(0);
                        else throw new BadInputCommandException();
                        break;

                    case 3:
                        if (commands[0].equals("start") && Player.isAvailavleType(commands[1]) && Player.isAvailavleType(commands[2])) {
                            if (commands[1].equals("user")) player1 = new User('X');
                            else {
                                player1 = new AIPlayer('X', DifficultyLevel.getByString(commands[1].toLowerCase()));
                            }
                            if (commands[2].equals("user")) player2 = new User('O');
                            else {
                                player2 = new AIPlayer('O', DifficultyLevel.getByString(commands[2].toLowerCase()));
                            }
                        } else throw new BadInputCommandException();
                        break;
                    default:
                        throw new BadInputCommandException();
                }
                success = true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void enterCells() {
        System.out.println("Enter the cells: ");
        String cells = scanner.nextLine();
        gameGrid.gameGridInit(cells);
        state = gameGrid.checkWin();
    }

}
