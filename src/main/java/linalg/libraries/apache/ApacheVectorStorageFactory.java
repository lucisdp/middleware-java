package linalg.libraries.apache;

import linalg.VectorStorage;
import linalg.VectorStorageFactory;
import org.apache.commons.math3.linear.ArrayRealVector;

public class ApacheVectorStorageFactory implements VectorStorageFactory {
    @Override
    public VectorStorage make(double[] values) {
        return new ApacheVectorStorage(new ArrayRealVector(values));
    }
}
