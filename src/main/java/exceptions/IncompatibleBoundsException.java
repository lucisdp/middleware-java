package exceptions;

public class IncompatibleBoundsException extends RuntimeException {
    public IncompatibleBoundsException() {
        super("Lower bound must be strictly smaller than upper bound.");
    }
}
