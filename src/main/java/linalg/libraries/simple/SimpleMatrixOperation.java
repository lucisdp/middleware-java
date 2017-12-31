package linalg.libraries.simple;

import exceptions.IncompatibleLinearAlgebraLibraryException;
import linalg.MatrixStorage;
import linalg.MatrixOperation;
import linalg.VectorStorage;

/**
 * This is a naive implementation of all matrix operations using double[][] arrays.
 *
 * @author lucianodp
 */
public class SimpleMatrixOperation implements MatrixOperation {
    private double[][] fromMatrix(MatrixStorage matrix){
        try {
            return ((SimpleMatrixStorage) matrix).getRawStorage();
        } catch (ClassCastException ex){
            throw new IncompatibleLinearAlgebraLibraryException();
        }
    }

    private MatrixStorage toMatrix(double[][] simpleMatrix){
        return new SimpleMatrixStorage(simpleMatrix);
    }

    @Override
    public MatrixStorage add(MatrixStorage matrix, double value){
        int rows = matrix.getNumRows();
        int cols = matrix.getNumCols();
        
        double[][] simpleMatrix = fromMatrix(matrix);
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
            res[i][j] = simpleMatrix[i][j] + value;

        return toMatrix(res);
    }

    @Override
    public MatrixStorage add(MatrixStorage leftMatrix, MatrixStorage rightMatrix){
        int rows = leftMatrix.getNumRows();
        int cols = leftMatrix.getNumCols();
        
        double[][] simpleLeftMatrix = fromMatrix(leftMatrix);
        double[][] simpleRightMatrix = fromMatrix(rightMatrix);
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                res[i][j] = simpleLeftMatrix[i][j] + simpleRightMatrix[i][j];

        return toMatrix(res);
    }

    @Override
    public MatrixStorage subtract(MatrixStorage matrix, double value){
        int rows = matrix.getNumRows();
        int cols = matrix.getNumCols();

        double[][] simpleMatrix = fromMatrix(matrix);
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                res[i][j] = simpleMatrix[i][j] - value;

        return toMatrix(res);
    }

    @Override
    public MatrixStorage subtract(MatrixStorage leftMatrix, MatrixStorage rightMatrix){
        int rows = leftMatrix.getNumRows();
        int cols = leftMatrix.getNumCols();

        double[][] simpleLeftMatrix = fromMatrix(leftMatrix);
        double[][] simpleRightMatrix = fromMatrix(rightMatrix);
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                res[i][j] = simpleLeftMatrix[i][j] - simpleRightMatrix[i][j];

        return toMatrix(res);
    }

    @Override
    public MatrixStorage multiply(MatrixStorage matrix, double value){
        int rows = matrix.getNumRows();
        int cols = matrix.getNumCols();
        double[][] simpleMatrix = fromMatrix(matrix);
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                res[i][j] = simpleMatrix[i][j] * value;

        return toMatrix(res);
    }

    @Override
    public MatrixStorage multiply(MatrixStorage leftMatrix, MatrixStorage rightMatrix) {
        int leftRows = leftMatrix.getNumRows();
        int rightCols = rightMatrix.getNumCols();

        double[][] simpleLeftMatrix = fromMatrix(leftMatrix);
        double[][] simpleRightMatrix = fromMatrix(rightMatrix);
        double[][] res = new double[leftRows][rightCols];

        for(int i=0; i < leftRows; i++)
            for(int j=0; j < rightCols; j++)
                for(int k = 0; k < leftMatrix.getNumCols(); k++)
                    res[i][j] += simpleLeftMatrix[i][k] * simpleRightMatrix[k][j];

        return toMatrix(res);
    }

    @Override
    public VectorStorage multiply(MatrixStorage matrix, VectorStorage vector){
        int rows = matrix.getNumRows();
        double[][] simpleMatrix = fromMatrix(matrix);
        double[] simpleVector = SimpleVectorOperation.fromVector(vector);
        double[] res = new double[rows];

        for(int i=0; i < rows; i++)
            for(int k = 0; k < matrix.getNumCols(); k++)
                res[i] += simpleMatrix[i][k] * simpleVector[k];

        return SimpleVectorOperation.toVector(res);
    }

    @Override
    public MatrixStorage multiplyElement(MatrixStorage leftMatrix, MatrixStorage rightMatrix){
        int rows = leftMatrix.getNumRows();
        int cols = leftMatrix.getNumCols();

        double[][] simpleLeftMatrix = fromMatrix(leftMatrix);
        double[][] simpleRightMatrix = fromMatrix(rightMatrix);
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                res[i][j] += simpleLeftMatrix[i][j] * simpleRightMatrix[i][j];

        return toMatrix(res);
    }
    
    @Override
    public MatrixStorage divide(MatrixStorage matrix, double value){
        int rows = matrix.getNumRows();
        int cols = matrix.getNumCols();
        double[][] simpleMatrix = fromMatrix(matrix);
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                res[i][j] = simpleMatrix[i][j] / value;

        return toMatrix(res);
    }

    @Override
    public MatrixStorage divide(MatrixStorage leftMatrix, MatrixStorage rightMatrix){
        int rows = leftMatrix.getNumRows();
        int cols = leftMatrix.getNumCols();

        double[][] simpleLeftMatrix = fromMatrix(leftMatrix);
        double[][] simpleRightMatrix = fromMatrix(rightMatrix);
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                res[i][j] = simpleLeftMatrix[i][j] / simpleRightMatrix[i][j];

        return toMatrix(res);
    }
}
