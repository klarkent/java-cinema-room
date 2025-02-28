package cinema.exceptions;

public class SeatAlreadyPurchasedException extends RuntimeException {

    public SeatAlreadyPurchasedException(String message) {
        super(message);
    }
}
