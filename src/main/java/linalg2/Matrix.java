package linalg2;

import exceptions.FoundNegativeDimensionException;
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
            throw new FoundNegativeDimensionException(rows);

        if (cols <= 0)
            throw new FoundNegativeDimensionException(cols);
        
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

    private void checkDim(Matrix Matrix){
        if (this.getRows() != Matrix.getRows())
            throw new IncompatibleDimensionsException(this.getRows(), Matrix.getRows());

        if (this.getCols() != Matrix.getCols())
            throw new IncompatibleDimensionsException(this.getCols(), Matrix.getCols());
    }

    public Matrix add(double val){
        return opStrategy.add(this, val);
    }

    public Matrix add(Matrix Matrix){
        checkDim(Matrix);
        return opStrategy.add(this, Matrix);
    }

    public Matrix subtract(double val){
        return opStrategy.subtract(this, val);
    }

    public Matrix subtract(Matrix Matrix){
        checkDim(Matrix);
        return opStrategy.subtract(this, Matrix);
    }

    public Matrix multiply(double val){
        return opStrategy.multiply(this, val);
    }

    public Matrix multiply(Matrix Matrix){
        checkDim(Matrix);
        return opStrategy.multiply(this, Matrix);
    }

    public Matrix divide(double val){
        return opStrategy.divide(this, val);
    }

    public Matrix divide(Matrix Matrix){
        checkDim(Matrix);
        return opStrategy.divide(this, Matrix);
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
