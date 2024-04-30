package mk.ukim.finki.labprefixa.Web.Controllers;

import mk.ukim.finki.labprefixa.Model.Movie;
import mk.ukim.finki.labprefixa.Model.TicketOrder;
import mk.ukim.finki.labprefixa.Service.MovieService;
import mk.ukim.finki.labprefixa.Service.TicketOrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/ticket-order")
public class TicketOrderController
{

    private final TicketOrderService ticketOrderService;
    private final MovieService movieService;

    public TicketOrderController(TicketOrderService ticketOrderService, MovieService movieService) {
        this.ticketOrderService = ticketOrderService;
        this.movieService = movieService;
    }

    @GetMapping("ticket-form")
    public String showTicketOrderForm(Model model) {
        List<Movie> movies = movieService.listAll();
        model.addAttribute("movies", movies);
        model.addAttribute("bodyContent","ticket-order-form");
        return "master-template";
    }
    @GetMapping("/orders-in-time-interval")
    public String getOrdersWithinTimeInterval(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm") LocalDateTime from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd' 'HH:mm") LocalDateTime to,
            Model model) {
        List<TicketOrder> orders = ticketOrderService.getOrdersWithinTimeInterval(from, to);
        model.addAttribute("orders", orders);
        model.addAttribute("bodyContent","order-confirmation");
        return "master-template";
    }
}
