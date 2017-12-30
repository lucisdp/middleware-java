package linalg.libraries.simple;

import linalg.VectorStorage;

public class SimpleVectorStorage implements VectorStorage {
    double[] storage;

    public SimpleVectorStorage(double[] values){
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
