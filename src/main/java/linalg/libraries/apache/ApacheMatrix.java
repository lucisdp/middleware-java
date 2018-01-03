package linalg.libraries.apache;

import exceptions.IncompatibleDimensionsException;
import exceptions.IncompatibleLinearAlgebraBackendException;
import linalg.Matrix;
import linalg.Vector;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

public class ApacheMatrix extends Matrix {
    private RealMatrix storage;

    ApacheMatrix(RealMatrix storage) {
        this.storage = storage;
    }

    @Override
    public int getNumRows() {
        return storage.getRowDimension();
    }

    @Override
    public int getNumCols() {
        return storage.getColumnDimension();
    }

    @Override
    public double get(int row, int col) {
        if(row < 0 || row >= getNumRows() || col < 0 || col >= getNumCols())
            throw new ArrayIndexOutOfBoundsException();
        return storage.getEntry(row, col);
    }

    @Override
    public void set(int row, int col, double value) {
        if(row < 0 || row >= getNumRows() || col < 0 || col >= getNumCols())
            throw new ArrayIndexOutOfBoundsException();
        storage.setEntry(row, col, value);
    }

    @Override
    public Vector getRow(int row) {
        if(row < 0 || row >= getNumRows())
            throw new ArrayIndexOutOfBoundsException();
        return new ApacheVector(storage.getRowVector(row));
    }

    @Override
    public Matrix add(double value) {
        return new ApacheMatrix(storage.scalarAdd(value));
    }

    @Override
    public Matrix subtract(double value) {
        return new ApacheMatrix(storage.scalarAdd(-value));
    }

    @Override
    public Matrix multiply(double value) {
        return new ApacheMatrix(storage.scalarMultiply(value));
    }

    @Override
    public Matrix divide(double value) {
        return new ApacheMatrix(storage.scalarMultiply(1.0/value));
    }

    @Override
    public Matrix add(Matrix matrix) {
        checkDim(matrix);
        return new ApacheMatrix(storage.add(getStorage(matrix)));
    }

    @Override
    public Matrix subtract(Matrix matrix) {
        checkDim(matrix);
        return new ApacheMatrix(storage.subtract(getStorage(matrix)));
    }

    @Override
    public Vector multiply(Vector vector) {
        if (storage.getColumnDimension() != vector.getDim())
            throw new IncompatibleDimensionsException(storage.getColumnDimension(), vector.getDim());
        return new ApacheVector(storage.operate(getStorage(vector)));
    }

    @Override
    public Matrix multiply(Matrix matrix) {
        if (storage.getColumnDimension() != matrix.getNumRows())
            throw new IncompatibleDimensionsException(storage.getColumnDimension(), matrix.getNumRows());
        return new ApacheMatrix(storage.multiply(getStorage(matrix)));
    }

    @Override
    public double[][] asArray() {
        return storage.getData();
    }

    private RealMatrix getStorage(Matrix matrix){
        try {
            return ((ApacheMatrix) matrix).storage;
        } catch (ClassCastException ex){
            throw new IncompatibleLinearAlgebraBackendException();
        }
    }

    private RealVector getStorage(Vector vector){
        try {
            return ((ApacheVector) vector).getStorage();
        } catch (ClassCastException ex){
            throw new IncompatibleLinearAlgebraBackendException();
        }
    }
}
