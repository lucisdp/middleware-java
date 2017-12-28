package linear_programming;

import exceptions.IncompatibleDimensionsException;
import exceptions.LinearProgrammingLibraryNotFound;
import linalg.Matrix;
import linalg.Vector;
import utils.Configuration;

public interface LinearProgramSolver {
    /*
    * Wrapper of most common Linear Programming solver available in java: Ojalgo and Apache Commons Math
    * */
    static LinearProgramSolver getLinearProgramSolver(int dim){
        return getLinearProgramSolver(Configuration.getLinearProgrammingLibrary(), dim);
    }

    static LinearProgramSolver getLinearProgramSolver(String libraryName, int dim){
        if(libraryName.equalsIgnoreCase("apache"))
            return new ApacheLinearProgramSolver(dim);
        else if(libraryName.equalsIgnoreCase("ojalgo"))
            return new OjalgoLinearProgramSolver(dim);
        else
            throw new LinearProgrammingLibraryNotFound();
    }

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
    default void addLinearConstrain(Matrix constrains, Vector values){
        for(int i=0; i < values.getDim(); i++){
            addLinearConstrain(constrains.getRow(i), values.get(i));
        }
    }
}
