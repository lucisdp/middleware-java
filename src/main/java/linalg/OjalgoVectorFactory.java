package linalg;

import exceptions.NegativeDimensionException;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

/**
 * Vector factory using OjAlgo library as backend.
 *
 * @see linalg.VectorFactory
 */
class OjalgoVectorFactory implements VectorFactory {
    @Override
    public Vector makeVector(double[] values) {
        return new OjalgoVector(PrimitiveDenseStore.FACTORY.columns(values));
    }

    @Override
    public Vector makeFilled(int dim, double fill) {
        if(dim <= 0)
            throw new NegativeDimensionException(dim);
        return makeZero(dim).add(fill);
    }

    @Override
    public Vector makeZero(int dim) {
        if(dim <= 0)
            throw new NegativeDimensionException(dim);
        return new OjalgoVector(PrimitiveDenseStore.FACTORY.makeZero(1, dim));
    }
}
