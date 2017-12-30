package linalg.libraries.ojalgo;

import linalg.VectorStorage;
import org.ojalgo.matrix.PrimitiveMatrix;


public class OjalgoVectorStorage implements VectorStorage {
    private PrimitiveMatrix storage;

    OjalgoVectorStorage(PrimitiveMatrix ojalgoMatrix){
        storage = ojalgoMatrix;
    }

    @Override
    public int getDim() {
        return (int) storage.countRows();
    }

    @Override
    public double get(int index) {
        return storage.get(index);
    }

    @Override
    public void set(int index, double value) {
        double[] vals = asArray();
        vals[index] = value;
        storage = PrimitiveMatrix.FACTORY.columns(vals);
    }

    @Override
    public PrimitiveMatrix getRawStorage(){
        return storage;
    }

    public double[] asArray() {
        return storage.toRawCopy1D();
    }
}
