package mk.ukim.finki.labprefixa.Service;

import mk.ukim.finki.labprefixa.Model.Movie;
import mk.ukim.finki.labprefixa.Model.TicketOrder;
import mk.ukim.finki.labprefixa.Model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketOrderService
{
    TicketOrder placeOrder(User user, Movie movie, Long numberOfTickets, LocalDateTime dateCreated);


    List<TicketOrder> getOrdersWithinTimeInterval(LocalDateTime from, LocalDateTime to);

    List<TicketOrder> listAllOrders();
}
