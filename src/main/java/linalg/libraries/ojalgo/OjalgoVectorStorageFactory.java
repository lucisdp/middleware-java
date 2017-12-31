package linalg.libraries.ojalgo;

import linalg.VectorStorage;
import linalg.VectorStorageFactory;
import org.ojalgo.matrix.PrimitiveMatrix;

public class OjalgoVectorStorageFactory implements VectorStorageFactory {
    @Override
    public VectorStorage make(double[] values) {
        return new OjalgoVectorStorage(PrimitiveMatrix.FACTORY.columns(values));
    }
}
