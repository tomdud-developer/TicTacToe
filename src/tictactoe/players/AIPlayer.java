package tictactoe.players;

import tictactoe.GameGrid;
import tictactoe.GameState;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIPlayer extends Player {
    private DifficultyLevel level;

    public AIPlayer(Character sign, DifficultyLevel level) {
        this.level = level;
        this.sign = sign;
    }

    public void makeMove(GameGrid gameGrid) throws Exception {
        switch (level) {
            case EASY: makeMoveEasy(gameGrid); break;
            case MEDIUM: makeMoveMedium(gameGrid); break;
            case HARD: makeMoveHard(gameGrid); break;
        }
    }

    private void makeMoveMedium(GameGrid gameGrid) {
        Character oppositeSign = getOppositeSign(sign);
        //check rows
        for (int row = 0; row < 3; row++) {
            int sumSign = 0;
            int sumOppositeSign = 0;
            int sumEmpty = 0;
            int[] emptyCords = new int[2];
            for (int col = 0; col < 3; col++) {
                if (gameGrid.getCharacterAt(row, col) == sign) sumSign++;
                if (gameGrid.getCharacterAt(row, col) == oppositeSign) sumOppositeSign++;
                if (gameGrid.getCharacterAt(row, col) == ' ' || gameGrid.getCharacterAt(row, col) == '_') {
                    sumEmpty++;
                    emptyCords[0] = row;
                    emptyCords[1] = col;
                }
            }
            if((sumSign == 2 || sumOppositeSign == 2) && sumEmpty == 1) {
                gameGrid.makeMove(emptyCords[0], emptyCords[1], sign);
                return;
            }
        }
        //check columns
        for (int col = 0; col < 3; col++) {
            int sumSign = 0;
            int sumOppositeSign = 0;
            int sumEmpty = 0;
            int[] emptyCords = new int[2];
            for (int row = 0; row < 3; row++) {
                if (gameGrid.getCharacterAt(row, col) == sign) sumSign++;
                if (gameGrid.getCharacterAt(row, col) == oppositeSign) sumOppositeSign++;
                if (gameGrid.getCharacterAt(row, col) == ' ' || gameGrid.getCharacterAt(row, col) == '_') {
                    sumEmpty++;
                    emptyCords[0] = row;
                    emptyCords[1] = col;
                }
            }
            if((sumSign == 2 || sumOppositeSign == 2) && sumEmpty == 1) {
                gameGrid.makeMove(emptyCords[0], emptyCords[1], sign);
                return;
            }
        }

        //check cross 1
        int sumSign = 0;
        int sumOppositeSign = 0;
        int sumEmpty = 0;
        int[] emptyCords = new int[2];
        for (int row = 0, col =0; row < 3; row++, col++) {
            if (gameGrid.getCharacterAt(row, col) == sign) sumSign++;
            if (gameGrid.getCharacterAt(row, col) == oppositeSign) sumOppositeSign++;
            if (gameGrid.getCharacterAt(row, col) == ' ' || gameGrid.getCharacterAt(row, col) == '_') {
                sumEmpty++;
                emptyCords[0] = row;
                emptyCords[1] = col;
            }
        }
        if((sumSign == 2 || sumOppositeSign == 2) && sumEmpty == 1) {
            gameGrid.makeMove(emptyCords[0], emptyCords[1], sign);
            return;
        }

        //check cross 2
        sumSign = 0;
        sumOppositeSign = 0;
        sumEmpty = 0;
        emptyCords = new int[2];
        for (int row = 0, col = 2; row < 3; row++, col--) {
            if (gameGrid.getCharacterAt(row, col) == sign) sumSign++;
            if (gameGrid.getCharacterAt(row, col) == oppositeSign) sumOppositeSign++;
            if (gameGrid.getCharacterAt(row, col) == ' ' || gameGrid.getCharacterAt(row, col) == '_') {
                sumEmpty++;
                emptyCords[0] = row;
                emptyCords[1] = col;
            }
        }
        if((sumSign == 2 || sumOppositeSign == 2) && sumEmpty == 1) {
            gameGrid.makeMove(emptyCords[0], emptyCords[1], sign);
            return;
        }
        //if there is not upcoming win or lose
        makeMoveEasy(gameGrid);
    }

    private void makeMoveEasy(GameGrid gameGrid) {
        Boolean success = false;
        while(!success) {
            try {
                Random rand = new Random();
                int row = rand.nextInt(3);
                int col = rand.nextInt(3);
                if (!gameGrid.isCellEmpty(row, col)) throw new Exception("");
                else gameGrid.makeMove(row, col, sign);
                System.out.println("Making move level \"easy\"");
                success = true;
            } catch (Exception e) {
            }
        }
    }

    private void makeMoveHard(GameGrid gameGrid) {
        List<Coordinate> emptyCellsList = getAllEmptyCells(gameGrid);
        Coordinate bestMove = emptyCellsList.get(0);
        int bestResult = -999;

        for (Coordinate cell : emptyCellsList) {
            GameGrid newGame = new GameGrid(gameGrid.getGrid());
            newGame.makeMove(cell.row, cell.col, this.sign);
            int result = minimax(newGame, false);
            if(result > bestResult) {
                bestResult = result;
                bestMove = cell;
            }
        }

        gameGrid.makeMove(bestMove.row, bestMove.col, sign);
        System.out.println("Making move level \"hard\"");
    }

    private int minimax(GameGrid gameGrid, Boolean isMaximizing) {
        if(gameGrid.checkWin() == GameState.getByChar(this.sign))
            return 1;
        else if(gameGrid.checkWin() == GameState.getByChar(Player.getOppositeSign(this.sign)))
            return -1;
        else if(gameGrid.checkWin() == GameState.DRAW)
            return 0;

        List<Coordinate> emptyCellsList = getAllEmptyCells(gameGrid);
        int bestResultMaximizing = -999;
        int bestResultMinimizing= 999;

        for (Coordinate cell : emptyCellsList) {
            GameGrid newGame = new GameGrid(gameGrid.getGrid());
            newGame.makeMove(cell.row, cell.col, isMaximizing ? this.sign : Player.getOppositeSign(this.sign));
            int result = minimax(newGame, !isMaximizing);

            if(isMaximizing) bestResultMaximizing = Math.max(result, bestResultMaximizing);
            else bestResultMinimizing = Math.min(result, bestResultMinimizing);
        }

        return isMaximizing ? bestResultMaximizing : bestResultMinimizing;
    }

    private List<Coordinate> getAllEmptyCells(GameGrid grid) {
        List<Coordinate> list = new ArrayList<>();
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++)
                if (grid.isCellEmpty(row, col)) list.add(new Coordinate(row, col));
        return list;
    }

}
