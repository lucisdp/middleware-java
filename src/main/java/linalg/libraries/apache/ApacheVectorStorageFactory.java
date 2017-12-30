package linalg.libraries.apache;

import linalg.VectorStorageStrategy;
import linalg.VectorStorageFactory;
import org.apache.commons.math3.linear.ArrayRealVector;

public class ApacheVectorStorageFactory implements VectorStorageFactory {
    @Override
    public VectorStorageStrategy makeVectorStorage(double[] values) {
        return new ApacheVectorStorageStrategy(new ArrayRealVector(values));
    }
}
