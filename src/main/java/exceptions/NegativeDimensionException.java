package exceptions;

public class NegativeDimensionException extends RuntimeException{
    public NegativeDimensionException(int dim){
        super(String.format("Dimension must be a positive integer, found %d", dim));
    }
}
