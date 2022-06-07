package tictactoe.exceptions;

public class CordsOutOfBoundsException extends Exception {
    public CordsOutOfBoundsException() {
        super("Coordinates should be from 1 to 3!");
    }
}