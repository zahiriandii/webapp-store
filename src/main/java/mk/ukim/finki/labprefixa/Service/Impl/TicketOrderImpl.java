package mk.ukim.finki.labprefixa.Service.Impl;

import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import mk.ukim.finki.labprefixa.Model.Movie;
import mk.ukim.finki.labprefixa.Model.TicketOrder;
import mk.ukim.finki.labprefixa.Repository.InMemory.InMemoryTicketOrderRepository;
import mk.ukim.finki.labprefixa.Repository.Jpa.TicketOrderRepository;
import mk.ukim.finki.labprefixa.Service.TicketOrderService;
import mk.ukim.finki.labprefixa.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Service
public class TicketOrderImpl implements TicketOrderService
{

    private final TicketOrderRepository inMemoryTicketOrderRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TicketOrderImpl(TicketOrderRepository inMemoryTicketOrderRepository) {
        this.inMemoryTicketOrderRepository = inMemoryTicketOrderRepository;
    }

    @Override
    @Transactional
    public TicketOrder placeOrder(User user, Movie movie,Long numberOfTickets, LocalDateTime dateCreated) {
        if(!entityManager.contains(user)){
            user=entityManager.merge(user);
        }
        Set<TicketOrder> ticketOrders = user.getTicketOrders();
        TicketOrder ticketOrder=new TicketOrder(user,movie,(long)numberOfTickets,dateCreated);
        user.addTicketOrder(ticketOrder);
        return inMemoryTicketOrderRepository.save(ticketOrder);
    }

    @Override
    public List<TicketOrder> getOrdersWithinTimeInterval(LocalDateTime from, LocalDateTime to) {
        return null;
    }

    @Override
    public List<TicketOrder> listAllOrders() {
        return null;
    }
}
