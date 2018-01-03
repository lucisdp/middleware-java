package linear_programming;

import exceptions.IncompatibleDimensionsException;
import exceptions.LinearProgrammingLibraryNotFound;
import linalg.Matrix;
import linalg.Vector;

/**
 * Wrapper of most common Linear Programming (LP) solvers available in java. A LP refers to the problem:
 *
 * \[
 * \min_{x \in \mathbb{R}^d} \langle C, x \rangle, \text{ s.t. } Ax \leq b \text{ and } L \leq x \leq U
 * \]
 *
 * The current interface does not support adding equality constrains. This can be added if needed in the future.
 *
 * We support two solvers: Ojalgo and Apache Commons Math.
 * We can specify which library to use through the LinearProgrammingLibrary parameter in the config.properties file.
 */
public interface LinearProgramSolver {

    /**
     * Factory method for retrieving a given LP solver through its library name.
     * @param library: LP solver library
     * @param dim: expected dimension of each vector constrain
     * @return LP solver instance
     */
    static LinearProgramSolver getSolver(LinearProgramSolverLibrary library, int dim){
        if(library == LinearProgramSolverLibrary.APACHE)
            return new ApacheLinearProgramSolver(dim);
        else if(library == LinearProgramSolverLibrary.OJALGO)
            return new OjalgoLinearProgramSolver(dim);
        else
            throw new LinearProgrammingLibraryNotFound();
    }

    /**
     * @return Expected dimension of equality and inequality constrain vectors.
     */
    int getDim();

    default void checkDim(Vector vector){
        if(getDim() != vector.getDim())
            throw new IncompatibleDimensionsException(getDim(), vector.getDim());
    }

    /**
     * Sets the C vector in the objective function \(\langle C, X \rangle \)
     * @param vector: C vector
     * @throws IncompatibleDimensionsException if vectors's dimension is different from getDim().
     */
    void setObjectiveFunction(Vector vector);

    /**
     * Sets the lower bound L for each variable.
     * @param vector: vector whose i-th component specify the constrain \( L_i \leq x_i \)
     * @throws IncompatibleDimensionsException if vector's dimension is different from getDim().
     */
    void setLower(Vector vector);

    /**
     * Sets the upper bound U for each variable.
     * @param vector: vector whose i-th component specify the constrain \( x_i \leq U_i \)
     * @throws IncompatibleDimensionsException if vector's dimension is different from getDim().
     */
    void setUpper(Vector vector);

    /**
     * Computes the solution \( X^* \) of the LP
     * @return solver's solution for the LP
     * @throws exceptions.LinearProgramOptimizationFailed if LP solver failed to find a solution
     */
    Vector findMinimizer();

    /**
     * Adds a new linear inequality constrain to the LP, given by \( \langle vector, x \rangle \leq val \)
     * @param vector: vector in LHS of inequality
     * @param val: RHS of inequality
     * @throws IncompatibleDimensionsException if vector's dimension is different from getDim().
     */
    void addLinearConstrain(Vector vector, double val);

    /**
     * Adds a collection of linear inequalities to the LP, given by \( M x  \leq q \)
     * @param constrains: matrix M in LHS of inequality
     * @param values: vector \(q\) in RHS of inequality
     * @throws IncompatibleDimensionsException if vector's dimension is different from getDim().
     */
    default void addLinearConstrain(Matrix constrains, Vector values){
        for(int i=0; i < values.getDim(); i++){
            addLinearConstrain(constrains.getRow(i), values.get(i));
        }
    }
}
