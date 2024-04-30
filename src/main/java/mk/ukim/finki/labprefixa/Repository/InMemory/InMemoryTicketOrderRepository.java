package mk.ukim.finki.labprefixa.Repository.InMemory;

import mk.ukim.finki.labprefixa.Bootstrap.DataHolder;
import mk.ukim.finki.labprefixa.Model.TicketOrder;
import mk.ukim.finki.labprefixa.Model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryTicketOrderRepository
{
    public List<TicketOrder> findAll(){
        return DataHolder.ticketOrders;
    }
    List<TicketOrder> findByUserId(Long userId){
        return DataHolder.ticketOrders.stream().filter(i->i.getUser().getId().equals(userId)).collect(Collectors.toList());
    }
    List<TicketOrder> findByMovieId(Long movieId){
        return DataHolder.ticketOrders.stream().filter(i->i.getMovie().getId().equals(movieId)).collect(Collectors.toList());

    }
    List<TicketOrder> findByUserIdAndMovieId(Long userId,Long movieId){
        return DataHolder.ticketOrders.stream().filter(i->i.getUser().getId().equals(userId) && i.getMovie().getId().equals(movieId)).collect(Collectors.toList());
    }

    public TicketOrder save(TicketOrder ticketOrder)
    {
        DataHolder.ticketOrders.add(ticketOrder);
        return ticketOrder;
    }
}
