package linalg;

import exceptions.IncompatibleDimensionsException;
import exceptions.NegativeDimensionException;


/**
 * <p>This module implements an euclidean Matrix. In other words, a matrix is a bi-dimensional collection of real numbers with
 * which we can perform several operations (sum, subtract, multiply, ...), including decompositions and acting upon vectors.</p>
 *
 * <p>There are several Linear Algebra libraries in Java, but there is not a single better choice for all applications. Given
 * the still uncertain nature of our Middleware, we decided to create a wrapper for the most promising Linear Algebra
 * libraries.</p>
 *
 * <p>Our design works as follows: every Matrix object contains a MatrixStorage object, which is responsible for encapsulating
 * the underlying matrix storage used by each library (RealMatrix for Apache Commons Math, Primitive Matrix for OjAlgo, etc).
 * For each library we create a specific MatrixStorage object, but instantiation can only be created through an appropriate
 * Factory stored in LinearAlgebraConfiguration. In addition, Matrix operations are delegated to a MatrixOperation object,
 * which is also stored in LinearAlgebraConfiguration. </p>
 *
 * <p> We note this design allows for great flexibility in the choice of library, and also hides the API's of each of these
 * libraries under a common, simpler interface. We note that in order to use the Matrix class, all is need to is </p>
 *
 * @author lucianodp
 *
 * @see LinearAlgebraConfiguration
 * @see MatrixOperation
 * @see MatrixStorage
 * @see Vector
 */
public class Matrix {
    private MatrixStorage storage;

    private Matrix(MatrixStorage storage){
        this.storage = storage;
    }

    public static class FACTORY{
        /**
         * Create new matrix from double array
         * @param values: array of values to be composing the matrix
         */
        public static Matrix make(double[][] values){
            MatrixStorage store = LinearAlgebraConfiguration.getMatrixStorageFactory().make(values);
            return new Matrix(store);
        }

        /**
         * Construct a matrix of specified size, filling it with a given value.
         * @param rows: number of rows
         * @param cols: number of columns
         * @param fill: value to fill the new matrix
         * @throws NegativeDimensionException if rows or cols are not positive numbers
         */
        public static Matrix makeFilled(int rows, int cols, double fill){
            if (rows <= 0)
                throw new NegativeDimensionException(rows);

            if (cols <= 0)
                throw new NegativeDimensionException(cols);

            MatrixStorage storage = LinearAlgebraConfiguration.getMatrixStorageFactory().makeFilled(rows, cols, fill);
            return new Matrix(storage);
        }

        /**
         * Constructs a zero matrix from given size.
         * @param rows: number of rows
         * @param cols: number of columns
         * @throws NegativeDimensionException if rows or cols are not positive numbers
         */
        public static Matrix makeZero(int rows, int cols){
            if (rows <= 0)
                throw new NegativeDimensionException(rows);

            if (cols <= 0)
                throw new NegativeDimensionException(cols);

            MatrixStorage storage = LinearAlgebraConfiguration.getMatrixStorageFactory().makeFilled(rows, cols, 0.0);
            return new Matrix(storage);
        }

        /**
         * Creates an identity matrix of given dimension.
         * @param dim: dimension of matrix (number of rows = number of cols = dim)
         * @return identity matrix
         */
        public static Matrix makeEye(int dim){
            if(dim <= 0)
                throw new NegativeDimensionException(dim);

            return new Matrix(LinearAlgebraConfiguration.getMatrixStorageFactory().makeEye(dim));
        }

    }

    /**
     * Returns a copy of the inner matrix storage as a double[][] array.
     * @return inner storage of values
     */
    public double[][] asArray() {
        return storage.asArray();
    }

    public int getNumRows() {
        return storage.getNumRows();
    }

    public int getNumCols() {
        return storage.getNumCols();
    }

    /**
     * Returns a line of matrix as Vector instance.
     * @param row: line to return
     * @return Matrix row
     */
    public Vector getRow(int row) {
        if(row < 0 || row >= getNumRows())
            throw new ArrayIndexOutOfBoundsException();
        return new Vector(storage.getRow(row));
    }

    /**
     * Returns a line of matrix as Vector instance.
     * @param col: line to return
     * @return Matrix row
     */
    public Vector getColumn(int col) {
        if(col < 0 || col >= getNumCols())
            throw new ArrayIndexOutOfBoundsException();
        return new Vector(storage.getColumn(col));
    }

