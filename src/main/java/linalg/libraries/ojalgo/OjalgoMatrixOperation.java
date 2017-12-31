package linalg.libraries.ojalgo;

import exceptions.IncompatibleLinearAlgebraLibraryException;
import linalg.MatrixOperation;
import linalg.MatrixStorage;
import linalg.VectorStorage;
import org.ojalgo.matrix.PrimitiveMatrix;

/**
 * This class implements all matrix operations by means of the ojAlgo third-party library.
 *
 * @see <a href="http://ojalgo.org">ojAlgo website</a>
 * @see <a href="https://github.com/optimatika/ojAlgo/wiki">ojAlgo wiki</a>
 * @author lucianodp
 */
public class OjalgoMatrixOperation implements MatrixOperation {
    private PrimitiveMatrix fromMatrix(MatrixStorage matrix){
        try {
            return ((OjalgoMatrixStorage) matrix).getRawStorage();
        } catch (ClassCastException ex){
            throw new IncompatibleLinearAlgebraLibraryException();
        }
    }

    private MatrixStorage toMatrix(PrimitiveMatrix ojalgoMatrix){
        return new OjalgoMatrixStorage(ojalgoMatrix);
    }

    @Override
    public MatrixStorage add(MatrixStorage matrix, double value){
        PrimitiveMatrix ojalgoMatrix = fromMatrix(matrix);
        return toMatrix(ojalgoMatrix.add(value));
    }

    @Override
    public MatrixStorage add(MatrixStorage leftMatrix, MatrixStorage rightMatrix){
        return toMatrix(fromMatrix(leftMatrix).add(fromMatrix(rightMatrix)));
    }

    @Override
    public MatrixStorage subtract(MatrixStorage matrix, double value){
        return toMatrix(fromMatrix(matrix).add(-value));
    }

    @Override
    public MatrixStorage subtract(MatrixStorage leftMatrix, MatrixStorage rightMatrix){
        PrimitiveMatrix leftOjalgoMatrix = fromMatrix(leftMatrix);
        PrimitiveMatrix rightOjalgoMatrix = fromMatrix(rightMatrix);
        return toMatrix(leftOjalgoMatrix.subtract(rightOjalgoMatrix));
    }

    @Override
    public MatrixStorage multiply(MatrixStorage matrix, double value){
        PrimitiveMatrix ojalgoMatrix = fromMatrix(matrix);
        return toMatrix(ojalgoMatrix.multiply(value));
    }

    @Override
    public VectorStorage multiply(MatrixStorage matrix, VectorStorage vector){
        PrimitiveMatrix ojalgoMatrix = fromMatrix(matrix);
        PrimitiveMatrix ojalgoVector = OjalgoVectorOperation.fromVector(vector);
        return OjalgoVectorOperation.toVector(ojalgoMatrix.multiply(ojalgoVector));
    }

    @Override
    public MatrixStorage multiply(MatrixStorage leftMatrix, MatrixStorage rightMatrix){
        PrimitiveMatrix leftOjalgoMatrix = fromMatrix(leftMatrix);
        PrimitiveMatrix rightOjalgoMatrix = fromMatrix(rightMatrix);
        return toMatrix(leftOjalgoMatrix.multiply(rightOjalgoMatrix));
    }

    @Override
    public MatrixStorage multiplyElement(MatrixStorage leftMatrix, MatrixStorage rightMatrix){
        PrimitiveMatrix leftOjalgoMatrix = fromMatrix(leftMatrix);
        PrimitiveMatrix rightOjalgoMatrix = fromMatrix(rightMatrix);
        return toMatrix(leftOjalgoMatrix.multiplyElements(rightOjalgoMatrix));
    }

    @Override
    public MatrixStorage divide(MatrixStorage matrix, double value){
        PrimitiveMatrix ojalgoMatrix = fromMatrix(matrix);
        return toMatrix(ojalgoMatrix.divide(value));
    }

    @Override
    public MatrixStorage divide(MatrixStorage leftMatrix, MatrixStorage rightMatrix){
        PrimitiveMatrix leftOjalgoMatrix = fromMatrix(leftMatrix);
        PrimitiveMatrix rightOjalgoMatrix = fromMatrix(rightMatrix);
        return toMatrix(leftOjalgoMatrix.divideElements(rightOjalgoMatrix));
    }
}
