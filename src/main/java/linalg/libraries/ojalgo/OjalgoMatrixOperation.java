package linalg.libraries.ojalgo;

import linalg.Matrix;
import linalg.MatrixOperation;
import linalg.Vector;
import org.ojalgo.matrix.PrimitiveMatrix;

/**
 * This class implements all matrix operations by means of the ojAlgo third-party library.
 *
 * @see <a href="http://ojalgo.org">ojAlgo website</a>
 * @see <a href="https://github.com/optimatika/ojAlgo/wiki">ojAlgo wiki</a>
 * @author lucianodp
 */
public class OjalgoMatrixOperation implements MatrixOperation {
    private PrimitiveMatrix fromMatrix(Matrix matrix){
        return ((OjalgoMatrixStorage) matrix.getStorageStrategy()).getRawStorage();
    }

    private Matrix toMatrix(PrimitiveMatrix ojalgoMatrix){
        return new Matrix(new OjalgoMatrixStorage(ojalgoMatrix));
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
        PrimitiveMatrix ojalgoVector = OjalgoVectorOperation.fromVector(vector);
        return OjalgoVectorOperation.toVector(ojalgoMatrix.multiply(ojalgoVector));
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
