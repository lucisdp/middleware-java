package linear_programming;

import exceptions.IncompatibleDimensionsException;
import linalg.Matrix;
import linalg.Vector;

import java.util.Collection;

public interface LinearProgramSolver {
    /*
    * Wrapper of most common Linear Programming solver available in java: Ojalgo and Apache Commons Math
    * */
    int getDim();
    default void checkDim(Vector vector){
        if(getDim() != vector.getDim())
            throw new IncompatibleDimensionsException(getDim(), vector.getDim());
    }
    void setObjectiveFunction(Vector vector);
    void setLower(Vector vector);
    void setUpper(Vector vector);
    Vector findMinimizer();
    void addLinearConstrain(Vector vector, double val);
    default void addLinearConstrain(Collection<Vector> constrains, Vector values){
        int i=0;
        for(Vector constrain: constrains){
            addLinearConstrain(constrain, values.get(i));
            i++;
        }
    }
    default void addLinearConstrain(Matrix constrains, Vector values){
        for(int i=0; i < values.getDim(); i++){
            addLinearConstrain(constrains.getRow(i), values.get(i));
        }
    }
}
