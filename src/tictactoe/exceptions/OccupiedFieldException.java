package tictactoe.exceptions;

public class OccupiedFieldException extends Exception {
    public OccupiedFieldException() {
        super("This cell is occupied! Choose another one!");
    }
}
