package mk.ukim.finki.labprefixa.Service.Impl;

import mk.ukim.finki.labprefixa.Model.Movie;
import mk.ukim.finki.labprefixa.Model.MovieOld;
import mk.ukim.finki.labprefixa.Model.Production;
import mk.ukim.finki.labprefixa.Repository.InMemory.InMemoryMovieRepository;
import mk.ukim.finki.labprefixa.Repository.InMemory.InMemoryProductionRepository;
import mk.ukim.finki.labprefixa.Repository.Jpa.MovieRepository;
import mk.ukim.finki.labprefixa.Repository.Jpa.ProductRepository;
import mk.ukim.finki.labprefixa.Service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository inMemoryMovieRepository;
    private final ProductRepository inMemoryProductionRepository;

    public MovieServiceImpl(MovieRepository inMemoryMovieRepository, ProductRepository inMemoryProductionRepository) {
        this.inMemoryMovieRepository = inMemoryMovieRepository;
        this.inMemoryProductionRepository = inMemoryProductionRepository;
    }

    @Override
    public List<Movie> listAll() {
        return inMemoryMovieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return inMemoryMovieRepository.findById(id);
    }

    @Override
    public Optional<Movie> save(String title, String summary, double rating, Long production) {
        Optional<Production> productionOptional = (inMemoryProductionRepository.findById(production));
        if (productionOptional.isPresent()) {
            //Movie movie = new Movie(title, summary, rating, productionOptional.get());
            return Optional.of(inMemoryMovieRepository.save(new Movie(title,summary,rating,productionOptional.get())));
        }
        return Optional.empty();
    }

    private Movie convertDtoToMovie(MovieOld movieOld) {
        Movie movie=new Movie();
        updateMovieFromDto(movie, movieOld);
        return movie;
    }
    private void updateMovieFromDto(Movie movie, MovieOld movieOld) {
        movie.setTitle(movieOld.getTitle());
        movie.setSummary(movieOld.getSummary());
        movie.setRating(movieOld.getRating());
        Production production = inMemoryProductionRepository.findById(movieOld.getProduction().getId())
                .orElseThrow(() -> new RuntimeException("Production not found"));
        movie.setProduction(production);
    }
    @Override
    public Optional<Movie> save(MovieOld movieOld) {
        Movie movie = convertDtoToMovie(movieOld);
        return Optional.of(inMemoryMovieRepository.save(movie));
    }


    @Override
    public Optional<Movie> edit(Long id, String title, String summary, double rating, Long production) {
        Optional<Movie> optionalMovie = inMemoryMovieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            movie.setTitle(title);
            movie.setSummary(summary);
            movie.setRating(rating);

            Optional<Production> productionOptional = inMemoryProductionRepository.findById(production);
            productionOptional.ifPresent(movie::setProduction);

            return Optional.of(inMemoryMovieRepository.save(movie));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Movie> edit(Long id, MovieOld movieOld) {
        Optional<Movie> optionalMovie=inMemoryMovieRepository.findById(id);
        if(optionalMovie.isPresent()){
            Movie movie=optionalMovie.get();
            updateMovieFromDto(movie,movieOld);
            return Optional.of(inMemoryMovieRepository.save(movie));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        inMemoryMovieRepository.deleteById(id);
    }
}
