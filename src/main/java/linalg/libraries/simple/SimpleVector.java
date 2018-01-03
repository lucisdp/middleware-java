package linalg.libraries.simple;

import linalg.Vector;

import java.util.Arrays;


/**
 * Vector object using the OjAlgo library. The implementation of these operations are based on their AbstractMatrix class,
 * found <a href="https://github.com/optimatika/ojAlgo/blob/master/src/org/ojalgo/matrix/AbstractMatrix.java">here</a>.
 *
 * @see Vector
 */
public class SimpleVector extends Vector {
    private double[] storage;

    SimpleVector(double[] storage) {
        this.storage = storage;
    }

    @Override
    public int getDim() {
        return storage.length;
    }

    @Override
    public double get(int index) {
        return storage[index];
    }

    @Override
    public void set(int index, double newValue) {
        storage[index] = newValue;
    }

    @Override
    public double[] asArray() {
        return Arrays.copyOf(storage, storage.length);
    }

    double[] getStorage() {
        return storage;
    }
}
