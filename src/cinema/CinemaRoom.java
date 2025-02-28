package cinema;

import cinema.dtos.Seat;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Class representing a cinema room.
 */
public class CinemaRoom {

    /**
     * How many rows of seats the room has.
     */
    private final int rows;

    /**
     * How many seats per room the room has.
     */
    private final int columns;

    /**
     * The configuration of seats with their properties.
     */
    private final Seat[][] seats;

    CinemaRoom(int rows, int columns, Seat[][] seats) {
        this.rows = rows;
        this.columns = columns;
        this.seats = seats;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    /**
     *
     * @return all seats in the room.
     */
    public List<Seat> getSeats() {
        return Arrays.stream(seats).flatMap(Stream::of).toList();
    }

    /**
     *
     * @return all free seats in the room.
     */
    public List<Seat> getFreeSeats() {
        return getSeats().stream()
                .filter(Predicate.not(Seat::isBooked))
                .toList();
    }

    /**
     *
     * @return all booked seats in the room.
     */
    public List<Seat> getBookedSeats() {
        return getSeats().stream()
                .filter(Seat::isBooked)
                .toList();
    }

    /**
     *
     * @param row
     * @param column
     * @return the seat in the given row and column.
     */
    public Seat getSeat(int row, int column) {
        // rows and columns must start from one
        if (row <= 0 || row > rows || column > columns) {
            throw new IllegalArgumentException("Invalid seat location.");
        }
        return seats[row - 1][column - 1];
    }

    /**
     *
     * @param token token associated with a sold seat.
     * @return a seat if the seat has been sold, null otherwise.
     */
    public Seat findSeatByToken(String token) {
        return getSeats().stream()
                .filter(seat -> seat.getToken().equals(token))
                .findFirst()
                .orElse(null);
    }
}
