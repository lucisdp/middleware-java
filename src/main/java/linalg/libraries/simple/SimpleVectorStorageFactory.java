package linalg.libraries.simple;

import linalg.VectorStorageStrategy;
import linalg.VectorStorageFactory;

public class SimpleVectorStorageFactory implements VectorStorageFactory {
    @Override
    public VectorStorageStrategy makeVectorStorage(double[] values) {
        return new SimpleVectorStorageStrategy(values);
    }
}
