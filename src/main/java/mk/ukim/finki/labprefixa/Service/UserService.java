package mk.ukim.finki.labprefixa.Service;

import mk.ukim.finki.labprefixa.Model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService
{
    List<User> getAllUsers();
    Optional<mk.ukim.finki.labprefixa.Model.User> saveUser(String username, String name, String surname, String password, LocalDate dateOfBirth);
}
