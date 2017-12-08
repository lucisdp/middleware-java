package linalg;

import org.ojalgo.matrix.PrimitiveMatrix;
import org.ojalgo.type.context.NumberContext;

public class Matrix {
    private PrimitiveMatrix ojalgoMatrix;

    public Matrix(double[][] values){
        ojalgoMatrix = PrimitiveMatrix.FACTORY.rows(values);
    }

    private Matrix(PrimitiveMatrix values){
        ojalgoMatrix = values;
    }

    public long getNumRows(){
        return ojalgoMatrix.countRows();
    }

    public long getNumCols(){
        return ojalgoMatrix.countColumns();
    }

    private void checkDim(Matrix mat){
        if (getNumRows() != mat.getNumRows() || getNumCols() != mat.getNumCols())
            throw new IllegalArgumentException("Incompatible dimensions.");
    }

    private void isMultCompatible(Matrix mat){
        if (getNumCols() != mat.getNumRows())
            throw new IllegalArgumentException("Incompatible dimensions.");
    }

    private void isMultCompatible(Vector vec){
        if (getNumCols() != vec.getDim())
            throw new IllegalArgumentException("Incompatible dimensions.");
    }

    public double get(long row, long col){
        if (row < 0 || row >= getNumRows() || col < 0 || col >= getNumCols())
            throw new ArrayIndexOutOfBoundsException("Index out of bounds for matrix.");
        return ojalgoMatrix.get(row, col);
    }

    public Vector getRow(int row) {
        if (row < 0 || row >= getNumRows())
            throw new ArrayIndexOutOfBoundsException("Row index out of bounds for matrix.");
        return new Vector(ojalgoMatrix.getRowsRange(row, row+1));
    }


    public Matrix add(Matrix mat){
        checkDim(mat);
        return new Matrix(ojalgoMatrix.add(mat.ojalgoMatrix));
    }

    public Matrix subtract(Matrix mat){
        checkDim(mat);
        return new Matrix(ojalgoMatrix.subtract(mat.ojalgoMatrix));
    }

    public Matrix multiply(double val){
        return new Matrix(ojalgoMatrix.multiply(val));
    }

    public Matrix multiply(Matrix mat){
        isMultCompatible(mat);
        return new Matrix(ojalgoMatrix.multiply(mat.ojalgoMatrix));
    }

    public Vector multiply(Vector vec){
        isMultCompatible(vec);
        return new Vector(ojalgoMatrix.multiply(vec.ojalgoVector));
    }

    public boolean equals(Matrix mat){
        // return true if Frobenius norm of difference is smaller than 1e-10
        return ojalgoMatrix.equals(mat.ojalgoMatrix, new NumberContext(10,10));
    }

    public double[][] toArray(){
        double[][] res = new double[(int) getNumRows()][(int) getNumCols()];
        for (int i=0; i < getNumRows(); i++)
            for (int j=0; j < getNumCols(); j++)
                res[i][j] = get(i,j);
        return res;
    }

    @Override
    public String toString() {
        return ojalgoMatrix.toString();
    }

}
