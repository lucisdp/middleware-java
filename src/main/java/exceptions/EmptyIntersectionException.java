package exceptions;

/**
 * Exception to be thrown when a line does not intercept convex body.
 *
 * @author lucianodp
 */
public class EmptyIntersectionException extends RuntimeException {
    public EmptyIntersectionException() {
        super("Line does not intercept convexbody!");
    }
}
