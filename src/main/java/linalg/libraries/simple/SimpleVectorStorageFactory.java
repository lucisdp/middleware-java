package linalg.libraries.simple;

import linalg.VectorStorage;
import linalg.VectorStorageFactory;

public class SimpleVectorStorageFactory implements VectorStorageFactory {
    @Override
    public VectorStorage make(double[] values) {
        return new SimpleVectorStorage(values);
    }
}
