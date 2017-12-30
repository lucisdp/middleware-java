package linalg.libraries.ojalgo;

import linalg.MatrixStorageFactory;
import linalg.MatrixStorageStrategy;
import org.ojalgo.matrix.PrimitiveMatrix;

public class OjalgoMatrixStorageFactory implements MatrixStorageFactory{
    @Override
    public MatrixStorageStrategy makeMatrixStorage(double[][] values) {
        return new OjalgoMatrixStorageStrategy(PrimitiveMatrix.FACTORY.rows(values));
    }
}
