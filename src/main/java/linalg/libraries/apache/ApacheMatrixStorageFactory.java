package linalg.libraries.apache;

import linalg.MatrixStorageFactory;
import linalg.MatrixStorage;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;

public class ApacheMatrixStorageFactory implements MatrixStorageFactory{
    @Override
    public MatrixStorage make(double[][] values) {
        return new ApacheMatrixStorage(new Array2DRowRealMatrix(values));
    }

    @Override
    public MatrixStorage makeEye(int dim) {
        return new ApacheMatrixStorage(MatrixUtils.createRealIdentityMatrix(dim));
    }
}
