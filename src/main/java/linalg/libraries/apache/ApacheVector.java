package linalg.libraries.apache;

import exceptions.IncompatibleLinearAlgebraBackendException;
import linalg.Vector;
import org.apache.commons.math3.linear.RealVector;

/**
 * Vector object using the OjAlgo library. The implementation of these operations are based on their AbstractMatrix class,
 * found <a href="https://github.com/optimatika/ojAlgo/blob/master/src/org/ojalgo/matrix/AbstractMatrix.java">here</a>.
 *
 * @see Vector
 */
public class ApacheVector extends Vector {
    private RealVector storage;

    ApacheVector(RealVector storage) {
        this.storage = storage;
    }

    @Override
    public int getDim() {
        return storage.getDimension();
    }

    @Override
    public double get(int index) {
        return storage.getEntry(index);
    }

    @Override
    public void set(int index, double newValue) {
        storage.setEntry(index, newValue);
    }

    @Override
    public Vector add(double value) {
        return new ApacheVector(storage.mapAdd(value));
    }

    @Override
    public Vector subtract(double value) {
        return new ApacheVector(storage.mapSubtract(value));
    }

    @Override
    public Vector multiply(double value) {
        return new ApacheVector(storage.mapMultiply(value));
    }

    @Override
    public Vector divide(double value) {
        return new ApacheVector(storage.mapDivide(value));
    }

    @Override
    public Vector add(Vector vector) {
        checkDim(vector);
        return new ApacheVector(storage.add(getStorage(vector)));
    }

    @Override
    public Vector subtract(Vector vector) {
        checkDim(vector);
        return new ApacheVector(storage.subtract(getStorage(vector)));
    }

    @Override
    public Vector multiply(Vector vector) {
        checkDim(vector);
        return new ApacheVector(storage.ebeMultiply(getStorage(vector)));
    }

    @Override
    public Vector divide(Vector vector) {
        checkDim(vector);
        return new ApacheVector(storage.ebeDivide(getStorage(vector)));
    }

    @Override
    public double dot(Vector vector) {
        checkDim(vector);
        return storage.dotProduct(getStorage(vector));
    }

    @Override
    public double norm() {
        return storage.getNorm();
    }

    @Override
    public double[] asArray() {
        return storage.toArray();
    }

    RealVector getStorage() {
        return storage;
    }

    private RealVector getStorage(Vector vector){
        try {
            return ((ApacheVector) vector).storage;
        } catch (ClassCastException ex){
            throw new IncompatibleLinearAlgebraBackendException();
        }
    }
}
