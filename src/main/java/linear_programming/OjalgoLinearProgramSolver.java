package linear_programming;

import exceptions.NegativeDimensionException;
import linalg.Vector;
import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;
import org.ojalgo.optimisation.Variable;

/**
 * Wrapper for Linear Programming solver in the OjAlgo library.
 *
 * @see <a href="https://github.com/optimatika/ojAlgo/wiki/The-Diet-Problem">OjAlgo wiki on Linear Programming</a>
 * @author lucianodp
 */
public class OjalgoLinearProgramSolver implements LinearProgramSolver {
    private int numConstrains;
    private final int dim;
    private final ExpressionsBasedModel tmpModel;

    public OjalgoLinearProgramSolver(int dim){
        if(dim <= 0)
            throw new NegativeDimensionException(dim);
        this.dim = dim;
        this.numConstrains = 0;
        this.tmpModel = new ExpressionsBasedModel();
        for (int i=0; i < dim; i++){
            Variable var = Variable.make(String.format("X%d", i+1));
            this.tmpModel.addVariable(var);
        }
    }

    @Override
    public int getDim() {
        return dim;
    }

    @Override
    public void setObjectiveFunction(Vector vector) {
        checkDim(vector);
        for(int i=0; i < dim; i++){
            tmpModel.getVariable(i).weight(vector.get(i));
        }
    }

    @Override
    public void setLower(Vector vector) {
        checkDim(vector);
        for(int i=0; i < dim; i++){
            tmpModel.getVariable(i).lower(vector.get(i));
        }
    }

    @Override
    public void setUpper(Vector vector) {
        checkDim(vector);
        for(int i=0; i < dim; i++){
            tmpModel.getVariable(i).upper(vector.get(i));
        }
    }

    @Override
    public void addLinearConstrain(Vector vector, double val) {
        checkDim(vector);
        Expression exp = tmpModel.addExpression(String.format("constrain_%d", numConstrains+1)).upper(val);
        for(int i=0; i < vector.getDim(); i++)
            exp.set(tmpModel.getVariable(i), vector.get(i));

        numConstrains++;
    }

    @Override
    public Vector findMinimizer() {
        return convertToVector(tmpModel.minimise());
    }

    private Vector convertToVector(Optimisation.Result result){
        double[] res = new double[dim];
        for(int i=0; i < dim; i++)
            res[i] = result.get(i).doubleValue();
        return new Vector(res);
    }

}
