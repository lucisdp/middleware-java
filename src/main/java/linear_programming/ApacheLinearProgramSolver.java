package linear_programming;

import exceptions.LinearProgramOptimizationFailed;
import exceptions.NegativeDimensionException;
import linalg.Matrix;
import linalg.Vector;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Wrapper for Linear Programming solver in the Apache Commons Math library.
 * @see <a href="https://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/index.html?overview-summary.html">
 *     Apache Commons Math 3 linear optimization javadoc</a>
 * @author lucianodp
 */
public class ApacheLinearProgramSolver implements LinearProgramSolver {
    private final int dim;
    private LinearObjectiveFunction objective;
    private final Collection<LinearConstraint> constraints = new ArrayList<>();

    public ApacheLinearProgramSolver(int dim) {
        if(dim <= 0)
            throw new NegativeDimensionException(dim);
        this.dim = dim;
    }

    @Override
    public int getDim() {
        return dim;
    }

    @Override
    public void setObjectiveFunction(Vector vector) {
        checkDim(vector);
        objective = new LinearObjectiveFunction(vector.asArray(), 0);
    }

    @Override
    public void setLower(Vector vector) {
        checkDim(vector);
        addLinearConstrain(Matrix.FACTORY.makeEye(vector.getDim()).multiply(-1), vector.multiply(-1));
    }

    @Override
    public void setUpper(Vector vector) {
        checkDim(vector);
        addLinearConstrain(Matrix.FACTORY.makeEye(vector.getDim()), vector);
    }

    @Override
    public void addLinearConstrain(Vector vector, double val) {
        checkDim(vector);
        constraints.add(new LinearConstraint(vector.asArray(), Relationship.LEQ, val));
    }

    @Override
    public Vector findMinimizer() {
        PointValuePair solution = new SimplexSolver().optimize(objective, new LinearConstraintSet(constraints), GoalType.MINIMIZE);

        if(solution == null){
            throw new LinearProgramOptimizationFailed();
        }

        return Vector.FACTORY.makeVector(solution.getPoint());
    }
}
