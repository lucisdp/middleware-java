package linalg.libraries.apache;

import linalg.MatrixStorageFactory;
import linalg.MatrixStorageStrategy;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class ApacheMatrixStorageFactory implements MatrixStorageFactory{
    @Override
    public MatrixStorageStrategy makeMatrixStorage(double[][] values) {
        return new ApacheMatrixStorageStrategy(new Array2DRowRealMatrix(values));
    }
}
