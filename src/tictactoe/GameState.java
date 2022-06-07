package tictactoe;

public enum GameState {
    RUN,
    DRAW,
    X_WIN,
    O_WIN,
    IMPOSSIBLE;

    public static void printRUN() {
        System.out.println("Game not finished");
    }

    public static void printDRAW() {
        System.out.println("Draw");
    }

    public static void printX_WIN() {
        System.out.println("X wins");
    }

    public static void printO_WIN() {
        System.out.println("O wins");
    }

    public static GameState getByChar(Character c) {
        if(c == 'O') return O_WIN;
        else if(c == 'X') return X_WIN;
        else return IMPOSSIBLE;
    }


}
