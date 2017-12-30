package linalg.libraries.apache;

import linalg.VectorStorage;
import org.apache.commons.math3.linear.RealVector;


public class ApacheVectorStorage implements VectorStorage {
    private RealVector storage;

    ApacheVectorStorage(RealVector ojalgoMatrix){
        storage = ojalgoMatrix;
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
    public void set(int index, double value) {
        storage.setEntry(index, value);
    }

    @Override
    public RealVector getRawStorage(){
        return storage;
    }

    @Override
    public double[] asArray() {
        return storage.toArray();
    }
}
