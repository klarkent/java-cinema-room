package cinema.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class Seat {

    private int row;

    private int column;

    private int price;

    private String token;

    public Seat(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    @JsonIgnore
    public boolean isBooked() {
        return token != null;
    }

    /**
     * Marks the seat as booked.
     */
    public void book() {
        if (isBooked()) {
            throw new IllegalStateException("Cannot book already booked seat!");
        }
        this.token = UUID.randomUUID().toString();
    }

    /**
     * Cancels the booking for the seat.
     */
    public void cancelBooking() {
        this.token = null;
    }

    @JsonIgnore
    public String getToken() {
        return token;
    }
}
