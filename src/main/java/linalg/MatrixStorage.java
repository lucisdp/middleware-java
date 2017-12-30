package linalg;

public interface MatrixStorage {
    int getNumRows();
    int getNumCols();
    double get(int row, int col);
    VectorStorage getRow(int index);
    Object getRawStorage();
    double[][] asArray();
}
