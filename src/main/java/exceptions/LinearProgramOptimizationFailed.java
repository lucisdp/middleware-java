package exceptions;

public class LinearProgramOptimizationFailed extends RuntimeException {
    public LinearProgramOptimizationFailed() {
        super("Linear Programming optimization failed. Check constrains for problem feasibility.");
    }
}
