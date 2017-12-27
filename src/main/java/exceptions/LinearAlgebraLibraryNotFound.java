package exceptions;

public class LinearAlgebraLibraryNotFound extends RuntimeException {
    public LinearAlgebraLibraryNotFound(String name){
        super(String.format("Couldn't find Linear Algebra class '%s'.", name));
    }
}
