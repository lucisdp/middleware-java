package exceptions;

public class LinearProgrammingLibraryNotFound extends RuntimeException {
    public LinearProgrammingLibraryNotFound() {
        super("Linear Programming library not found.");
    }
}
