package linalg.libraries.apache;

import linalg.MatrixStorageStrategy;
import linalg.VectorStorageStrategy;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealMatrix;

public class ApacheMatrixStorageStrategy implements MatrixStorageStrategy {
    private RealMatrix storage;

    ApacheMatrixStorageStrategy(RealMatrix matrix){
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
    public VectorStorageStrategy getRow(int index) {
        return new ApacheVectorStorageStrategy(new ArrayRealVector(storage.getRow(index)));
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
