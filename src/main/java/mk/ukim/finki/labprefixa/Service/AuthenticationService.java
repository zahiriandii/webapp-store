package mk.ukim.finki.labprefixa.Service;

import mk.ukim.finki.labprefixa.Model.User;

import java.time.LocalDate;

public interface AuthenticationService
{
    User login(String username, String password);

    User register(String username, String password, String repeatPassword, String name, String surname, LocalDate dateOfBirth);
}
