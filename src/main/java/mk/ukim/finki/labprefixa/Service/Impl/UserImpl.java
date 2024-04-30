package mk.ukim.finki.labprefixa.Service.Impl;

import mk.ukim.finki.labprefixa.Repository.InMemory.InMemoryUserRepository;
import mk.ukim.finki.labprefixa.Repository.Jpa.UserRepository;
import mk.ukim.finki.labprefixa.Service.UserService;
import mk.ukim.finki.labprefixa.Model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserImpl implements UserService
{

    private final UserRepository inMemoryUserRepository;

    public UserImpl(UserRepository inMemoryUserRepository) {
        this.inMemoryUserRepository = inMemoryUserRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return inMemoryUserRepository.findAll();
    }

    @Override
    public Optional<User> saveUser(String username, String name, String surname, String password , LocalDate dateOfBirth) {

        User user=new User(username,name,surname,password,dateOfBirth);
        return Optional.of(inMemoryUserRepository.save(user));

    }
}
