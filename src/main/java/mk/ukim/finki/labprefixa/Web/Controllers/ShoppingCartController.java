package mk.ukim.finki.labprefixa.Web.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.labprefixa.Model.ShoppingCart;
import mk.ukim.finki.labprefixa.Model.User;
import mk.ukim.finki.labprefixa.Service.ShoppingCartService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController
{
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getShoppingCart(@RequestParam(required = false) String error , HttpServletRequest req, Model model)
    {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/logIn";
        }
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(user.getUsername());
        model.addAttribute("movies", this.shoppingCartService.listAllTicketsInShoppingCart(shoppingCart.getId()));
        model.addAttribute("bodyContent","shopping-cart");
        return "master-template";
    }

    @PostMapping("/add-movie/{id}")
    public String addMovieToCart (@PathVariable Long id,
                                  @RequestParam Long numOfTickets,
                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm") LocalDateTime dateCreated,
                                  HttpServletRequest req)
    {
      try {
          User username =(User) req.getSession().getAttribute("user");
          this.shoppingCartService.addMovieToShoppingCart(username.getUsername(), id,numOfTickets,dateCreated);
          return "redirect:/movies";
      }
      catch (RuntimeException exception)
      {
          return "redirect:/shopping-cart?error=" + exception.getMessage();
      }



    }
}
