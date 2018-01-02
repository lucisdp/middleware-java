package linalg;

import exceptions.IncompatibleDimensionsException;
import exceptions.IncompatibleLinearAlgebraBackendException;
import org.ojalgo.function.BinaryFunction;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import static org.ojalgo.function.PrimitiveFunction.*;

public class OjalgoMatrix extends Matrix {
    private PrimitiveDenseStore storage;

    OjalgoMatrix(PrimitiveDenseStore storage) {
        this.storage = storage;
    }

    @Override
    public int getNumRows() {
        return (int) storage.countRows();
    }

    @Override
    public int getNumCols() {
        return (int) storage.countColumns();
    }

    @Override
    public double get(int row, int col) {
        return storage.get(row, col);
    }

    @Override
    public Vector getRow(int row) {
        PrimitiveDenseStore result = PrimitiveDenseStore.FACTORY.makeZero(1, getNumCols());
        storage.logical().row(row).get().supplyTo(result);
        return new OjalgoVector (result);
    }

    @Override
    public Matrix add(double value) {
        return new OjalgoMatrix(performUnaryOperation(ADD, value));
    }

    @Override
    public Matrix subtract(double value) {
        return new OjalgoMatrix(performUnaryOperation(SUBTRACT, value));
    }

    @Override
    public Matrix multiply(double value) {
        return new OjalgoMatrix(performUnaryOperation(MULTIPLY, value));
    }

    @Override
    public Matrix divide(double value) {
        return new OjalgoMatrix(performUnaryOperation(DIVIDE, value));
    }
    
    private PrimitiveDenseStore performUnaryOperation(BinaryFunction<Double> function, double value){
        PrimitiveDenseStore result = storage.copy();
        result.modifyAll(function.second(value));
        return result;
    }

    @Override
    public Matrix add(Matrix matrix) {
        return new OjalgoMatrix(performBinaryOperation(ADD, getStorage(matrix)));
    }

    @Override
    public Matrix subtract(Matrix matrix) {
        return new OjalgoMatrix(performBinaryOperation(SUBTRACT, getStorage(matrix)));
    }

    @Override
    public Matrix multiplyElement(Matrix matrix) {
        return new OjalgoMatrix(performBinaryOperation(MULTIPLY, getStorage(matrix)));
    }

    @Override
    public Matrix divide(Matrix matrix) {
        return new OjalgoMatrix(performBinaryOperation(DIVIDE, getStorage(matrix)));
    }

    private PrimitiveDenseStore performBinaryOperation(BinaryFunction<Double> function, PrimitiveDenseStore rightHandSide){
        if (storage.countRows() != rightHandSide.countRows())
            throw new IncompatibleDimensionsException((int) storage.countRows(), (int) rightHandSide.countRows());
        if (storage.countColumns() != rightHandSide.countColumns())
            throw new IncompatibleDimensionsException((int) storage.countColumns(), (int) rightHandSide.countColumns());

        PrimitiveDenseStore result = rightHandSide.copy();
        result.modifyMatching(storage, function);
        return result;
    }

    @Override
    public Vector multiply(Vector vector) {
        if (storage.countColumns() != vector.getDim())
            throw new IncompatibleDimensionsException((int) storage.countColumns(), vector.getDim());
        return new OjalgoVector((PrimitiveDenseStore) storage.multiply(getStorage(vector)));
    }

    @Override
    public Matrix multiply(Matrix matrix) {
        if (storage.countColumns() != matrix.getNumRows())
            throw new IncompatibleDimensionsException((int) storage.countColumns(), matrix.getNumRows());
        return new OjalgoMatrix((PrimitiveDenseStore) storage.multiply(getStorage(matrix)));
    }

    @Override
    public double[][] asArray() {
        return storage.toRawCopy2D();
    }

    private PrimitiveDenseStore getStorage(Matrix matrix){
        try {
            return ((OjalgoMatrix) matrix).storage;
        } catch (ClassCastException ex){
            throw new IncompatibleLinearAlgebraBackendException();
        }
    }

    private PrimitiveDenseStore getStorage(Vector vector){
        try {
            return ((OjalgoVector) vector).getStorage();
        } catch (ClassCastException ex){
            throw new IncompatibleLinearAlgebraBackendException();
        }
    }
}
