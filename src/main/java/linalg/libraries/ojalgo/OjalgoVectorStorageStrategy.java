package linalg.libraries.ojalgo;

import linalg.VectorStorageStrategy;
import org.ojalgo.matrix.PrimitiveMatrix;


public class OjalgoVectorStorageStrategy implements VectorStorageStrategy {
    private PrimitiveMatrix storage;

    OjalgoVectorStorageStrategy(PrimitiveMatrix ojalgoMatrix){
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
