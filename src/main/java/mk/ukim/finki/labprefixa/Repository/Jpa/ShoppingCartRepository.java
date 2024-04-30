package mk.ukim.finki.labprefixa.Repository.Jpa;

import mk.ukim.finki.labprefixa.Model.Enumerations.ShoppingCartStatus;
import mk.ukim.finki.labprefixa.Model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long>
{
   Optional<ShoppingCart> findByUserUsernameAndStatus(String username, ShoppingCartStatus status);
}
