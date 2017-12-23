package exceptions;

public class PointOutsideConvexBodyException extends RuntimeException {
    public PointOutsideConvexBodyException() {
        super("Initial point must belong to the interior of the convex body!");
    }
}
