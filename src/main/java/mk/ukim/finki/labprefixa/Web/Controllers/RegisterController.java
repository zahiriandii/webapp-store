package mk.ukim.finki.labprefixa.Web.Controllers;

import mk.ukim.finki.labprefixa.Model.Exceptions.InvalidArgumentsException;
import mk.ukim.finki.labprefixa.Model.Exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.labprefixa.Service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@RequestMapping("/register")
public class RegisterController
{
    private final AuthenticationService authService;

    public RegisterController(AuthenticationService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent","register-user");


        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam LocalDate dateOfBirth) {
        try{
            this.authService.register(username, password, repeatedPassword, name, surname,dateOfBirth);
            return "redirect:/logIn";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }

}
