package linalg.libraries.apache;

import linalg.MatrixStorage;
import linalg.VectorStorage;
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
    public VectorStorage getRow(int row) {
        return new ApacheVectorStorage(storage.getRowVector(row));
    }

    @Override
    public VectorStorage getColumn(int col) {
        return new ApacheVectorStorage(storage.getColumnVector(col));
    }

    @Override
    public MatrixStorage sliceColumns(int start, int end) {
        return new ApacheMatrixStorage(storage.getSubMatrix(0, getNumRows()-1, start, end-1)); // end is inclusive in Apache
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
