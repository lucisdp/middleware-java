package exceptions;

public class LinearAlgebraClassNotFound extends RuntimeException {
    public LinearAlgebraClassNotFound(String name){
        super(String.format("Couldn't find Linear Algebra class '%s'.", name));
    }
}
