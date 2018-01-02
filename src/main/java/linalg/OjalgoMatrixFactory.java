package linalg;

import exceptions.NegativeDimensionException;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

public class OjalgoMatrixFactory implements MatrixFactory {
    @Override
    public Matrix makeMatrix(double[][] values) {
        return new OjalgoMatrix(PrimitiveDenseStore.FACTORY.rows(values));
    }

    @Override
    public Matrix makeFilled(int rows, int cols, double value) {
        if(rows <= 0)
            throw new NegativeDimensionException(rows);
        if(cols <= 0)
            throw new NegativeDimensionException(cols);
        return makeZero(rows, cols).add(value);
    }

    @Override
    public Matrix makeZero(int rows, int cols) {
        if(rows <= 0)
            throw new NegativeDimensionException(rows);
        if(cols <= 0)
            throw new NegativeDimensionException(cols);
        return new OjalgoMatrix(PrimitiveDenseStore.FACTORY.makeZero(rows, cols));
    }

    @Override
    public Matrix makeEye(int dim) {
        if(dim <= 0)
            throw new NegativeDimensionException(dim);
        return new OjalgoMatrix(PrimitiveDenseStore.FACTORY.makeEye(dim, dim));
    }
}
