package version_space;

import exceptions.IncompatibleDimensionsException;
import linalg.Matrix;
import linalg.Vector;

public interface VersionSpace {
    Matrix sample();

    Vector findInteriorPoint();

    void addConstrain(Vector point, double label);

    default void addConstrains(Matrix points, Vector labels){
        if(points.getNumRows() != labels.getDim())
            throw new IncompatibleDimensionsException(points.getNumRows(), labels.getDim());

        for(int i=0; i < labels.getDim(); i++)
            addConstrain(points.getRow(i), labels.get(i));
    }
}
