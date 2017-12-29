package exceptions;

/**
 * Exception to be thrown when an lower bound is not smaller than an upper bound
 *
 * @author lucianodp
 */
public class IncompatibleBoundsException extends RuntimeException {
    public IncompatibleBoundsException() {
        super("Lower bound must be strictly smaller than upper bound.");
    }
}
