package linalg.libraries.simple;

import linalg.MatrixStorageStrategy;
import linalg.VectorStorageStrategy;


public class SimpleMatrixStorageStrategy implements MatrixStorageStrategy {
    private double[][] storage;

    SimpleMatrixStorageStrategy(double[][] matrix){
        this.storage = matrix;
    }

    @Override
    public int getNumCols() {
        return storage[0].length;
    }

    @Override
    public int getNumRows() {
        return storage.length;
    }

    @Override
    public double get(int row, int col) {
        return storage[row][col];
    }

    @Override
    public VectorStorageStrategy getRow(int index) {
        return new SimpleVectorStorageStrategy(storage[index]);
    }

    @Override
    public double[][] getRawStorage() {
        return storage;
    }

    @Override
    public double[][] asArray() {
        return storage;
    }
}
