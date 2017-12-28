package linalg;

import exceptions.IncompatibleDimensionsException;
import exceptions.NegativeDimensionException;
import utils.Configuration;

import java.util.Arrays;

public class Matrix {
    private double[][] values;
    private int rows;
    private int cols;
    private static MatrixOperationStrategy opStrategy;
    static {
        Configuration.setLinearAlgebraLibrary(Configuration.getLinearAlgebraLibrary());
    }

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

    public static Matrix getIdentity(int dim){
        if(dim <= 0)
            throw new NegativeDimensionException(dim);

        double[][] res = new double[dim][dim];
        for (int i=0; i < dim; i++)
            res[i][i] = 1.0;
        return new Matrix(res);
    }

    public static void setOpStrategy(MatrixOperationStrategy opStrategy) {
        Matrix.opStrategy = opStrategy;
    }

    public Matrix(int rows, int cols){
        this(rows, cols, 0);
    }

    public double[][] getValues() {
        return values;
    }

    public int getNumRows() {
        return rows;
    }

    public int getNumCols() {
        return cols;
    }

    public Vector getRow(int i) { return new Vector(values[i]); }

    private void checkDim(Matrix matrix){
        if (this.getNumRows() != matrix.getNumRows())
            throw new IncompatibleDimensionsException(this.getNumRows(), matrix.getNumRows());

        if (this.getNumCols() != matrix.getNumCols())
            throw new IncompatibleDimensionsException(this.getNumCols(), matrix.getNumCols());
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
        if (this.getNumCols() != vector.getDim())
            throw new IncompatibleDimensionsException(this.getNumRows(), vector.getDim());
        return opStrategy.multiply(this, vector);
    }

    public Matrix multiply(Matrix matrix){
        if (this.getNumCols() != matrix.getNumRows())
            throw new IncompatibleDimensionsException(this.getNumCols(), matrix.getNumRows());
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
