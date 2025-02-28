package cinema;

import cinema.dtos.Seat;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Provides CinemaRoom classes with pre-defined size.
 */
@Component
public class CinemaRoomFactory {

    /**
     * Creates an object representing the only room in the cinema.
     *
     * @return the cinema room
     */
    @Bean
    public CinemaRoom getRoom() {
        int rows = 9;
        int columns = 9;
        int farSeatPrice = 8;
        int closeSeatPrice = 10;
        int closeSeatThreshold = rows / 2;
        Seat[][] seats = new Seat[rows][columns];
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                int price = i + 1 <= closeSeatThreshold ? closeSeatPrice : farSeatPrice;
                seats[i][j] = new Seat(i + 1, j + 1, price);
            }
        }

        return new CinemaRoom(rows, columns, seats);
    }
}
