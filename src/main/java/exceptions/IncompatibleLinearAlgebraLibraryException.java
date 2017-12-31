package exceptions;

public class IncompatibleLinearAlgebraLibraryException extends RuntimeException {
    public IncompatibleLinearAlgebraLibraryException() {
        super("Linear Algebra library conflict. Check if you are not mixing objects from different backends.");
    }
}
