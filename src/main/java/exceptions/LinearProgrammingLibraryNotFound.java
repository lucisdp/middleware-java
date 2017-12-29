package exceptions;

/**
 * Exception to be thrown when trying to specify an unknown Linear Programming solver library
 *
 * @author lucianodp
 */
public class LinearProgrammingLibraryNotFound extends RuntimeException {
    public LinearProgrammingLibraryNotFound() {
        super("Linear Programming library not found.");
    }
}
