package cinema.dtos;

public class PurchasedTicket {

    private String token;

    private Ticket ticket;

    public PurchasedTicket(String token, Ticket ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public String getToken() {
        return token;
    }
}
