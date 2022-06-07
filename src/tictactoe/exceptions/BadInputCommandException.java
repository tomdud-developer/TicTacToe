package tictactoe.exceptions;

public class BadInputCommandException extends Exception {
    public BadInputCommandException() {
        super("Bad parameters!");
    }
}
