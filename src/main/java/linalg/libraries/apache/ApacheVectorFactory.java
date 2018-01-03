package linalg.libraries.apache;

import exceptions.NegativeDimensionException;
import linalg.Vector;
import linalg.VectorFactory;
import org.apache.commons.math3.linear.ArrayRealVector;

/**
 * Vector factory using Apache Commons Math library as backend.
 *
 * @see VectorFactory
 */
public class ApacheVectorFactory implements VectorFactory {
    @Override
    public Vector make(double[] values) {
        return new ApacheVector(new ArrayRealVector(values));
    }

    @Override
    public Vector makeFilled(int dim, double fill) {
        return makeZero(dim).add(fill);
    }

    @Override
    public Vector makeZero(int dim) {
        if(dim <= 0)
            throw new NegativeDimensionException(dim);
        return make(new double[dim]);
    }
}
