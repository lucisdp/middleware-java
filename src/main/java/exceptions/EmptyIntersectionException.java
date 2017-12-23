package exceptions;

public class EmptyIntersectionException extends RuntimeException {
    public EmptyIntersectionException() {
        super("Line does not intercept convexbody!");
    }
}
