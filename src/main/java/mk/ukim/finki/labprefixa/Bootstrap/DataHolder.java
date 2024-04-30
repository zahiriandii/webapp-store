package mk.ukim.finki.labprefixa.Bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.labprefixa.Model.Movie;
import mk.ukim.finki.labprefixa.Model.Production;
import mk.ukim.finki.labprefixa.Model.ShoppingCart;
import mk.ukim.finki.labprefixa.Model.TicketOrder;
import mk.ukim.finki.labprefixa.Model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder
{
    public static List<User> users= new ArrayList<>();
    public static List<Movie> movies;
    public static List<Production> productions;
    public static List<ShoppingCart> shoppingCarts= new ArrayList<>();
    public static List<TicketOrder> ticketOrders= new ArrayList<>();

//    @PostConstruct
//    public void init ()
//    {
//       // users.add(new User("An","Andi","Zahiri","an",LocalDate.of(2000,5,24)));
//
////        productions = new ArrayList<>();
////        productions.add(new Production("Castle Rock Entertainment","USA","9169 West Sunset Boulevard"));
////        productions.add(new Production("Paramount Pictures","USA","5555 Melrose Avenue, Hollywood, California"));
////        productions.add(new Production("Marvel Studios","USA","500 South Buena Vista Street, Burbank, California"));
////        productions.add(new Production("Warner Bros. Pictures","USA","4000 Warner Boulevard, Burbank, California"));
////        productions.add(new Production("Marvel Studios","USA","500 South Buena Vista Street, Burbank, California"));
////
////        movies = new ArrayList<>();
////        movies.add(new Movie("The Shawshank Redemption", "Drama",  9.3,productions.get(0)));
////        movies.add(new Movie("The Godfather: Part II", "Crime",  9.0,productions.get(1)));
////        movies.add(new Movie("Avengers: End Game", "Sci-fi",  9.9,productions.get(2)));
////        movies.add(new Movie("SuperMan", "Hero",  8.9, productions.get(3)));
////        movies.add(new Movie("Spider-Man", "Hero",  10.0,productions.get(4)));
//    }

}
