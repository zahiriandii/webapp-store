package mk.ukim.finki.labprefixa.Model.Exceptions;

public class InvalidUserCredentialsException extends RuntimeException
{
    public InvalidUserCredentialsException() {
        super("Invalid user credentials");
    }
}
