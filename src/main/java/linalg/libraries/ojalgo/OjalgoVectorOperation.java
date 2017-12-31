package linalg.libraries.ojalgo;

import linalg.VectorStorage;
import linalg.VectorOperation;
import org.ojalgo.matrix.PrimitiveMatrix;

/**
 * This class implements all vector operations by means of the ojAlgo third-party library.
 *
 * @see <a href="http://ojalgo.org">ojAlgo website</a>
 * @see <a href="https://github.com/optimatika/ojAlgo/wiki">ojAlgo wiki</a>
 * @author lucianodp
 */
public class OjalgoVectorOperation implements VectorOperation {

    static PrimitiveMatrix fromVector(VectorStorage vector){
        return ((OjalgoVectorStorage) vector).getRawStorage();
    }

    static VectorStorage toVector(PrimitiveMatrix ojalgoVector){
        return new OjalgoVectorStorage(ojalgoVector);
    }

    @Override
    public VectorStorage add(VectorStorage vector, double value){
        PrimitiveMatrix ojalgoVector = fromVector(vector);
        return toVector(ojalgoVector.add(value));
    }

    @Override
    public VectorStorage add(VectorStorage leftVector, VectorStorage rightVector){
        PrimitiveMatrix leftOjalgoVector = fromVector(leftVector);
        PrimitiveMatrix rightOjalgoVector = fromVector(rightVector);
        return toVector(leftOjalgoVector.add(rightOjalgoVector));
    }

    @Override
    public VectorStorage subtract(VectorStorage vector, double value){
        PrimitiveMatrix ojalgoVector = fromVector(vector);
        return toVector(ojalgoVector.add(-value));
    }

    @Override
    public VectorStorage subtract(VectorStorage leftVector, VectorStorage rightVector){
        PrimitiveMatrix leftOjalgoVector = fromVector(leftVector);
        PrimitiveMatrix rightOjalgoVector = fromVector(rightVector);
        return toVector(leftOjalgoVector.subtract(rightOjalgoVector));
    }

    @Override
    public VectorStorage multiply(VectorStorage vector, double value){
        PrimitiveMatrix ojalgoVector = fromVector(vector);
        return toVector(ojalgoVector.multiply(value));
    }

    @Override
    public VectorStorage multiply(VectorStorage leftVector, VectorStorage rightVector){
        PrimitiveMatrix leftOjalgoVector = fromVector(leftVector);
        PrimitiveMatrix rightOjalgoVector = fromVector(rightVector);
        return toVector(leftOjalgoVector.multiplyElements(rightOjalgoVector));
    }

    @Override
    public VectorStorage divide(VectorStorage vector, double value){
        PrimitiveMatrix ojalgoVector = fromVector(vector);
        return toVector(ojalgoVector.divide(value));
    }

    @Override
    public VectorStorage divide(VectorStorage leftVector, VectorStorage rightVector){
        PrimitiveMatrix leftOjalgoVector = fromVector(leftVector);
        PrimitiveMatrix rightOjalgoVector = fromVector(rightVector);
        return toVector(leftOjalgoVector.divideElements(rightOjalgoVector));
    }

    @Override
    public double dot(VectorStorage leftVector, VectorStorage rightVector){
        PrimitiveMatrix leftOjalgoVector = fromVector(leftVector);
        PrimitiveMatrix rightOjalgoVector = fromVector(rightVector);
        return leftOjalgoVector.dot(rightOjalgoVector);
    }

    @Override
    public double norm(VectorStorage vector) {
        PrimitiveMatrix ojalgoVector = fromVector(vector);
        return ojalgoVector.norm();
    }

    @Override
    public boolean equals(VectorStorage vector, double val) {
        PrimitiveMatrix simpleVector = fromVector(vector);

        for (int i=0; i < vector.getDim(); i++){
            if (Math.abs(simpleVector.get(i) - val) > 1e-10)
                return false;
        }
        return true;
    }

    public boolean equals(VectorStorage leftVector, VectorStorage rightVector){
        int dim = leftVector.getDim();
        PrimitiveMatrix simpleLeftVector = fromVector(leftVector);
        PrimitiveMatrix simpleRightVector = fromVector(rightVector);

        for (int i=0; i < dim; i++){
            if (Math.abs(simpleLeftVector.get(i) - simpleRightVector.get(i)) > 1e-10)
                return false;
        }
        return true;
    }

    public boolean isSmallerThan(VectorStorage vector, double val){
        int dim = vector.getDim();
        PrimitiveMatrix simpleVector = fromVector(vector);

        for (int i=0; i < dim; i++){
            if (simpleVector.get(i) >= val)
                return false;
        }
        return true;
    }

    public boolean isSmallerThan(VectorStorage leftVector, VectorStorage rightVector){
        int dim = leftVector.getDim();
        PrimitiveMatrix simpleLeftVector = fromVector(leftVector);
        PrimitiveMatrix simpleRightVector = fromVector(rightVector);

        for (int i=0; i < dim; i++){
            if (simpleLeftVector.get(i) >= simpleRightVector.get(i))
                return false;
        }
        return true;
    }

    public boolean isSmallerOrEqualThan(VectorStorage vector, double val){
        int dim = vector.getDim();
        PrimitiveMatrix simpleVector = fromVector(vector);

        for (int i=0; i < dim; i++){
            if (simpleVector.get(i) > val)
                return false;
        }
        return true;
    }

    public boolean isSmallerOrEqualThan(VectorStorage leftVector, VectorStorage rightVector){
        int dim = leftVector.getDim();
        PrimitiveMatrix simpleLeftVector = fromVector(leftVector);
        PrimitiveMatrix simpleRightVector = fromVector(rightVector);

        for (int i=0; i < dim; i++){
            if (simpleLeftVector.get(i) > simpleRightVector.get(i))
                return false;
        }
        return true;
    }

    public boolean isLargerThan(VectorStorage vector, double val){
        int dim = vector.getDim();
        PrimitiveMatrix simpleVector = fromVector(vector);

        for (int i=0; i < dim; i++){
            if (simpleVector.get(i) <= val)
                return false;
        }
        return true;
    }

    public boolean isLargerThan(VectorStorage leftVector, VectorStorage rightVector){
        int dim = leftVector.getDim();
        PrimitiveMatrix simpleLeftVector = fromVector(leftVector);
        PrimitiveMatrix simpleRightVector = fromVector(rightVector);

        for (int i=0; i < dim; i++){
            if (simpleLeftVector.get(i) <= simpleRightVector.get(i))
                return false;
        }
        return true;
    }

    public boolean isLargerOrEqualThan(VectorStorage vector, double val){
        int dim = vector.getDim();
        PrimitiveMatrix simpleVector = fromVector(vector);

        for (int i=0; i < dim; i++){
            if (simpleVector.get(i) < val)
                return false;
        }
        return true;
    }

    public boolean isLargerOrEqualThan(VectorStorage leftVector, VectorStorage rightVector){
        int dim = leftVector.getDim();
        PrimitiveMatrix simpleLeftVector = fromVector(leftVector);
        PrimitiveMatrix simpleRightVector = fromVector(rightVector);

        for (int i=0; i < dim; i++){
            if (simpleLeftVector.get(i) < simpleRightVector.get(i))
                return false;
        }
        return true;
    }
}

