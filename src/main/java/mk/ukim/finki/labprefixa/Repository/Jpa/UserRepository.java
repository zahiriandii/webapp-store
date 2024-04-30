package mk.ukim.finki.labprefixa.Repository.Jpa;

import mk.ukim.finki.labprefixa.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long>
{
    Optional<User> findByUsernameAndPassword(String username,String password);
    Optional<User> findByUsername(String username);
}
