package cinema.controller;

import cinema.*;
import cinema.dtos.*;
import cinema.exceptions.SeatAlreadyPurchasedException;
import cinema.exceptions.WrongPasswordException;
import cinema.exceptions.WrongSeatSelectionException;
import cinema.exceptions.WrongTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CinemaRoomController {

    @Autowired
    private CinemaRoom cinemaRoom;

    @GetMapping("/seats")
    public Map<String, Object> getFreeSeats() {
        Map<String, Object> body = new HashMap<>();
        body.put("rows", cinemaRoom.getRows());
        body.put("columns", cinemaRoom.getColumns());
        body.put("seats", cinemaRoom.getFreeSeats());
        return body;
    }

    @PostMapping("/return")
    public ReturnTicket returnTicket(@RequestBody ReturnToken token) {
        Seat seat = cinemaRoom.findSeatByToken(token.getToken());

        if (seat != null) {
            Ticket ticket = new Ticket(seat.getRow(), seat.getColumn(), seat.getPrice());
            seat.cancelBooking();
            return new ReturnTicket(ticket);
        }

        throw new WrongTokenException("Wrong token!");
    }

    @PostMapping("/purchase")
    public PurchasedTicket purchase(@RequestBody Ticket ticket) {
        int row = ticket.getRow();
        int column = ticket.getColumn();

        if (row > cinemaRoom.getRows() || column > cinemaRoom.getColumns() || row < 1 || column < 1) {
            throw new WrongSeatSelectionException("The number of a row or a column is out of bounds!");
        }

        Seat seat = cinemaRoom.getSeat(row, column);

        if (seat.isBooked()) {
            throw new SeatAlreadyPurchasedException("The ticket has been already purchased!");
        }

        ticket.setPrice(seat.getPrice());
        seat.book();
        return new PurchasedTicket(seat.getToken(), ticket);
    }

    @GetMapping("/stats")
    public Map<String, Integer> stats(@RequestParam(required = false) String password) {
        if (password == null || !password.equals("super_secret")) {
            throw new WrongPasswordException("The password is wrong!");
        }

        List<Seat> seats = cinemaRoom.getSeats();
        // calculate income, available and purchased seats
        List<Seat> purchasedSeatsList = seats.stream().filter(Seat::isBooked).toList();
        int purchased = purchasedSeatsList.size();
        int available = seats.size() - purchased;
        Optional<Integer> income = purchasedSeatsList.stream().map(Seat::getPrice).reduce(Integer::sum);

        Map<String, Integer> body = new HashMap<>();
        body.put("available", available);
        body.put("purchased", purchased);
        body.put("income", income.orElse(0));

        return body;
    }
}
