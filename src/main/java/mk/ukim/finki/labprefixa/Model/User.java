package mk.ukim.finki.labprefixa.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Data
@Entity
@Table(name = "users-table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String name;
    private String surname;

    private String password;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @OneToMany
    private List<ShoppingCart> carts;

    @OneToMany(mappedBy = "user",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private Set<TicketOrder> ticketOrders = new HashSet<>();

    public void addTicketOrder(TicketOrder ticketOrder) {
        this.ticketOrders.add(ticketOrder);
        ticketOrder.setUser(this);
    }
    public User() {

    }
    public User(String username,String name,String surname, String password, LocalDate dateOfBirth) {
//this.id=(long)(Math.random()*1000);
        this.username = username;
        this.name=name;
        this.surname=surname;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

//    public Set<TicketOrder> getTicketOrders ()
//    {
//        return ticketOrders;
//    }


}
