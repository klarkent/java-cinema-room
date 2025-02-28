package cinema.exceptions;

public class WrongSeatSelectionException extends RuntimeException {

    public WrongSeatSelectionException(String message) {
        super(message);
    }
}
