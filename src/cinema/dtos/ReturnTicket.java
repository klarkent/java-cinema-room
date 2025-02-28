package cinema.dtos;

public class ReturnTicket {
    private Ticket ticket;

    public ReturnTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
