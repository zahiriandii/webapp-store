package mk.ukim.finki.labprefixa.Repository.Jpa;

import mk.ukim.finki.labprefixa.Model.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Production,Long>
{
    Optional<Production> findById(Long id);

}
