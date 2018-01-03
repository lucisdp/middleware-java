package exceptions.linalg;

/**
 * Exception to be thrown when trying to construct an empty vector.
 *
 * @author lucianodp
 */
public class EmptyVectorException extends RuntimeException{
    public EmptyVectorException() {
        super("Found an empty vector!");
    }
}
