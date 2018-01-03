package linalg;

import exceptions.IncompatibleDimensionsException;

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

    /**
     * FACTORY, as the name implies, is a simple factory class providing a shortcut for Matrix creation. It instantiates
     * Matrix objects based on LinearAlgebraConfig configurations, so both APIs always give the same results. However,
     * we are not able to directly set a new library from here, use LinearAlgebraConfig instead.
     *
     * @see LinearAlgebraConfig
     */
    public static class FACTORY{
        public static Matrix make(double[][] values){
            return LinearAlgebraConfig.getMatrixFactory().make(values);
        }

        public static Matrix makeFilled(int rows, int cols, double fill){
            return LinearAlgebraConfig.getMatrixFactory().makeFilled(rows, cols, fill);
        }

        public static Matrix makeZero(int rows, int cols){
            return LinearAlgebraConfig.getMatrixFactory().makeZero(rows, cols);
        }

        public static Matrix makeEye(int dim){
            return LinearAlgebraConfig.getMatrixFactory().makeEye(dim);
        }
    }

    /**
     * @return copy of inner matrix storage as double[][] array
     */
    public abstract double[][] asArray();

    /**
     * @return number of rows in matrix
     */
    public abstract int getNumRows();

    /**
     * @return number of columns in matrix.
     */
    public abstract int getNumCols();

    /**
     * @param row: row position
     * @param col: col position
     * @return matrix element at row 'row' and column 'col'
     */
    public abstract double get(int row, int col);

    /**
     * Set matrix element at row 'row' and column 'col' to new 'value'
     * @param row: row position
     * @param col: col position
     * @param value: new value
     */
    public abstract void set(int row, int col, double value);

    /**
     * @param row: row to return
     * @return a copy of a given row of the matrix
     * @throws ArrayIndexOutOfBoundsException if row is negative or larger than getNumRows()
     */
    public abstract Vector getRow(int row);

    protected void checkDim(Matrix matrix){
        if (this.getNumRows() != matrix.getNumRows())
            throw new IncompatibleDimensionsException(this.getNumRows(), matrix.getNumRows());

        if (this.getNumCols() != matrix.getNumCols())
            throw new IncompatibleDimensionsException(this.getNumCols(), matrix.getNumCols());
    }

    /**
     * @param val: value to add all elements of matrix with
     * @return sum result
     */
    public Matrix add(double val){
        Matrix result = FACTORY.makeZero(getNumRows(), getNumCols());
        for(int i=0; i < getNumRows(); i++)
            for(int j=0; j < getNumCols(); j++)
                result.set(i, j,get(i,j) + val);
        return result;
    }

    /**
     * @param matrix: matrix to perform element-wise addition.
     * @return sum result
     */
    public Matrix add(Matrix matrix){
        checkDim(matrix);

        Matrix result = FACTORY.makeZero(getNumRows(), getNumCols());
        for(int i=0; i < getNumRows(); i++)
            for(int j=0; j < getNumCols(); j++)
                result.set(i, j,get(i,j) + matrix.get(i,j));
        return result;
    }

    /**
     * @param val: value to subtract all elements of matrix with
     * @return subtraction result
     */
    public Matrix subtract(double val){
        Matrix result = FACTORY.makeZero(getNumRows(), getNumCols());
        for(int i=0; i < getNumRows(); i++)
            for(int j=0; j < getNumCols(); j++)
                result.set(i, j,this.get(i,j) - val);
        return result;
    }

    /**
     * @param matrix: matrix to perform element-wise subtraction.
     * @return subtraction result
     */
    public Matrix subtract(Matrix matrix){
        checkDim(matrix);

        Matrix result = FACTORY.makeZero(getNumRows(), getNumCols());
        for(int i=0; i < getNumRows(); i++)
            for(int j=0; j < getNumCols(); j++)
                result.set(i, j,get(i,j) - matrix.get(i,j));
        return result;
    }

    /**
     * @param val: value to multiply all elements of matrix with
     * @return multiplication result
     */
    public Matrix multiply(double val){
        Matrix result = FACTORY.makeZero(this.getNumRows(), this.getNumCols());
        for(int i=0; i < this.getNumRows(); i++)
            for(int j=0; j < this.getNumCols(); j++)
                result.set(i, j,this.get(i,j) * val);
        return result;
    }

    /**
     * @param vector: vector to multiply
     * @return Matrix-Vector multiplication result
     */
    public Vector multiply(Vector vector){
        if(this.getNumCols() != vector.getDim())
            throw new IncompatibleDimensionsException(this.getNumCols(), vector.getDim());

        Vector result = Vector.FACTORY.makeZero(this.getNumRows());
        for(int i=0; i < this.getNumRows(); i++) {
            double sum = 0.0;
            for (int j = 0; j < this.getNumCols(); j++)
                sum += this.get(i, j) * vector.get(j);
            result.set(i, sum);
        }
        return result;
    }

    /**
     * @param matrix: matrix to multiply to the right
     * @return Matrix-Matrix multiplication result
     */
    public Matrix multiply(Matrix matrix){
        if(this.getNumCols() != matrix.getNumRows())
            throw new IncompatibleDimensionsException(this.getNumCols(), matrix.getNumRows());

        Matrix result = FACTORY.makeZero(this.getNumRows(), matrix.getNumCols());
        for(int i=0; i < this.getNumRows(); i++) {
            for (int j = 0; j < matrix.getNumCols(); j++) {
                double sum = 0.0;
                for (int k = 0; k < matrix.getNumRows(); k++)
                    sum += this.get(i, k) * matrix.get(k, j);
                result.set(i, j, sum);
            }
        }
        return result;
    }

    /**
     * @param matrix: matrix to perform element-wise multiplication.
     * @return multiplication result
     */
    public Matrix multiplyElement(Matrix matrix){
        checkDim(matrix);

        Matrix result = FACTORY.makeZero(getNumRows(), getNumCols());
        for(int i=0; i < getNumRows(); i++)
            for(int j=0; j < getNumCols(); j++)
                result.set(i, j,get(i,j) * matrix.get(i,j));
        return result;
    }

    /**
     * @param val: value to divide all elements of matrix with
     * @return division result
     */
    public Matrix divide(double val){
        Matrix result = FACTORY.makeZero(getNumRows(), getNumCols());
        for(int i=0; i < getNumRows(); i++)
            for(int j=0; j < getNumCols(); j++)
                result.set(i, j,get(i,j) / val);
        return result;
    }

    /**
     * @param matrix: matrix to perform element-wise division.
     * @return division result
     */
    public Matrix divide(Matrix matrix){
        checkDim(matrix);

        Matrix result = FACTORY.makeZero(getNumRows(), getNumCols());
        for(int i=0; i < getNumRows(); i++)
            for(int j=0; j < getNumCols(); j++)
                result.set(i, j,get(i,j) / matrix.get(i,j));
        return result;
    }

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