    public Matrix sliceColumns(int start, int end) {
        if(start < 0 || end >= getNumCols() || start >= end)
            throw new ArrayIndexOutOfBoundsException();
        return new Matrix(storage.sliceColumns(start, end));
    }

    private void checkDim(Matrix matrix){
        if (this.getNumRows() != matrix.getNumRows())
            throw new IncompatibleDimensionsException(this.getNumRows(), matrix.getNumRows());

        if (this.getNumCols() != matrix.getNumCols())
            throw new IncompatibleDimensionsException(this.getNumCols(), matrix.getNumCols());
    }

    /**
     * Adds a value to each matrix element
     * @param val: value to add
     * @return sum result
     */
    public Matrix add(double val){
        return new Matrix(LinearAlgebraConfiguration.getMatrixOperation().add(this.storage, val));
    }

    /**
     * Sum another matrix to this one.
     * @param matrix: matrix to sum
     * @return sum result
     */
    public Matrix add(Matrix matrix){
        checkDim(matrix);
        return new Matrix(LinearAlgebraConfiguration.getMatrixOperation().add(this.storage, matrix.storage));
    }

    /**
     * Subtracts a value from each matrix element
     * @param val: value to subtract
     * @return subtraction result
     */
    public Matrix subtract(double val){
        return new Matrix(LinearAlgebraConfiguration.getMatrixOperation().subtract(this.storage, val));
    }

    /**
     * Subtract another matrix from this one.
     * @param matrix: matrix to subtract
     * @return subtraction result
     */
    public Matrix subtract(Matrix matrix){
        checkDim(matrix);
        return new Matrix(LinearAlgebraConfiguration.getMatrixOperation().subtract(this.storage, matrix.storage));
    }

    /**
     * Multiplies a value to each matrix element
     * @param val: value to multiply
     * @return multiplication result
     */
    public Matrix multiply(double val){
        return new Matrix(LinearAlgebraConfiguration.getMatrixOperation().multiply(this.storage, val));
    }

    /**
     * Matrix-Vector multiplication
     * @param vector: vector to multiply
     * @return multiplication result
     */
    public Vector multiply(Vector vector){
        if (this.getNumCols() != vector.getDim())
            throw new IncompatibleDimensionsException(this.getNumRows(), vector.getDim());
        return new Vector(LinearAlgebraConfiguration.getMatrixOperation().multiply(this.storage, vector.getStorage()));
    }

    /**
     * Matrix-Matrix multiplication
     * @param matrix: matrix to multiply (RHS)
     * @return multiplication result
     */
    public Matrix multiply(Matrix matrix){
        if (this.getNumCols() != matrix.getNumRows())
            throw new IncompatibleDimensionsException(this.getNumCols(), matrix.getNumRows());
        return new Matrix(LinearAlgebraConfiguration.getMatrixOperation().multiply(this.storage, matrix.storage));
    }

    /**
     * Element-wise multiplication of matrix with another one
     * @param matrix: matrix to multiply element-by-element
     * @return multiplication result
     */
    public Matrix multiplyElement(Matrix matrix){
        checkDim(matrix);
        return new Matrix(LinearAlgebraConfiguration.getMatrixOperation().multiplyElement(this.storage, matrix.storage));
    }

    /**
     * Divide a value to each matrix's element. Division by 0 will result in \(\pm \infty\)
     * @param val: value to divide
     * @return division result
     */
    public Matrix divide(double val){
        return new Matrix(LinearAlgebraConfiguration.getMatrixOperation().divide(this.storage, val));
    }

    /**
     * Element-wise division of matrix with another one
     * @param matrix: matrix to multiply element-by-element
     * @return multiplication result
     */
    public Matrix divide(Matrix matrix){
        checkDim(matrix);
        return new Matrix(LinearAlgebraConfiguration.getMatrixOperation().divide(this.storage, matrix.storage));
    }

    /**
     * Returns the transpose of this matrix
     * @return transpose result
     */
    public Matrix transpose(){
        return new Matrix(LinearAlgebraConfiguration.getMatrixOperation().transpose(this.storage));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < getNumRows(); i++) {
            builder.append('{');
            for (int j = 0; j < getNumCols(); j++) {
                builder.append(storage.get(i, j));
                builder.append(',');
                builder.append(' ');
            }
            builder.deleteCharAt(builder.length()-1);
            builder.deleteCharAt(builder.length()-1);
            builder.append('}');
            builder.append('\n');
        }
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }

}
