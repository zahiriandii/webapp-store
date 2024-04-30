package mk.ukim.finki.labprefixa.Service.Impl;

import mk.ukim.finki.labprefixa.Model.Production;
import mk.ukim.finki.labprefixa.Repository.InMemory.InMemoryProductionRepository;
import mk.ukim.finki.labprefixa.Repository.Jpa.ProductRepository;
import mk.ukim.finki.labprefixa.Service.ProductionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductionImpl implements ProductionService
{

    private final ProductRepository inMemoryProductionRepository;

    public ProductionImpl(ProductRepository inMemoryProductionRepository) {
        this.inMemoryProductionRepository = inMemoryProductionRepository;
    }

    @Override
    public List<Production> listAll() {
        return inMemoryProductionRepository.findAll();
    }

    @Override
    public Optional<Production> findById(Long id) {
        return inMemoryProductionRepository.findById(id);
    }


    @Override
    public Production save(String name, String country, String address) {
        Production production=new Production(name,country,address);
        return inMemoryProductionRepository.save(production);
    }

    @Override
    public Optional<Production> edit(Long id, String name, String country, String address) {
        Optional<Production> optionalProduction=inMemoryProductionRepository.findById(id);
        if(optionalProduction.isPresent()){
            Production production=optionalProduction.get();
            production.setName(name);
            production.setCountry(country);
            production.setAddress(address);

            return Optional.of(inMemoryProductionRepository.save(production));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        inMemoryProductionRepository.deleteById(id);
    }
}
