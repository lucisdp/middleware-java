package linalg;

import exceptions.IncompatibleDimensionsException;
import exceptions.NegativeDimensionException;
import utils.Configuration;

import java.util.Arrays;


/**
 * <p>This module implements an euclidean Matrix. In other words, a matrix is a bi-dimensional collection of real numbers with
 * which we can perform several operations (sum, subtract, multiply, ...), including decompositions and acting upon vectors.</p>
 *
 * <p>There are several Linear Algebra libraries in Java, but there is not a single better choice for all applications. Given
 * the still uncertain nature of our Middleware, we decided to create a wrapper for the most promising Linear Algebra
 * libraries.</p>
 *
 * <p>Our design works as follows: this Matrix class stores an array containing the matrix values, but it delegates all
 * matrix-related operations to a MatrixOperationStrategy object. For each third-part library, we create a new object
 * implementing the MatrixOperationStrategy interface, where the matrix operations are finally performed using the library's
 * own functions and syntax. We note this design allows for great flexibility in the choice of library, and also hides the
 * API's of each of these libraries under a common, simpler interface. However, the main drawback lies within performance:
 * before and after every operation, the matrix must be converted to the libraries appropriate Matrix class, which incurs
 * copying the array to a new position in memory. This overhead may not be negligible for large matrices, which may require a
 * rethink on the current design.</p>
 *
 * @author lucianodp
 *
 * @see Vector
 * @see MatrixOperationStrategy
 */
public class Matrix {
    private double[][] values;
    private int rows;
    private int cols;
    private static MatrixOperationStrategy opStrategy;
    static {
        // sets Linear Algebra library from config file
        Configuration.setLinearAlgebraLibrary(Configuration.getLinearAlgebraLibrary());
    }

    /**
     * Create new matrix from double array
     * @param values: array of values to be composing the matrix
     */
    public Matrix(double[][] values){
        this.values = values;
        this.rows = values.length;
        this.cols = values[0].length;
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
        
        this.values = new double[rows][cols];
        for (int i=0; i< rows; i++)
            Arrays.fill(this.values[i], fill);
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

        double[][] res = new double[dim][dim];
        for (int i=0; i < dim; i++)
            res[i][i] = 1.0;
        return new Matrix(res);
    }

    /**
     * Sets matrix operation strategy, responsible for performing matrix operations.
     * @param opStrategy: new matrix operation strategy
     */
    public static void setOpStrategy(MatrixOperationStrategy opStrategy) {
        Matrix.opStrategy = opStrategy;
    }

    /**
     * Returns inner matrix storage (not a copy, so be careful when manipulating!)
     * @return inner storage of values
     */
    public double[][] getValues() {
        return values;
    }

    public int getNumRows() {
        return rows;
    }

    public int getNumCols() {
        return cols;
    }

    /**
     * Returns a line of matrix as Vector instance.
     * @param row: line to return
     * @return Matrix row
     */
    public Vector getRow(int row) { return new Vector(values[row]); }

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
                builder.append(values[i][j]);
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
