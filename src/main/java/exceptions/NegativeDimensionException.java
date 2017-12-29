package exceptions;

/**
 * Exception to be thrown when trying to create an object of negative dimension.
 *
 * @author lucianodp
 */
public class NegativeDimensionException extends RuntimeException{
    public NegativeDimensionException(int dim){
        super(String.format("Dimension must be a positive integer, found %d", dim));
    }
}
