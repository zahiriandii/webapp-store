package mk.ukim.finki.labprefixa.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
public class TicketOrder
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private Long numberOfTickets;

    @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss")
    private LocalDateTime dateCreated;



    public TicketOrder(User user, Movie movie, Long numberOfTickets,LocalDateTime dateCreated) {
        //this.id=(long)(Math.random()*1000);
        this.user = user;
        this.movie = movie;
        this.numberOfTickets = numberOfTickets;
        this.dateCreated=dateCreated;
    }


    public TicketOrder() {

    }
}
