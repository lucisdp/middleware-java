package linalg.libraries.simple;

import exceptions.NegativeDimensionException;
import linalg.Vector;
import linalg.VectorFactory;

import java.util.Arrays;

/**
 * Vector factory using Apache Commons Math library as backend.
 *
 * @see VectorFactory
 */
public class SimpleVectorFactory implements VectorFactory {
    @Override
    public Vector make(double[] values) {
        return new SimpleVector(values);
    }

    @Override
    public Vector makeFilled(int dim, double fill) {
        if(dim <= 0)
            throw new NegativeDimensionException(dim);
        double[] result = new double[dim];
        Arrays.fill(result, fill);
        return make(result);
    }

    @Override
    public Vector makeZero(int dim) {
        if(dim <= 0)
            throw new NegativeDimensionException(dim);
        return make(new double[dim]);
    }
}
