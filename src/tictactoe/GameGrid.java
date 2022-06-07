package tictactoe;

import java.util.Arrays;

public class GameGrid implements Cloneable {

    Character[][] grid;

    public GameGrid() {
        grid = new Character[3][3];
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                grid[row][col] = ' ';
    }

    public GameGrid(Character[][] grid) {
        this.grid = grid.clone();
    }

    public GameGrid(String str) {
        grid = new Character[3][3];
        String[] arr = str.split("");

        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                grid[row][col] = arr[row * 3 + col].charAt(0);
    }

    public void gameGridInit(String str) {
        grid = new Character[3][3];
        String[] arr = str.split("");

        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                grid[row][col] = arr[row * 3 + col].charAt(0);
    }

    public void printCurrentState() {
        System.out.println("---------");
        for (int row = 0; row < 3; row++) {
            System.out.print("| ");
            for (int col = 0; col < 3; col++)
                System.out.printf("%c ", grid[row][col]);
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    public void makeMove(int row, int col, char sign) {

        grid[row][col] = sign;
    }

    public GameState checkWin() {
        if(!checkPossibility()) return GameState.IMPOSSIBLE;
        //check rows
        for (int row = 0; row < 3; row++) {
            int sumX = 0;
            int sumO = 0;
            for (int col = 0; col < 3; col++) {
                if (grid[row][col] == 'X') sumX++;
                if (grid[row][col] == 'O') sumO++;
            }
            if(sumX == 3) return GameState.X_WIN;
            if(sumO == 3) return GameState.O_WIN;
        }

        //check columns
        for (int col = 0; col < 3; col++) {
            int sumX = 0;
            int sumO = 0;
            for (int row = 0; row < 3; row++) {
                if (grid[row][col] == 'X') sumX++;
                if (grid[row][col] == 'O') sumO++;
            }
            if(sumX == 3) return GameState.X_WIN;
            if(sumO == 3) return GameState.O_WIN;
        }

        //check cross1
        int sumX = 0;
        int sumO = 0;
        for (int row = 0, col = 0; row < 3; col++, row++)
            if (grid[row][col] == 'X') sumX++;
            else if (grid[row][col] == 'O') sumO++;
        if(sumX == 3) return GameState.X_WIN;
        if(sumO == 3) return GameState.O_WIN;

        //check cross2
        sumX = 0;
        sumO = 0;
        for (int row = 0, col = 2; row < 3; col--, row++)
            if (grid[row][col] == 'X') sumX++;
            else if (grid[row][col] == 'O') sumO++;
        if(sumX == 3) return GameState.X_WIN;
        if(sumO == 3) return GameState.O_WIN;

        if(Arrays.stream(grid).flatMap(Arrays::stream).filter(c -> c == '_' || c == ' ').count() >= 1)
            return GameState.RUN;

        return GameState.DRAW;
    }

    public Boolean checkPossibility() {
        int sumX = 0;
        int sumO = 0;
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (grid[row][col] == 'X') sumX++;
                else if (grid[row][col] == 'O') sumO++;

        return !(Math.abs(sumX - sumO) > 1);
    }

    public Character whoHasNextMove() {
        int sumX = 0;
        int sumO = 0;
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (grid[row][col] == 'X') sumX++;
                else if (grid[row][col] == 'O') sumO++;

        if(sumX > sumO) return 'O';
        else return 'X';
    }

    public Boolean isCellEmpty (int row, int col) {
        return (this.getCharacterAt(row, col) == '_' || this.getCharacterAt(row, col) == ' ');
    }

    public char getCharacterAt(int row, int col) {
        return grid[row][col];
    }

    public Object clone() throws CloneNotSupportedException
    {
        GameGrid t = (GameGrid)super.clone();
        t.grid = this.grid.clone();
        return t;
    }

    public Character[][] getGrid() {
        Character[][] copiedArray = new Character[3][3];
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                copiedArray[row][col] = grid[row][col].charValue();
        return copiedArray;
    }

}
