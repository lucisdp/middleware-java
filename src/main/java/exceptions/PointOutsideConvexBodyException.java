package exceptions;

/**
 * Exception to be thrown when initial point for RandomWalk algorithms are outside convex body
 *
 * @author lucianodp
 */
public class PointOutsideConvexBodyException extends RuntimeException {
    public PointOutsideConvexBodyException() {
        super("Initial point must belong to the interior of the convex body!");
    }
}
