package linalg.libraries.simple;

import linalg.MatrixStorageFactory;
import linalg.MatrixStorageStrategy;

public class SimpleMatrixStorageFactory implements MatrixStorageFactory{
    @Override
    public MatrixStorageStrategy makeMatrixStorage(double[][] values) {
        return new SimpleMatrixStorageStrategy(values);
    }
}
