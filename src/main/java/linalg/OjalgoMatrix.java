package linalg;

import org.ojalgo.matrix.PrimitiveMatrix;
import org.ojalgo.type.context.NumberContext;

public class OjalgoMatrix implements IMatrix {
    private PrimitiveMatrix ojalgoMatrix;

    public OjalgoMatrix(double[][] values){
        ojalgoMatrix = PrimitiveMatrix.FACTORY.rows(values);
    }

    private OjalgoMatrix(PrimitiveMatrix values){
        ojalgoMatrix = values;
    }

    public int getNumRows(){
        return (int) ojalgoMatrix.countRows();
    }

    public int getNumCols(){
        return (int) ojalgoMatrix.countColumns();
    }

    private OjalgoVector tryToConvert(IVector ivec){
        if (!(ivec instanceof OjalgoVector))
            throw new IllegalArgumentException("Expected OjalgoVector class.");
        return (OjalgoVector) ivec;
    }

    private OjalgoMatrix tryToConvert(IMatrix imat){
        if (!(imat instanceof OjalgoMatrix))
            throw new IllegalArgumentException("Expected OjalgoVector class.");
        return (OjalgoMatrix) imat;
    }

    public double get(int row, int col){
        if (row < 0 || row >= getNumRows() || col < 0 || col >= getNumCols())
            throw new ArrayIndexOutOfBoundsException("Index out of bounds for matrix.");
        return ojalgoMatrix.get(row, col);
    }

    public OjalgoVector getRow(int row) {
        if (row < 0 || row >= getNumRows())
            throw new ArrayIndexOutOfBoundsException("Row index out of bounds for matrix.");
        return new OjalgoVector(ojalgoMatrix.getRowsRange(row, row+1));
    }

    public OjalgoMatrix add(double val){ return new OjalgoMatrix(ojalgoMatrix.add(val)); }
    public OjalgoMatrix add(IMatrix mat){
        checkDim(mat);
        return new OjalgoMatrix(ojalgoMatrix.add(tryToConvert(mat).ojalgoMatrix));
    }

    public OjalgoMatrix subtract(double val){ return new OjalgoMatrix(ojalgoMatrix.add(-val)); }
    public OjalgoMatrix subtract(IMatrix mat){
        checkDim(mat);
        return new OjalgoMatrix(ojalgoMatrix.subtract(tryToConvert(mat).ojalgoMatrix));
    }

    public OjalgoMatrix multiply(double val){
        return new OjalgoMatrix(ojalgoMatrix.multiply(val));
    }

    public OjalgoMatrix multiply(IMatrix mat){
        isMultCompatible(mat);
        return new OjalgoMatrix(ojalgoMatrix.multiply(tryToConvert(mat).ojalgoMatrix));
    }

    public OjalgoVector multiply(IVector ivec){
        isMultCompatible(ivec);
        return new OjalgoVector(ojalgoMatrix.multiply(tryToConvert(ivec).ojalgoVector));
    }

    //public OjalgoMatrix divide(IMatrix mat){ return new OjalgoMatrix(ojalgoMatrix.divide(tryToConvert(mat).ojalgoMatrix)); }
    public OjalgoMatrix divide(double val){ return new OjalgoMatrix(ojalgoMatrix.divide(val)); }


    public boolean equals(IMatrix mat){ return ojalgoMatrix.equals(tryToConvert(mat).ojalgoMatrix, new NumberContext(10,10)); }

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
