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
 * <p>Our design works as follows: this Matrix class stores an array containing the matrix values, but it delegates all
 * matrix-related operations to a MatrixOperation object. For each third-part library, we create a new object
 * implementing the MatrixOperation interface, where the matrix operations are finally performed using the library's
 * own functions and syntax. We note this design allows for great flexibility in the choice of library, and also hides the
 * API's of each of these libraries under a common, simpler interface. However, the main drawback lies within performance:
 * before and after every operation, the matrix must be converted to the libraries appropriate Matrix class, which incurs
 * copying the array to a new position in memory. This overhead may not be negligible for large matrices, which may require a
 * rethink on the current design.</p>
 *
 * @author lucianodp
 *
 * @see Vector
 * @see MatrixOperation
 */
public class Matrix {
    private MatrixStorage storageStrategy;
    private static MatrixStorageFactory storageFactory;
    private static MatrixOperation opStrategy;

    /**
     * Create new matrix from double array
     * @param values: array of values to be composing the matrix
     */
    public Matrix(double[][] values){
        this.storageStrategy = storageFactory.makeMatrixStorage(values);
    }

    /**
     * Construct a matrix of specified size, filling it with a given value.
     * @param rows: number of rows
     * @param cols: number of columns
     * @param fill: value to fill the new matrix
     * @throws NegativeDimensionException if rows or cols are not positive numbers
     */
    public Matrix(int rows, int cols, double fill){
        if (rows <= 0)
            throw new NegativeDimensionException(rows);

        if (cols <= 0)
            throw new NegativeDimensionException(cols);
        
        this.storageStrategy = storageFactory.makeMatrixStorage(rows, cols, fill);
    }

    /**
     * Constructs a zero matrix from given size.
     * @param rows: number of rows
     * @param cols: number of columns
     * @throws NegativeDimensionException if rows or cols are not positive numbers
     */
    public Matrix(int rows, int cols){
        this(rows, cols, 0);
    }


    /**
     * Creates an identity matrix of given dimension.
     * @param dim: dimension of matrix (number of rows = number of cols = dim)
     * @return identity matrix
     */
    public static Matrix getIdentity(int dim){
        if(dim <= 0)
            throw new NegativeDimensionException(dim);

        return new Matrix(storageFactory.makeEye(dim));
    }


    private Matrix(MatrixStorage storageStrategy){
        this.storageStrategy = storageStrategy;
    }

    /**
     * Sets matrix operation strategy, responsible for performing matrix operations.
     * @param opStrategy: new matrix operation strategy
     */
    // TODO: create one single public method to set both values, to avoid different Op and Factory
    public static void setOpStrategy(MatrixOperation opStrategy) {
        Matrix.opStrategy = opStrategy;
    }
    public static void setStorageFactory(MatrixStorageFactory storageFactory) { Matrix.storageFactory = storageFactory; }

    /**
     * Returns inner matrix storage (not a copy, so be careful when manipulating!)
     * @return inner storage of values
     */
    public double[][] asArray() {
        return storageStrategy.asArray();
    }

    public int getNumRows() {
        return storageStrategy.getNumRows();
    }

    public int getNumCols() {
        return storageStrategy.getNumCols();
    }

    /**
     * Returns a line of matrix as Vector instance.
     * @param row: line to return
     * @return Matrix row
     */
    public Vector getRow(int row) {
        if(row < 0 || row >= getNumRows())
            throw new ArrayIndexOutOfBoundsException();
        return new Vector(storageStrategy.getRow(row));
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
        return opStrategy.add(this, val);
    }

    /**
     * Sum another matrix to this one.
     * @param matrix: matrix to sum
     * @return sum result
     */
    public Matrix add(Matrix matrix){
        checkDim(matrix);
        return opStrategy.add(this, matrix);
    }

    /**
     * Subtracts a value from each matrix element
     * @param val: value to subtract
     * @return subtraction result
     */
    public Matrix subtract(double val){
        return opStrategy.subtract(this, val);
    }

    /**
     * Subtract another matrix from this one.
     * @param matrix: matrix to subtract
     * @return subtraction result
     */
    public Matrix subtract(Matrix matrix){
        checkDim(matrix);
        return opStrategy.subtract(this, matrix);
    }

    /**
     * Multiplies a value to each matrix element
     * @param val: value to multiply
     * @return multiplication result
     */
    public Matrix multiply(double val){
        return opStrategy.multiply(this, val);
    }

    /**
     * Matrix-Vector multiplication
     * @param vector: vector to multiply
     * @return multiplication result
     */
    public Vector multiply(Vector vector){
        if (this.getNumCols() != vector.getDim())
            throw new IncompatibleDimensionsException(this.getNumRows(), vector.getDim());
        return opStrategy.multiply(this, vector);
    }

    /**
     * Matrix-Matrix multiplication
     * @param matrix: matrix to multiply (RHS)
     * @return multiplication result
     */
    public Matrix multiply(Matrix matrix){
        if (this.getNumCols() != matrix.getNumRows())
            throw new IncompatibleDimensionsException(this.getNumCols(), matrix.getNumRows());
        return opStrategy.multiply(this, matrix);
    }

    /**
     * Element-wise multiplication of matrix with another one
     * @param matrix: matrix to multiply element-by-element
     * @return multiplication result
     */
    public Matrix multiplyElement(Matrix matrix){
        checkDim(matrix);
        return opStrategy.multiplyElement(this, matrix);
    }

    /**
     * Divide a value to each matrix's element. Division by 0 will result in \(\pm \infty\)
     * @param val: value to divide
     * @return division result
     */
    public Matrix divide(double val){
        return opStrategy.divide(this, val);
    }

    /**
     * Element-wise division of matrix with another one
     * @param matrix: matrix to multiply element-by-element
     * @return multiplication result
     */
    public Matrix divide(Matrix matrix){
        checkDim(matrix);
        return opStrategy.divide(this, matrix);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < getNumRows(); i++) {
            builder.append('{');
            for (int j = 0; j < getNumCols(); j++) {
                builder.append(storageStrategy.get(i, j));
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
