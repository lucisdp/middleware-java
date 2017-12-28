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
        objective = new LinearObjectiveFunction(vector.getValues(), 0);
    }

    @Override
    public void setLower(Vector vector) {
        checkDim(vector);
        addLinearConstrain(Matrix.getIdentity(vector.getDim()).multiply(-1), vector.multiply(-1));
    }

    @Override
    public void setUpper(Vector vector) {
        checkDim(vector);
        addLinearConstrain(Matrix.getIdentity(vector.getDim()), vector);
    }

    @Override
    public void addLinearConstrain(Vector vector, double val) {
        checkDim(vector);
        constraints.add(new LinearConstraint(vector.getValues(), Relationship.LEQ, val));
    }

    @Override
    public Vector findMinimizer() {
        PointValuePair solution = new SimplexSolver().optimize(objective, new LinearConstraintSet(constraints), GoalType.MINIMIZE);

        if(solution == null){
            throw new LinearProgramOptimizationFailed();
        }

        return new Vector(solution.getPoint());
    }
}