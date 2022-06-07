package tictactoe.players;

import tictactoe.GameGrid;
import java.util.Arrays;
import java.util.List;

abstract public class Player {
    public Character sign;
    abstract public void makeMove(GameGrid grid) throws Exception;

    public static Boolean isAvailavleType(String str) {
        List<String> list = Arrays.asList("user", "easy", "medium", "hard");
        return list.contains(str);
    }

    public static Character getOppositeSign(char c) {
        if(c == 'X') return 'O';
        else if(c == 'O') return 'X';
        else return '_';
    }
}
