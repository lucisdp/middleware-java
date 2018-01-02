package linalg.libraries.ojalgo;

import linalg.MatrixStorage;
import linalg.VectorStorage;
import org.ojalgo.matrix.PrimitiveMatrix;

import java.util.stream.IntStream;


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
    public VectorStorage getRow(int row) {
        return new OjalgoVectorStorage(storage.selectRows(row).transpose());
    }

    @Override
    public VectorStorage getColumn(int col) {
        return new OjalgoVectorStorage(storage.selectColumns(col).transpose());
    }

    @Override
    public MatrixStorage sliceColumns(int start, int end) {
        return new OjalgoMatrixStorage(storage.selectColumns(IntStream.range(start, end).toArray()));
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
