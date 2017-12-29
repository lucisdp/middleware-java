package exceptions;

/**
 * Exception to be thrown when trying to set a negative length (in convex body parameters).
 *
 * @author lucianodp
 */
public class NegativeLengthException extends RuntimeException {
    public NegativeLengthException(String obj) {
        super(String.format("%s must be a positive number.", obj));
    }
}
