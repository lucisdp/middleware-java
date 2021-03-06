package linalg.libraries.simple;

import linalg.Matrix;

import java.util.Arrays;

public class SimpleMatrix extends Matrix {
    private double[][] storage;

    SimpleMatrix(double[][] storage) {
        this.storage = storage;
    }

    @Override
    public int getNumRows() {
        return storage.length;
    }

    @Override
    public int getNumCols() {
        return storage[0].length;
    }

    @Override
    public double get(int row, int col) {
        return storage[row][col];
    }

    @Override
    public void set(int row, int col, double value) {
        storage[row][col] = value;
    }

    @Override
    public double[][] asArray() {
        double[][] result = new double[getNumRows()][getNumCols()];
        for(int i=0; i < getNumRows(); i++)
            result[i] = Arrays.copyOf(storage[i], getNumCols());
        return result;
    }

}
