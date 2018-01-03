package linalg;

/**
 * Interface for creating Matrix objects. Factories shouldn't (but can) be directly instantiated; you can access its methods
 * by requesting one factory from LinearAlgebraConfig.getMatrixLibrary() or by using Matrix.FACTORY.{method of choice}.
 *
 * @author lucianodp
 */
public interface MatrixFactory {
    /**
     * Create Matrix from a double[][] array.
     * @param values: double[][] matrix of values
     * @return Matrix containing values
     */
    Matrix make(double[][] values);

    /**
     * Create matrix of given size populated with the same value.
     * @param rows: number of rows
     * @param cols: number of columns
     * @param fill: value used to populate matrix
     * @return Matrix
     * @throws exceptions.NegativeDimensionException if rows or cols is negative
     */
    Matrix makeFilled(int rows, int cols, double fill);

    /**
     * Create zero matrix of given size.
     * @param rows: number of rows
     * @param cols: number of columns
     * @return Matrix
     * @throws exceptions.NegativeDimensionException if rows or cols is negative
     */
    Matrix makeZero(int rows, int cols);

    /**
     * Create identity (square) matrix of given dimension
     * @param dim: number of rows/columns in matrix
     * @return Matrix
     * @throws exceptions.NegativeDimensionException if dim is negative
     */
    Matrix makeEye(int dim);
}
