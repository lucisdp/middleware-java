package linalg.libraries.apache;

import exceptions.NegativeDimensionException;
import linalg.Matrix;
import linalg.MatrixFactory;
import org.apache.commons.math3.linear.MatrixUtils;

/**
 * Matrix factory using OjAlgo library as backend.
 *
 * @see MatrixFactory
 */
public class ApacheMatrixFactory implements MatrixFactory {
    @Override
    public Matrix make(double[][] values) {
        return new ApacheMatrix(MatrixUtils.createRealMatrix(values));
    }

    @Override
    public Matrix makeFilled(int rows, int cols, double value) {
        return makeZero(rows, cols).add(value);
    }

    @Override
    public Matrix makeZero(int rows, int cols) {
        if(rows <= 0)
            throw new NegativeDimensionException(rows);
        if(cols <= 0)
            throw new NegativeDimensionException(cols);
        return new ApacheMatrix(MatrixUtils.createRealMatrix(new double[rows][cols]));
    }

    @Override
    public Matrix makeEye(int dim) {
        if(dim <= 0)
            throw new NegativeDimensionException(dim);
        return new ApacheMatrix(MatrixUtils.createRealIdentityMatrix(dim));
    }
}
