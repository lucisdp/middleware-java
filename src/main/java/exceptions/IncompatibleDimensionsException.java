package exceptions;

/**
 * Exception to be thrown when two objects have incompatible dimensions
 *
 * @author lucianodp
 */
public class IncompatibleDimensionsException extends RuntimeException {
    public IncompatibleDimensionsException(int dim1, int dim2){
        super(String.format("Incompatible dimensions found: %d and %d", dim1, dim2));
    }
}
