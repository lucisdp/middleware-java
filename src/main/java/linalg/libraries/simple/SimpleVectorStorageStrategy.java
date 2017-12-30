package linalg.libraries.simple;

import linalg.VectorStorageStrategy;

public class SimpleVectorStorageStrategy implements VectorStorageStrategy {
    double[] storage;

    public SimpleVectorStorageStrategy(double[] values){
        storage = values;
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
    public void set(int index, double value) {
        storage[index] = value;
    }

    public double[] getRawStorage(){
        return storage;
    }

    @Override
    public double[] asArray() {
        return storage;
    }
}
