package exceptions;

public class UnboundedSegmentException extends RuntimeException {
    public UnboundedSegmentException() {
        super("Cannot sample from unbounded segment.");
    }
}
