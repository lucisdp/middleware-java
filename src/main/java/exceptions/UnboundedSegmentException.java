package exceptions;

/**
 * Exception to be thrown when trying to sample a point uniformly from an unbounded line segment.
 *
 * @author lucianodp
 */
public class UnboundedSegmentException extends RuntimeException {
    public UnboundedSegmentException() {
        super("Cannot sample from unbounded segment.");
    }
}
