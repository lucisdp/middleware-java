package linalg.libraries.simple;

import linalg.MatrixStorageFactory;
import linalg.MatrixStorage;

public class SimpleMatrixStorageFactory implements MatrixStorageFactory{
    @Override
    public MatrixStorage make(double[][] values) {
        return new SimpleMatrixStorage(values);
    }
}
