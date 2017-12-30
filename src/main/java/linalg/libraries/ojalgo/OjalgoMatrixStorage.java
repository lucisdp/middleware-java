package linalg.libraries.ojalgo;

import linalg.MatrixStorage;
import linalg.VectorStorage;
import org.ojalgo.matrix.PrimitiveMatrix;


public class OjalgoMatrixStorage implements MatrixStorage {
    private PrimitiveMatrix storage;

    OjalgoMatrixStorage(PrimitiveMatrix matrix){
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
    public VectorStorage getRow(int index) {
        return new OjalgoVectorStorage(storage.selectRows(index).transpose());
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
