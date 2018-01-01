package linalg;

/**
 * This module encapsulates a Matrix data object in a given third-party library. This interface provides methods for
 * accessing and manipulating its data.
 */
public interface MatrixStorage {
    static MatrixStorage getMatrixFactory(String name){
        return null;
    }

    /**
     * Returns the number of rows in matrix.
     * @return number of rows
     */
    int getNumRows();

    /**
     * Returns the number of columns in matrix.
     * @return number of columns
     */
    int getNumCols();

    /**
     * Returns element at position (row, col).
     * @return element at given position.
     * @throws ArrayIndexOutOfBoundsException if row or col are negative or larger the corresponding array's dimension
     */
    double get(int row, int col);

    /**
     * Get one single row of the matrix.
     * @param row: row index
     * @return specified row
     * @throws ArrayIndexOutOfBoundsException if row index is out of bounds
     */
    VectorStorage getRow(int row);

    /**
     * Returns a copy of the matrix in double[][] array format.
     * @return copy of matrix as array
     */
    double[][] asArray();

    /**
     * Gets the matrix underlying storage object (RealMatrix for Apache Commons Math, PrimitiveMatrix for OjAlgo)
     * @return underlying matrix storage
     */
    Object getRawStorage();
}
