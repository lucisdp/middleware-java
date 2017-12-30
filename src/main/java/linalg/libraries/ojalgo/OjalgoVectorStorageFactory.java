package linalg.libraries.ojalgo;

import linalg.VectorStorageStrategy;
import linalg.VectorStorageFactory;
import org.ojalgo.matrix.PrimitiveMatrix;

public class OjalgoVectorStorageFactory implements VectorStorageFactory {
    @Override
    public VectorStorageStrategy makeVectorStorage(double[] values) {
        return new OjalgoVectorStorageStrategy(PrimitiveMatrix.FACTORY.columns(values));
    }
}
