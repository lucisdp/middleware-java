package exceptions;

public class EmptyVectorException extends RuntimeException{
    public EmptyVectorException() {
        super("Found an empty vector!");
    }
}
