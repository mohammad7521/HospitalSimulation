package exceptions;


//when there is no such account with a given id or username
public class NoSuchUser extends RuntimeException {
    public NoSuchUser() {
    }
}
