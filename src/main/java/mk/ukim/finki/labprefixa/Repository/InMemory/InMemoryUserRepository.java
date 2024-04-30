package mk.ukim.finki.labprefixa.Repository.InMemory;

import mk.ukim.finki.labprefixa.Bootstrap.DataHolder;
//import org.apache.catalina.User;
import mk.ukim.finki.labprefixa.Model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryUserRepository
{
    public Optional<mk.ukim.finki.labprefixa.Model.User> findByUsername(String username){
        return DataHolder.users.stream().filter(r->r.getUsername().equals(username)).findFirst();
    }
    public Optional<mk.ukim.finki.labprefixa.Model.User> findByUsernameAndPassword(String username, String password) {
        return DataHolder.users.stream().filter(r->r.getUsername().equals(username) && r.getPassword().equals(password)).findFirst();
    }

    public User saveOrUpdate(User user) {
        DataHolder.users.removeIf(r->r.getUsername().equals(user.getUsername()));
        DataHolder.users.add( user);
        return user;
    }

    public List<mk.ukim.finki.labprefixa.Model.User> findAll()
    {
        return DataHolder.users;
    }
    public User save (User user)
    {
        DataHolder.users.add( user);
        return user;
    }
    public void delete(String username) {
        DataHolder.users.removeIf(r->r.getUsername().equals(username));
    }
}
