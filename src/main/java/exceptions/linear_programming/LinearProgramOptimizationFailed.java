package exceptions.linear_programming;

/**
 * Exception to be thrown when LP solver fails to find a solution.
 *
 * @author lucianodp
 */
public class LinearProgramOptimizationFailed extends RuntimeException {
    public LinearProgramOptimizationFailed() {
        super("Linear Programming optimization failed. Check constrains for problem feasibility.");
    }
}
