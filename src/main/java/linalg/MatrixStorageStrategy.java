package linalg;

public interface MatrixStorageStrategy {
    int getNumRows();
    int getNumCols();
    double get(int row, int col);
    VectorStorageStrategy getRow(int index);
    Object getRawStorage();
    double[][] asArray();
}
