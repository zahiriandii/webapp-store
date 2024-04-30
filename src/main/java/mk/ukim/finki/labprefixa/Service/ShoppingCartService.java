package mk.ukim.finki.labprefixa.Service;

import mk.ukim.finki.labprefixa.Model.Exceptions.UserNotFoundException;
import mk.ukim.finki.labprefixa.Model.ShoppingCart;
import mk.ukim.finki.labprefixa.Model.TicketOrder;

import java.time.LocalDateTime;
import java.util.List;

public interface ShoppingCartService
{
    List<TicketOrder> listAllTicketsInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username) throws UserNotFoundException;
    ShoppingCart addMovieToShoppingCart(String username, Long movieId, Long numberOfTickets, LocalDateTime dateCreated);
}
