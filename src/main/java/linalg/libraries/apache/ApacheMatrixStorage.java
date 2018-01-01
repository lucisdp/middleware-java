package linalg.libraries.apache;

import linalg.MatrixStorage;
import linalg.VectorStorage;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;

/**
 * Matrix storage in Apache Commons Math library. This class cannot be instantiated directly,
 */
public class ApacheMatrixStorage implements MatrixStorage {
    private RealMatrix storage;

    ApacheMatrixStorage(RealMatrix matrix){
        this.storage = matrix;
    }

    @Override
    public int getNumCols() {
        return storage.getColumnDimension();
    }

    @Override
    public int getNumRows() {
        return storage.getRowDimension();
    }

    @Override
    public double get(int row, int col) {
        return storage.getEntry(row, col);
    }

    @Override
    public VectorStorage getRow(int index) {
        return new ApacheVectorStorage(new ArrayRealVector(storage.getRow(index)));
    }

    @Override
    public RealMatrix getRawStorage() {
        return storage;
    }

    @Override
    public double[][] asArray() {
        return storage.getData();
    }
}
