package tictactoe.players;

import tictactoe.GameGrid;
import tictactoe.exceptions.*;


import static tictactoe.GameLoop.scanner;

public class User extends Player{

    public User(Character sign) {
        this.sign = sign;
    }

    @Override
    public void makeMove(GameGrid gameGrid) throws Exception {
        int row;
        int col;
        Boolean success = false;
        while(!success) {
            try {
                System.out.println("Enter the coordinates: ");
                String cordString = scanner.nextLine().trim();
                String[] arr = cordString.split(" ");
                if (arr.length != 2) throw new CordsNotNumbersException();

                try {
                    row = Integer.parseInt(arr[0]) - 1;
                    col = Integer.parseInt(arr[1]) - 1;
                } catch (NumberFormatException e) {
                    throw new CordsNotNumbersException();
                }

                if (row < 0 || row > 2 || col < 0 || col > 2) throw new CordsOutOfBoundsException();
                if (!gameGrid.isCellEmpty(row, col)) throw new OccupiedFieldException();
                else gameGrid.makeMove(row, col, sign);

                success = true;
            } catch (NumberFormatException | CordsNotNumbersException | CordsOutOfBoundsException | OccupiedFieldException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
