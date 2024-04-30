package mk.ukim.finki.labprefixa.Repository.InMemory;

import mk.ukim.finki.labprefixa.Bootstrap.DataHolder;
import mk.ukim.finki.labprefixa.Model.Movie;
import mk.ukim.finki.labprefixa.Model.MovieOld;
import mk.ukim.finki.labprefixa.Model.Production;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryMovieRepository
{

    public List<Movie> findAll() {
        return DataHolder.movies;
    }
    public Optional<Movie> findById(Long id) {
        return DataHolder.movies.stream()
                .filter(movie -> movie.getId().equals(id)).findFirst();
    }
    public Optional<Movie> findByName(String name){
        return DataHolder.movies.stream().filter(movie -> movie.getTitle().equals(name)).findFirst();
    }
    public void deleteById(Long id) {
        DataHolder.movies.removeIf(movie -> movie.getId().equals(id));
    }

    public Optional<Movie> save(String title, String summary, double rating, Production production) {
        DataHolder.movies.removeIf(movie -> movie.getTitle().equals(title));
        Movie movie=new Movie(title,summary,rating,production);
        DataHolder.movies.add(movie);
        return Optional.of(movie);
    }

    public Movie save (Movie movie)
    {
       Movie movie1 = new Movie(movie.getTitle(),movie.getSummary(),movie.getRating(),movie.getProduction());
       DataHolder.movies.removeIf(m->m.getId().equals(movie.getId()));
       DataHolder.movies.add(movie1);
       return movie1;
    }
}
