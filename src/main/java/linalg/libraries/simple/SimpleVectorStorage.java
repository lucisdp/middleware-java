package linalg.libraries.simple;

import linalg.VectorStorage;

import java.util.Arrays;

public class SimpleVectorStorage implements VectorStorage {
    private double[] storage;

    SimpleVectorStorage(double[] values){
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
        return Arrays.copyOf(storage, getDim());
    }
}
