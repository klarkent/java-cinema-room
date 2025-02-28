package cinema.controller;

import cinema.exceptions.SeatAlreadyPurchasedException;
import cinema.exceptions.WrongPasswordException;
import cinema.exceptions.WrongSeatSelectionException;
import cinema.exceptions.WrongTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(WrongSeatSelectionException.class)
    public ResponseEntity<CustomErrorMessage> handleWrongSeatSelection(WrongSeatSelectionException e, WebRequest request) {
        CustomErrorMessage body = new CustomErrorMessage(e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeatAlreadyPurchasedException.class)
    public ResponseEntity<CustomErrorMessage> handleSeatAlreadyPurchased(SeatAlreadyPurchasedException e, WebRequest request) {
        CustomErrorMessage body = new CustomErrorMessage(e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongTokenException.class)
    public ResponseEntity<CustomErrorMessage> handleWrongTokenException(WrongTokenException e, WebRequest request) {
        CustomErrorMessage body = new CustomErrorMessage(e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<CustomErrorMessage> handleWrongPasswordException(WrongPasswordException e, WebRequest request) {
        CustomErrorMessage body = new CustomErrorMessage(e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }
}
