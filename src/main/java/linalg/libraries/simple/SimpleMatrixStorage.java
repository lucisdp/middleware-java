package linalg.libraries.simple;

import linalg.MatrixStorage;
import linalg.VectorStorage;


public class SimpleMatrixStorage implements MatrixStorage {
    private double[][] storage;

    SimpleMatrixStorage(double[][] matrix){
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
    public VectorStorage getRow(int index) {
        return new SimpleVectorStorage(storage[index]);
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
