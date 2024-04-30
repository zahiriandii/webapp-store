package mk.ukim.finki.labprefixa.Model;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.labprefixa.Model.Enumerations.ShoppingCartStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss")
    private LocalDateTime dateCreated;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<TicketOrder> ticketOrders = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    public ShoppingCart()
    {

    }
    public ShoppingCart(User user) {
        //this.id=(long)(Math.random()*1000);
        this.user = user;
        this.dateCreated = LocalDateTime.now();
        this.status=ShoppingCartStatus.CREATED;
    }
}
