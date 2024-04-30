package mk.ukim.finki.labprefixa.Service;

import mk.ukim.finki.labprefixa.Model.Movie;
import mk.ukim.finki.labprefixa.Model.MovieOld;

import java.util.List;
import java.util.Optional;

public interface MovieService
{
    List<Movie> listAll();
    Optional<Movie> findById(Long id);

    Optional<Movie> save(String title, String summary, double rating, Long production);

    Optional<Movie> save(MovieOld movieOld);

    Optional<Movie> edit(Long id, String title, String  summary,double rating, Long production);

    Optional<Movie> edit(Long id, MovieOld movieOld);

    void deleteById(Long id);
}
