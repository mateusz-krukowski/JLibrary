package test.library.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String s){
        super(s);
    }
}
