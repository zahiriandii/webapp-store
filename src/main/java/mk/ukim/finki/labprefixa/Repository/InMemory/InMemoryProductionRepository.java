package mk.ukim.finki.labprefixa.Repository.InMemory;

import mk.ukim.finki.labprefixa.Bootstrap.DataHolder;
import mk.ukim.finki.labprefixa.Model.Production;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductionRepository
{

    public  List<Production> findAll(){
        return DataHolder.productions;
    }
    public Optional<Production> findByID(Long id){
        return DataHolder.productions.stream()
                .filter(production -> production.getId().equals(id))
                .findFirst();
    }
    public Optional<Production> save(String name,String country,String address){
        Production production=new Production(name,country,address);
        DataHolder.productions.add(production);
        return Optional.of(production);
    }

    public Production save(Production production) {
        DataHolder.productions.add(production);
        return production;
    }
    public void deleteById(Long id){
        DataHolder.productions.removeIf(production -> production.getId().equals(id));
    }
}
