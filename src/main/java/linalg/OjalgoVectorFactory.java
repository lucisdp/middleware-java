package linalg;

import exceptions.NegativeDimensionException;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

class OjalgoVectorFactory implements VectorFactory {
    @Override
    public Vector makeVector(double[] values) {
        return new OjalgoVector(PrimitiveDenseStore.FACTORY.columns(values));
    }

    @Override
    public Vector makeFilled(int dim, double value) {
        if(dim <= 0)
            throw new NegativeDimensionException(dim);
        return makeZero(dim).add(value);
    }

    @Override
    public Vector makeZero(int dim) {
        if(dim <= 0)
            throw new NegativeDimensionException(dim);
        return new OjalgoVector(PrimitiveDenseStore.FACTORY.makeZero(1, dim));
    }
}
