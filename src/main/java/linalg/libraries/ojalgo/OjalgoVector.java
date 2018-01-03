package linalg.libraries.ojalgo;

import exceptions.IncompatibleDimensionsException;
import exceptions.linalg.IncompatibleLinearAlgebraBackendException;
import linalg.Vector;
import org.ojalgo.function.BinaryFunction;
import org.ojalgo.matrix.store.PrimitiveDenseStore;

import static org.ojalgo.function.PrimitiveFunction.*;

/**
 * Vector object using the OjAlgo library. The implementation of these operations are based on their AbstractMatrix class,
 * found <a href="https://github.com/optimatika/ojAlgo/blob/master/src/org/ojalgo/matrix/AbstractMatrix.java">here</a>.
 *
 * @see Vector
 */
public class OjalgoVector extends Vector {
    private PrimitiveDenseStore storage;

    PrimitiveDenseStore getStorage() {
        return storage;
    }

    OjalgoVector(PrimitiveDenseStore storage) {
        this.storage = storage;
    }

    @Override
    public int getDim() {
        return (int) storage.count();
    }

    @Override
    public double get(int index) {
        if(index < 0 || index >= getDim())
            throw new ArrayIndexOutOfBoundsException();
        return storage.get(index);
    }

    @Override
    public void set(int index, double newValue) {
        if(index < 0 || index >= getDim())
            throw new ArrayIndexOutOfBoundsException();
        storage.set(index, newValue);
    }

    @Override
    public Vector add(double value) {
        return new OjalgoVector(performUnaryOperation(ADD, value));
    }

    @Override
    public Vector subtract(double value) {
        return new OjalgoVector(performUnaryOperation(SUBTRACT, value));
    }

    @Override
    public Vector multiply(double value) {
        return new OjalgoVector(performUnaryOperation(MULTIPLY, value));
    }

    @Override
    public Vector divide(double value) {
        return new OjalgoVector(performUnaryOperation(DIVIDE, value));
    }

    private PrimitiveDenseStore performUnaryOperation(BinaryFunction<Double> function, double value){
        PrimitiveDenseStore result = storage.copy();
        result.modifyAll(function.second(value));
        return result;
    }

    @Override
    public Vector add(Vector vector) {
        return new OjalgoVector(performBinaryOperation(ADD, getStorage(vector)));
    }

    @Override
    public Vector subtract(Vector vector) {
        return new OjalgoVector(performBinaryOperation(SUBTRACT, getStorage(vector)));
    }

    @Override
    public Vector multiply(Vector vector) {
        return new OjalgoVector(performBinaryOperation(MULTIPLY, getStorage(vector)));
    }

    @Override
    public Vector divide(Vector vector) {
        return new OjalgoVector(performBinaryOperation(DIVIDE, getStorage(vector)));
    }

    private PrimitiveDenseStore performBinaryOperation(BinaryFunction<Double> function, PrimitiveDenseStore rightHandSide){
        if (storage.count() != rightHandSide.count())
            throw new IncompatibleDimensionsException((int) storage.count(), (int) rightHandSide.count());

        PrimitiveDenseStore result = rightHandSide.copy();
        result.modifyMatching(storage, function);
        return result;
    }

    @Override
    public double dot(Vector vector) {
        checkDim(vector);
        return storage.dot(getStorage(vector));
    }

    @Override
    public double norm() {
        return storage.norm();
    }

    @Override
    public double[] asArray() {
        return storage.toRawCopy1D();
    }

    private PrimitiveDenseStore getStorage(Vector vector){
        try {
            return ((OjalgoVector) vector).storage;
        } catch (ClassCastException ex){
            throw new IncompatibleLinearAlgebraBackendException();
        }
    }
}
