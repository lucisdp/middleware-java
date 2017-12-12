package exceptions;

public class FoundNegativeDimensionException extends RuntimeException{
    public FoundNegativeDimensionException(int dim){
        super(String.format("Dimension must be a positive integer, found %d", dim));
    }
}
