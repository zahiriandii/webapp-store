package mk.ukim.finki.labprefixa.Repository.Jpa;

import mk.ukim.finki.labprefixa.Model.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketOrderRepository extends JpaRepository<TicketOrder,Long>
{

}
