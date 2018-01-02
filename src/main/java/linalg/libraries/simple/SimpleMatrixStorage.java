package linalg.libraries.simple;

import linalg.MatrixStorage;
import linalg.VectorStorage;

import java.util.Arrays;


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
    public VectorStorage getColumn(int column) {
        double[] col = new double[getNumRows()];
        for(int i=0; i < getNumRows(); i++)
            col[i] = storage[i][column];
        return new SimpleVectorStorage(col);
    }

    @Override
    public MatrixStorage sliceColumns(int start, int end) {
        double[][] col = new double[getNumRows()][end-start];
        for(int i=0; i < getNumRows(); i++)
            for(int j=start; j < end; j++)
                col[i][j] = storage[i][j];
        return new SimpleMatrixStorage(col);
    }

    @Override
    public double[][] getRawStorage() {
        return storage;
    }

    @Override
    public double[][] asArray() {
        double[][] result = new double[getNumRows()][getNumCols()];
        for(int i=0; i < getNumRows(); i++)
            result[i] = Arrays.copyOf(storage[i], getNumCols());
        return result;
    }
}
