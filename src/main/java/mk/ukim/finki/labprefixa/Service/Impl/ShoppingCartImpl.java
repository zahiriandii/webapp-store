package mk.ukim.finki.labprefixa.Service.Impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.labprefixa.Model.Enumerations.ShoppingCartStatus;
import mk.ukim.finki.labprefixa.Model.Exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.labprefixa.Model.Exceptions.UserNotFoundException;
import mk.ukim.finki.labprefixa.Model.ShoppingCart;
import mk.ukim.finki.labprefixa.Model.TicketOrder;
import mk.ukim.finki.labprefixa.Model.User;
import mk.ukim.finki.labprefixa.Model.Movie;
import mk.ukim.finki.labprefixa.Repository.Jpa.MovieRepository;
import mk.ukim.finki.labprefixa.Repository.Jpa.ShoppingCartRepository;
import mk.ukim.finki.labprefixa.Repository.Jpa.UserRepository;
import mk.ukim.finki.labprefixa.Service.ShoppingCartService;
import mk.ukim.finki.labprefixa.Service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartImpl implements ShoppingCartService {
    private final ShoppingCartRepository inMemoryShoppingCartRepository;
    private final UserRepository inMemoryUserRepository;
    private final MovieRepository inMemoryMovieRepository;


    public ShoppingCartImpl(ShoppingCartRepository inMemoryShoppingCartRepository, UserRepository inMemoryUserRepository, MovieRepository inMemoryMovieRepository) {
        this.inMemoryShoppingCartRepository = inMemoryShoppingCartRepository;
        this.inMemoryUserRepository = inMemoryUserRepository;
        this.inMemoryMovieRepository = inMemoryMovieRepository;

    }

    @Override
    public List<TicketOrder> listAllTicketsInShoppingCart(Long cartId) {
        ShoppingCart shoppingCart = inMemoryShoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ShoppingCartNotFoundException(cartId));
        return shoppingCart.getTicketOrders();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username)  {

        return this.inMemoryShoppingCartRepository
                .findByUserUsernameAndStatus(username, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    User user = this.inMemoryUserRepository.findByUsername(username)
                            .orElseThrow(() -> new UserNotFoundException(username));
                    ShoppingCart shoppingCart = new ShoppingCart(user);
                    return this.inMemoryShoppingCartRepository.save(shoppingCart);
                });

//        User user= (User) this.inMemoryUserRepository.findByUsername(username)
//                .orElseThrow(()->new UserNotFoundException(username));
//        return inMemoryShoppingCartRepository.findByUsernameAndStatus(user.getUsername(), ShoppingCartStatus.CREATED)
//                .orElseGet(()->{
//                    ShoppingCart cart=new ShoppingCart(user);
//                    return this.inMemoryShoppingCartRepository.save(cart);
//                });
    }

    @Override
    public ShoppingCart addMovieToShoppingCart(String username, Long movieId, Long numberOfTickets, LocalDateTime dateCreated) {
        ShoppingCart shoppingCart= this.getActiveShoppingCart(username);
        Movie movie= inMemoryMovieRepository.findById(movieId)
                .orElseThrow(()->new RuntimeException("Movie not found"));
        TicketOrder ticketOrder = new TicketOrder(shoppingCart.getUser(),movie,numberOfTickets,dateCreated);
        shoppingCart.getTicketOrders().add(ticketOrder);
        return inMemoryShoppingCartRepository.save(shoppingCart);
    }
}
