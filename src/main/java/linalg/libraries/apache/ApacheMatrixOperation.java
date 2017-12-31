package linalg.libraries.apache;


import linalg.*;
import linalg.VectorStorage;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

/**
 * This class implements all matrix operations by means of the Apache Commons Math third-party library.
 *
 * @see <a href="http://commons.apache.org/proper/commons-math/">Apache Commons Math website</a>
 * @see <a href="http://commons.apache.org/proper/commons-math/userguide/linear.html">Apache Commons Math linear algebra user guide</a>
 * @author lucianodp
 */
public class ApacheMatrixOperation implements MatrixOperation {
    private RealMatrix fromMatrix(MatrixStorage matrix){
        return ((ApacheMatrixStorage) matrix).getRawStorage();
    }

    private MatrixStorage toMatrix(RealMatrix apacheMatrix){
        return new ApacheMatrixStorage(apacheMatrix);
    }

    @Override
    public MatrixStorage add(MatrixStorage matrix, double value){
        RealMatrix apacheMatrix = fromMatrix(matrix);
        return toMatrix(apacheMatrix.scalarAdd(value));
    }

    @Override
    public MatrixStorage add(MatrixStorage leftMatrix, MatrixStorage rightMatrix){
        RealMatrix leftApacheMatrix = fromMatrix(leftMatrix);
        RealMatrix rightApacheMatrix = fromMatrix(rightMatrix);
        return toMatrix(leftApacheMatrix.add(rightApacheMatrix));
    }

    @Override
    public MatrixStorage subtract(MatrixStorage matrix, double value){
        RealMatrix apacheMatrix = fromMatrix(matrix);
        return toMatrix(apacheMatrix.scalarAdd(-value));
    }

    @Override
    public MatrixStorage subtract(MatrixStorage leftMatrix, MatrixStorage rightMatrix){
        RealMatrix leftApacheMatrix = fromMatrix(leftMatrix);
        RealMatrix rightApacheMatrix = fromMatrix(rightMatrix);
        return toMatrix(leftApacheMatrix.subtract(rightApacheMatrix));
    }

    @Override
    public MatrixStorage multiply(MatrixStorage matrix, double value){
        RealMatrix apacheMatrix = fromMatrix(matrix);
        return toMatrix(apacheMatrix.scalarMultiply(value));
    }

    @Override
    public VectorStorage multiply(MatrixStorage matrix, VectorStorage vector){
        RealMatrix apacheMatrix = fromMatrix(matrix);
        RealVector apacheVector = ApacheVectorOperation.fromVector(vector);
        return ApacheVectorOperation.toVector(apacheMatrix.operate(apacheVector));
    }

    @Override
    public MatrixStorage multiply(MatrixStorage leftMatrix, MatrixStorage rightMatrix){
        RealMatrix leftApacheMatrix = fromMatrix(leftMatrix);
        RealMatrix rightApacheMatrix = fromMatrix(rightMatrix);
        return toMatrix(leftApacheMatrix.multiply(rightApacheMatrix));
    }

    @Override
    public MatrixStorage multiplyElement(MatrixStorage leftMatrix, MatrixStorage rightMatrix){
        int rows = leftMatrix.getNumRows();
        int cols = leftMatrix.getNumCols();

        double[][] simpleLeftMatrix = leftMatrix.asArray();
        double[][] simpleRightMatrix = rightMatrix.asArray();
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                res[i][j] = simpleLeftMatrix[i][j] * simpleRightMatrix[i][j];

        return (new ApacheMatrixStorageFactory()).makeMatrixStorage(res);
    }

    @Override
    public MatrixStorage divide(MatrixStorage matrix, double value){
        RealMatrix apacheMatrix = fromMatrix(matrix);
        return toMatrix(apacheMatrix.scalarMultiply(1.0/value));
    }

    @Override
    public MatrixStorage divide(MatrixStorage leftMatrix, MatrixStorage rightMatrix){
        int rows = leftMatrix.getNumRows();
        int cols = leftMatrix.getNumCols();

        double[][] simpleLeftMatrix = leftMatrix.asArray();
        double[][] simpleRightMatrix = rightMatrix.asArray();
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                res[i][j] += simpleLeftMatrix[i][j] / simpleRightMatrix[i][j];

        return (new ApacheMatrixStorageFactory()).makeMatrixStorage(res);
    }
}
