package mk.ukim.finki.labprefixa.Model.Exceptions;

public class PasswordsDoNotMatchException extends RuntimeException
{
    public PasswordsDoNotMatchException() {
        super("The Password and Repeat password fields do not match.");
    }
}
