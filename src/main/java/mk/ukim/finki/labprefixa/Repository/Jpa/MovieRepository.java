package mk.ukim.finki.labprefixa.Repository.Jpa;

import mk.ukim.finki.labprefixa.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long>
{

}
