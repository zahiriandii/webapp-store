package mk.ukim.finki.labprefixa.Web.Controllers;

import mk.ukim.finki.labprefixa.Model.Movie;
import mk.ukim.finki.labprefixa.Model.Production;
import mk.ukim.finki.labprefixa.Model.User;
import mk.ukim.finki.labprefixa.Repository.Jpa.MovieRepository;
import mk.ukim.finki.labprefixa.Repository.Jpa.ProductRepository;
import mk.ukim.finki.labprefixa.Repository.Jpa.UserRepository;
import mk.ukim.finki.labprefixa.Service.MovieService;
import mk.ukim.finki.labprefixa.Service.ProductionService;
import mk.ukim.finki.labprefixa.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = {"/movies","/"})
public class ListMoviesController
{
    private final MovieService movieService;
    private final UserService userService;
    private final ProductionService productionService;

    public ListMoviesController(MovieService movieService, UserService userService, ProductionService productionService) {
        this.movieService = movieService;
        this.userService = userService;
        this.productionService = productionService;
    }

    @GetMapping
    public String getMoviePage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Movie> movies = this.movieService.listAll();
        List<User> users=this.userService.getAllUsers();
        model.addAttribute("movies", movies);
        model.addAttribute("users",users);
        model.addAttribute("bodyContent","ListMovies");
        return "master-template";
    }
    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        this.movieService.deleteById(id);
        return "redirect:/movies";
    }

    @GetMapping("/edit-form/{id}")
    public String editMoviePage(@PathVariable Long id, Model model) {
        if (this.movieService.findById(id).isPresent()) {
            Movie movie = this.movieService.findById(id).get();
            List<Production> productions = this.productionService.listAll();
            model.addAttribute("productions", productions);
            model.addAttribute("movie", movie);
            model.addAttribute("bodyContent","AddMovie");
            return "master-template";
        }
        return "redirect:/movies?error=MovieNotFound";
    }

    @GetMapping("/add-form")
    public String addMoviePage(Model model) {
        List<Production> productions = this.productionService.listAll();
        model.addAttribute("productions", productions);
        model.addAttribute("bodyContent","AddMovie");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveMovie(
            @RequestParam(required = false) Long id,
            @RequestParam String title,
            @RequestParam String summary,
            @RequestParam double rating,
            @RequestParam Long productions) {

            if (id!=null)
            {
                this.movieService.edit(id,title,summary,rating,productions);
            }
            else
            {
                this.movieService.save(title, summary, rating, productions);
            }


        return "redirect:/movies";
    }


}
