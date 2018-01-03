package linalg.libraries.simple;

import exceptions.NegativeDimensionException;
import linalg.Matrix;
import linalg.MatrixFactory;

/**
 * Matrix factory using OjAlgo library as backend.
 *
 * @see MatrixFactory
 */
public class SimpleMatrixFactory implements MatrixFactory {
    @Override
    public Matrix make(double[][] values) {
        return new SimpleMatrix(values);
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
        return new SimpleMatrix(new double[rows][cols]);
    }

    @Override
    public Matrix makeEye(int dim) {
        Matrix eye = makeZero(dim, dim);
        for(int i=0; i < dim; i++)
            eye.set(i, i,1.0);
        return eye;
    }
}
