package linalg;

import exceptions.IncompatibleDimensionsException;
import exceptions.LinearAlgebraLibraryNotFound;

/**
 * <p>This module implements an euclidean Matrix. In other words, a matrix is a bi-dimensional collection of real numbers with
 * which we can perform several operations (sum, subtract, multiply, ...), including decompositions and acting upon vectors.</p>
 *
 * <p>There are several Linear Algebra libraries in Java, but there is not a single better choice for all applications. Given
 * the still uncertain nature of our Middleware, we decided to create a wrapper for the most promising Linear Algebra
 * libraries.</p>
 *
 *
 * @author lucianodp
 *
 * @see Vector
 */

public abstract class Matrix {

    public static class FACTORY{
        static private MatrixFactory matrixFactory;

        public static void setFactory(LinearAlgebraLibrary library){
            switch (library){
                case APACHE:
                    matrixFactory = null;
                    break;
                case OJALGO:
                    matrixFactory = new OjalgoMatrixFactory();
                    break;
                case SIMPLE:
                    matrixFactory = null;
                    break;
                default:
                    throw new LinearAlgebraLibraryNotFound(library.name());
            }
        }

        public static Matrix makeMatrix(double[][] values){
            return matrixFactory.makeMatrix(values);
        }

        public static Matrix makeFilled(int rows, int cols, double fill){
            return matrixFactory.makeFilled(rows, cols, fill);
        }

        public static Matrix makeZero(int rows, int cols){
            return matrixFactory.makeZero(rows, cols);
        }

        public static Matrix makeEye(int dim){
            return matrixFactory.makeEye(dim);
        }
    }

    /**
     * Returns inner matrix storage (not a copy, so be careful when manipulating!)
     * @return inner storage of values
     */
    public abstract double[][] asArray();

    /**
     * Get number of rows in matrix.
     * @return number of rows
     */
    public abstract int getNumRows();

    /**
     * Get number of columns in matrix.
     * @return number of columns
     */
    public abstract int getNumCols();

    /**
     * Get element at row 'row' and column 'col'.
     * @return matrix element
     */
    public abstract double get(int row, int col);

    /**
     * Returns a line of matrix as Matrix instance.
     * @param row: line to return
     * @return Matrix row
     */
    public abstract Vector getRow(int row);

    protected void checkDim(Matrix matrix){
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
    public abstract Matrix add(double val);

    /**
     * Sum another matrix to this one.
     * @param matrix: matrix to sum
     * @return sum result
     */
    public abstract Matrix add(Matrix matrix);

    /**
     * Subtracts a value from each matrix element
     * @param val: value to subtract
     * @return subtraction result
     */
    public abstract Matrix subtract(double val);

    /**
     * Subtract another matrix from this one.
     * @param matrix: matrix to subtract
     * @return subtraction result
     */
    public abstract Matrix subtract(Matrix matrix);

    /**
     * Multiplies a value to each matrix element
     * @param val: value to multiply
     * @return multiplication result
     */
    public abstract Matrix multiply(double val);

    /**
     * Matrix-Matrix multiplication
     * @param vector: vector to multiply
     * @return multiplication result
     */
    public abstract Vector multiply(Vector vector);

    /**
     * Matrix-Matrix multiplication
     * @param matrix: matrix to multiply (RHS)
     * @return multiplication result
     */
    public abstract Matrix multiply(Matrix matrix);

    /**
     * Element-wise multiplication of matrix with another one
     * @param matrix: matrix to multiply element-by-element
     * @return multiplication result
     */
    public abstract Matrix multiplyElement(Matrix matrix);

    /**
     * Divide a value to each matrix's element. Division by 0 will result in \(\pm \infty\)
     * @param val: value to divide
     * @return division result
     */
    public abstract Matrix divide(double val);

    /**
     * Element-wise division of matrix with another one
     * @param matrix: matrix to multiply element-by-element
     * @return multiplication result
     */
    public abstract Matrix divide(Matrix matrix);

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < getNumRows(); i++) {
            builder.append('{');
            for (int j = 0; j < getNumCols(); j++) {
                builder.append(this.get(i, j));
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
