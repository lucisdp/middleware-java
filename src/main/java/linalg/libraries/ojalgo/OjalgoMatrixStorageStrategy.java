package linalg.libraries.ojalgo;

import linalg.MatrixStorageStrategy;
import linalg.VectorStorageStrategy;
import org.ojalgo.matrix.PrimitiveMatrix;


public class OjalgoMatrixStorageStrategy implements MatrixStorageStrategy {
    private PrimitiveMatrix storage;

    OjalgoMatrixStorageStrategy(PrimitiveMatrix matrix){
        this.storage = matrix;
    }

    @Override
    public int getNumCols() {
        return (int) storage.countColumns();
    }

    @Override
    public int getNumRows() {
        return (int) storage.countRows();
    }

    @Override
    public double get(int row, int col) {
        return storage.get(row, col);
    }

    @Override
    public VectorStorageStrategy getRow(int index) {
        return new OjalgoVectorStorageStrategy(storage.selectRows(index));
    }

    @Override
    public PrimitiveMatrix getRawStorage() {
        return storage;
    }

    @Override
    public double[][] asArray() {
        return storage.toRawCopy2D();
    }
}
