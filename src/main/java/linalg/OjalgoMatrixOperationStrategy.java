package linalg;

import org.ojalgo.matrix.PrimitiveMatrix;

public class OjalgoMatrixOperationStrategy implements MatrixOperationStrategy {
    private PrimitiveMatrix fromMatrix(Matrix vector){
        return PrimitiveMatrix.FACTORY.rows(vector.getValues());
    }

    private Matrix toMatrix(PrimitiveMatrix ojalgoMatrix){
        int rows = (int) ojalgoMatrix.countRows();
        int cols = (int) ojalgoMatrix.countColumns();

        double[][] res = new double[rows][cols];
        for (int i=0; i < rows; i++)
            for (int j=0; j < cols; j++)
                res[i][j] = ojalgoMatrix.get(i,j);

        return new Matrix(res);
    }

    @Override
    public Matrix add(Matrix matrix, double value){
        PrimitiveMatrix ojalgoMatrix = fromMatrix(matrix);
        return toMatrix(ojalgoMatrix.add(value));
    }

    @Override
    public Matrix add(Matrix leftMatrix, Matrix rightMatrix){
        PrimitiveMatrix leftOjalgoMatrix = fromMatrix(leftMatrix);
        PrimitiveMatrix rightOjalgoMatrix = fromMatrix(rightMatrix);
        return toMatrix(leftOjalgoMatrix.add(rightOjalgoMatrix));
    }

    @Override
    public Matrix subtract(Matrix matrix, double value){
        PrimitiveMatrix ojalgoMatrix = fromMatrix(matrix);
        return toMatrix(ojalgoMatrix.add(-value));
    }

    @Override
    public Matrix subtract(Matrix leftMatrix, Matrix rightMatrix){
        PrimitiveMatrix leftOjalgoMatrix = fromMatrix(leftMatrix);
        PrimitiveMatrix rightOjalgoMatrix = fromMatrix(rightMatrix);
        return toMatrix(leftOjalgoMatrix.subtract(rightOjalgoMatrix));
    }

    @Override
    public Matrix multiply(Matrix matrix, double value){
        PrimitiveMatrix ojalgoMatrix = fromMatrix(matrix);
        return toMatrix(ojalgoMatrix.multiply(value));
    }

    @Override
    public Vector multiply(Matrix matrix, Vector vector){
        PrimitiveMatrix ojalgoMatrix = fromMatrix(matrix);
        PrimitiveMatrix ojalgoVector = OjalgoVectorOperationStrategy.fromVector(vector);
        return OjalgoVectorOperationStrategy.toVector(ojalgoMatrix.multiply(ojalgoVector));
    }

    @Override
    public Matrix multiply(Matrix leftMatrix, Matrix rightMatrix){
        PrimitiveMatrix leftOjalgoMatrix = fromMatrix(leftMatrix);
        PrimitiveMatrix rightOjalgoMatrix = fromMatrix(rightMatrix);
        return toMatrix(leftOjalgoMatrix.multiply(rightOjalgoMatrix));
    }

    @Override
    public Matrix multiplyElement(Matrix leftMatrix, Matrix rightMatrix){
        PrimitiveMatrix leftOjalgoMatrix = fromMatrix(leftMatrix);
        PrimitiveMatrix rightOjalgoMatrix = fromMatrix(rightMatrix);
        return toMatrix(leftOjalgoMatrix.multiplyElements(rightOjalgoMatrix));
    }

    @Override
    public Matrix divide(Matrix matrix, double value){
        PrimitiveMatrix ojalgoMatrix = fromMatrix(matrix);
        return toMatrix(ojalgoMatrix.divide(value));
    }

    @Override
    public Matrix divide(Matrix leftMatrix, Matrix rightMatrix){
        PrimitiveMatrix leftOjalgoMatrix = fromMatrix(leftMatrix);
        PrimitiveMatrix rightOjalgoMatrix = fromMatrix(rightMatrix);
        return toMatrix(leftOjalgoMatrix.divideElements(rightOjalgoMatrix));
    }
}
