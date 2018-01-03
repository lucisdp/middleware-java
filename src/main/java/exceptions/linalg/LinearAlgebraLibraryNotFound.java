package exceptions.linalg;

/**
 * Exception to be thrown when trying to specify an unknown Linear Algebra library backend
 *
 * @author lucianodp
 */
public class LinearAlgebraLibraryNotFound extends RuntimeException {
    public LinearAlgebraLibraryNotFound(String name){
        super(String.format("Couldn't find Linear Algebra class '%s'.", name));
    }
}
