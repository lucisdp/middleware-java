package exceptions;

public class NegativeLengthException extends RuntimeException {
    public NegativeLengthException(String obj) {
        super(String.format("%s must be a positive number.", obj));
    }
}
