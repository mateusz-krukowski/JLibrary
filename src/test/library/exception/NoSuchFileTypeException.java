package test.library.exception;

public class NoSuchFileTypeException extends RuntimeException {
    public NoSuchFileTypeException(String s) {
        super(s);
    }
}
