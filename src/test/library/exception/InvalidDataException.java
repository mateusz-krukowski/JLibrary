package test.library.exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String s) {
        super(s);
    }
}
