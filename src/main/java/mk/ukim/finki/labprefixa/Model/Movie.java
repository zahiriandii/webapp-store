package mk.ukim.finki.labprefixa.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Optional;


@Data
@Entity
public class Movie
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String title;
    String summary;

    @ManyToOne
    private Production production;

    double rating;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TicketOrder> ticketOrders;

    public Movie() {

    }

    public Movie(String title, String summary, double rating, Production production) {
       // this.id=(long)(Math.random()*1000);
        this.production = production;
        this.title = title;
        this.summary = summary;
        this.rating = rating;
    }


}
