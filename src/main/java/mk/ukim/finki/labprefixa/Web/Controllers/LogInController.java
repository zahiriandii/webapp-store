package mk.ukim.finki.labprefixa.Web.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.labprefixa.Model.Exceptions.InvalidArgumentsException;
import mk.ukim.finki.labprefixa.Model.Exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.labprefixa.Model.User;
import mk.ukim.finki.labprefixa.Service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logIn")
public class LogInController
{
    private final AuthenticationService authService;



    public LogInController(AuthenticationService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(Model model) {
        model.addAttribute("bodyContent","logIn");
        return "master-template";
    }
    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        User user = null;

        try {
            user = authService.login(request.getParameter("username"), request.getParameter("password"));
        } catch (InvalidUserCredentialsException | InvalidArgumentsException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            model.addAttribute("bodyContent","logIn");
            return "master-template";
        }

        request.getSession().setAttribute("user", user);
        return "redirect:/movies";
    }
}
