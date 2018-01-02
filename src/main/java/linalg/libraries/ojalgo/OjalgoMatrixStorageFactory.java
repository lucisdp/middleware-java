package linalg.libraries.ojalgo;

import linalg.MatrixStorageFactory;
import linalg.MatrixStorage;
import org.ojalgo.matrix.PrimitiveMatrix;

public class OjalgoMatrixStorageFactory implements MatrixStorageFactory{
    @Override
    public MatrixStorage make(double[][] values) {
        return new OjalgoMatrixStorage(PrimitiveMatrix.FACTORY.rows(values));
    }
}
