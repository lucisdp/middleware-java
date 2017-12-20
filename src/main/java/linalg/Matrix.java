package linalg;

import exceptions.NegativeDimensionException;
import exceptions.IncompatibleDimensionsException;

import java.util.Arrays;

public class Matrix {
    private double[][] values;
    private int rows;
    private int cols;
    private static MatrixOperationStrategy opStrategy = null;

    public Matrix(double[][] values){
        this.values = values;
        this.rows = values.length;
        this.cols = values[0].length;
    }

    public Matrix(int rows, int cols, double fill){
        if (rows <= 0)
            throw new NegativeDimensionException(rows);

        if (cols <= 0)
            throw new NegativeDimensionException(cols);
        
        this.values = new double[rows][cols];
        for (int i=0; i< rows; i++)
            Arrays.fill(this.values[i], fill);
    }

    public Matrix(int rows, int cols){
        this(rows, cols, 0);
    }

    public double[][] getValues() {
        return values;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public static void setMatrixOperationStrategy(String strategyName){
        Matrix.opStrategy = MatrixOperationStrategy.getStrategy(strategyName);
    }

    private void checkDim(Matrix matrix){
        if (this.getRows() != matrix.getRows())
            throw new IncompatibleDimensionsException(this.getRows(), matrix.getRows());

        if (this.getCols() != matrix.getCols())
            throw new IncompatibleDimensionsException(this.getCols(), matrix.getCols());
    }

    private void checkVectorDim(Vector vector){
        if (this.getCols() != vector.getDim())
            throw new IncompatibleDimensionsException(this.getRows(), vector.getDim());
    }

    private void checkMatrixMultDim(Matrix matrix){
        if (this.getCols() != matrix.getRows())
            throw new IncompatibleDimensionsException(this.getCols(), matrix.getRows());
    }

    public Matrix add(double val){
        return opStrategy.add(this, val);
    }

    public Matrix add(Matrix matrix){
        checkDim(matrix);
        return opStrategy.add(this, matrix);
    }

    public Matrix subtract(double val){
        return opStrategy.subtract(this, val);
    }

    public Matrix subtract(Matrix matrix){
        checkDim(matrix);
        return opStrategy.subtract(this, matrix);
    }

    public Matrix multiply(double val){
        return opStrategy.multiply(this, val);
    }

    public Vector multiply(Vector vector){
        checkVectorDim(vector);
        return opStrategy.multiply(this, vector);
    }

    public Matrix multiply(Matrix matrix){
        checkMatrixMultDim(matrix);
        return opStrategy.multiply(this, matrix);
    }

    public Matrix multiplyElement(Matrix matrix){
        checkDim(matrix);
        return opStrategy.multiplyElement(this, matrix);
    }

    public Matrix divide(double val){
        return opStrategy.divide(this, val);
    }

    public Matrix divide(Matrix matrix){
        checkDim(matrix);
        return opStrategy.divide(this, matrix);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i=0; i < getRows(); i++) {
            builder.append('{');
            for (int j=0; j < getCols(); j++) {
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
