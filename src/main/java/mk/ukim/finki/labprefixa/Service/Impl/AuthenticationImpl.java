package mk.ukim.finki.labprefixa.Service.Impl;

import mk.ukim.finki.labprefixa.Model.Exceptions.InvalidArgumentsException;
import mk.ukim.finki.labprefixa.Model.Exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.labprefixa.Model.Exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.labprefixa.Model.User;
import mk.ukim.finki.labprefixa.Repository.InMemory.InMemoryUserRepository;
import mk.ukim.finki.labprefixa.Repository.Jpa.UserRepository;
import mk.ukim.finki.labprefixa.Service.AuthenticationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthenticationImpl implements AuthenticationService {
    private final UserRepository inMemoryUserRepository;

    public AuthenticationImpl(UserRepository inMemoryUserRepository) {
        this.inMemoryUserRepository = inMemoryUserRepository;
    }

    private boolean credentialsInvalid(String username, String password) {
        return username == null || password == null || username.isEmpty() || password.isEmpty();
    }
    @Override
    public User login(String username, String password) {
        if(credentialsInvalid(username,password)){
            throw new IllegalArgumentException();
        }
        return  inMemoryUserRepository.findByUsernameAndPassword(username,password)
                .orElseThrow(()-> new InvalidUserCredentialsException());
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, LocalDate dateOfBirth) {
        if(credentialsInvalid(username,password)){
            throw new InvalidArgumentsException();
        }
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        User user=new User(username,name,surname,password,dateOfBirth);
        return  this.inMemoryUserRepository.save(user);
    }
}
