package mk.ukim.finki.labprefixa.Service;

import mk.ukim.finki.labprefixa.Model.Production;

import java.util.List;
import java.util.Optional;

public interface ProductionService
{
    List<Production> listAll();
    Optional<Production> findById(Long id);
    Production save(String name,String country, String address);
    Optional<Production> edit(Long id,String name,String country,String address);
    void deleteById(Long id);

}
