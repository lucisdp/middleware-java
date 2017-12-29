package exceptions;

public class NormalizingZeroVectorException extends RuntimeException {
    public NormalizingZeroVectorException() {
        super("Attempting to normalize a zero vector.");
    }
}
