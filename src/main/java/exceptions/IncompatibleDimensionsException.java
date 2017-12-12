package exceptions;

public class IncompatibleDimensionsException extends RuntimeException {
    public IncompatibleDimensionsException(int dim1, int dim2){
        super(String.format("Vectors have incompatible dimensions: found %d and %d", dim1, dim2));
    }
}
