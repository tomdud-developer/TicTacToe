package tictactoe.exceptions;

public class CordsNotNumbersException extends Exception {
    public CordsNotNumbersException() {
        super("You should enter numbers!");
    }
}
