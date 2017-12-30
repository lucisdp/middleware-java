package linalg.libraries.apache;

import linalg.MatrixStorageFactory;
import linalg.MatrixStorage;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class ApacheMatrixStorageFactory implements MatrixStorageFactory{
    @Override
    public MatrixStorage makeMatrixStorage(double[][] values) {
        return new ApacheMatrixStorage(new Array2DRowRealMatrix(values));
    }
}
