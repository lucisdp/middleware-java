package linalg.libraries.apache;


import linalg.Matrix;
import linalg.MatrixOperation;
import linalg.Vector;
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
    private RealMatrix fromMatrix(Matrix matrix){
        return ((ApacheMatrixStorage) matrix.getStorage()).getRawStorage();
    }

    private Matrix toMatrix(RealMatrix apacheMatrix){
        return new Matrix(new ApacheMatrixStorage(apacheMatrix));
    }

    @Override
    public Matrix add(Matrix matrix, double value){
        RealMatrix apacheMatrix = fromMatrix(matrix);
        return toMatrix(apacheMatrix.scalarAdd(value));
    }

    @Override
    public Matrix add(Matrix leftMatrix, Matrix rightMatrix){
        RealMatrix leftApacheMatrix = fromMatrix(leftMatrix);
        RealMatrix rightApacheMatrix = fromMatrix(rightMatrix);
        return toMatrix(leftApacheMatrix.add(rightApacheMatrix));
    }

    @Override
    public Matrix subtract(Matrix matrix, double value){
        RealMatrix apacheMatrix = fromMatrix(matrix);
        return toMatrix(apacheMatrix.scalarAdd(-value));
    }

    @Override
    public Matrix subtract(Matrix leftMatrix, Matrix rightMatrix){
        RealMatrix leftApacheMatrix = fromMatrix(leftMatrix);
        RealMatrix rightApacheMatrix = fromMatrix(rightMatrix);
        return toMatrix(leftApacheMatrix.subtract(rightApacheMatrix));
    }

    @Override
    public Matrix multiply(Matrix matrix, double value){
        RealMatrix apacheMatrix = fromMatrix(matrix);
        return toMatrix(apacheMatrix.scalarMultiply(value));
    }

    @Override
    public Vector multiply(Matrix matrix, Vector vector){
        RealMatrix apacheMatrix = fromMatrix(matrix);
        RealVector apacheVector = ApacheVectorOperation.fromVector(vector);
        return ApacheVectorOperation.toVector(apacheMatrix.operate(apacheVector));
    }

    @Override
    public Matrix multiply(Matrix leftMatrix, Matrix rightMatrix){
        RealMatrix leftApacheMatrix = fromMatrix(leftMatrix);
        RealMatrix rightApacheMatrix = fromMatrix(rightMatrix);
        return toMatrix(leftApacheMatrix.multiply(rightApacheMatrix));
    }

    @Override
    public Matrix multiplyElement(Matrix leftMatrix, Matrix rightMatrix){
        int rows = leftMatrix.getNumRows();
        int cols = leftMatrix.getNumCols();

        double[][] simpleLeftMatrix = leftMatrix.asArray();
        double[][] simpleRightMatrix = rightMatrix.asArray();
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                res[i][j] = simpleLeftMatrix[i][j] * simpleRightMatrix[i][j];

        return new Matrix(res);
    }

    @Override
    public Matrix divide(Matrix matrix, double value){
        RealMatrix apacheMatrix = fromMatrix(matrix);
        return toMatrix(apacheMatrix.scalarMultiply(1.0/value));
    }

    @Override
    public Matrix divide(Matrix leftMatrix, Matrix rightMatrix){
        int rows = leftMatrix.getNumRows();
        int cols = leftMatrix.getNumCols();

        double[][] simpleLeftMatrix = leftMatrix.asArray();
        double[][] simpleRightMatrix = rightMatrix.asArray();
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                res[i][j] += simpleLeftMatrix[i][j] / simpleRightMatrix[i][j];

        return new Matrix(res);
    }
}
