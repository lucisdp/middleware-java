package linalg.libraries.ojalgo;

import linalg.Vector;
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

    static PrimitiveMatrix fromVector(Vector vector){
        // TODO: add try-catch for incompatible storage and operation strategies
        return ((OjalgoVectorStorage) vector.getStorageStrategy()).getRawStorage();
    }

    static Vector toVector(PrimitiveMatrix ojalgoVector){
        return new Vector(new OjalgoVectorStorage(ojalgoVector));
    }

    @Override
    public Vector add(Vector vector, double value){
        PrimitiveMatrix ojalgoVector = fromVector(vector);
        return toVector(ojalgoVector.add(value));
    }

    @Override
    public Vector add(Vector leftVector, Vector rightVector){
        PrimitiveMatrix leftOjalgoVector = fromVector(leftVector);
        PrimitiveMatrix rightOjalgoVector = fromVector(rightVector);
        return toVector(leftOjalgoVector.add(rightOjalgoVector));
    }

    @Override
    public Vector subtract(Vector vector, double value){
        PrimitiveMatrix ojalgoVector = fromVector(vector);
        return toVector(ojalgoVector.add(-value));
    }

    @Override
    public Vector subtract(Vector leftVector, Vector rightVector){
        PrimitiveMatrix leftOjalgoVector = fromVector(leftVector);
        PrimitiveMatrix rightOjalgoVector = fromVector(rightVector);
        return toVector(leftOjalgoVector.subtract(rightOjalgoVector));
    }

    @Override
    public Vector multiply(Vector vector, double value){
        PrimitiveMatrix ojalgoVector = fromVector(vector);
        return toVector(ojalgoVector.multiply(value));
    }

    @Override
    public Vector multiply(Vector leftVector, Vector rightVector){
        PrimitiveMatrix leftOjalgoVector = fromVector(leftVector);
        PrimitiveMatrix rightOjalgoVector = fromVector(rightVector);
        return toVector(leftOjalgoVector.multiplyElements(rightOjalgoVector));
    }

    @Override
    public Vector divide(Vector vector, double value){
        PrimitiveMatrix ojalgoVector = fromVector(vector);
        return toVector(ojalgoVector.divide(value));
    }

    @Override
    public Vector divide(Vector leftVector, Vector rightVector){
        PrimitiveMatrix leftOjalgoVector = fromVector(leftVector);
        PrimitiveMatrix rightOjalgoVector = fromVector(rightVector);
        return toVector(leftOjalgoVector.divideElements(rightOjalgoVector));
    }

    @Override
    public double dot(Vector leftVector, Vector rightVector){
        PrimitiveMatrix leftOjalgoVector = fromVector(leftVector);
        PrimitiveMatrix rightOjalgoVector = fromVector(rightVector);
        return leftOjalgoVector.dot(rightOjalgoVector);
    }

    @Override
    public double norm(Vector vector) {
        PrimitiveMatrix ojalgoVector = fromVector(vector);
        return ojalgoVector.norm();
    }

    @Override
    public boolean equals(Vector vector, double val) {
        PrimitiveMatrix simpleVector = fromVector(vector);

        for (int i=0; i < vector.getDim(); i++){
            if (Math.abs(simpleVector.get(i) - val) > 1e-10)
                return false;
        }
        return true;
    }

    public boolean equals(Vector leftVector, Vector rightVector){
        int dim = leftVector.getDim();
        PrimitiveMatrix simpleLeftVector = fromVector(leftVector);
        PrimitiveMatrix simpleRightVector = fromVector(rightVector);

        for (int i=0; i < dim; i++){
            if (Math.abs(simpleLeftVector.get(i) - simpleRightVector.get(i)) > 1e-10)
                return false;
        }
        return true;
    }

    public boolean isSmallerThan(Vector vector, double val){
        int dim = vector.getDim();
        PrimitiveMatrix simpleVector = fromVector(vector);

        for (int i=0; i < dim; i++){
            if (simpleVector.get(i) >= val)
                return false;
        }
        return true;
    }

    public boolean isSmallerThan(Vector leftVector, Vector rightVector){
        int dim = leftVector.getDim();
        PrimitiveMatrix simpleLeftVector = fromVector(leftVector);
        PrimitiveMatrix simpleRightVector = fromVector(rightVector);

        for (int i=0; i < dim; i++){
            if (simpleLeftVector.get(i) >= simpleRightVector.get(i))
                return false;
        }
        return true;
    }

    public boolean isSmallerOrEqualThan(Vector vector, double val){
        int dim = vector.getDim();
        PrimitiveMatrix simpleVector = fromVector(vector);

        for (int i=0; i < dim; i++){
            if (simpleVector.get(i) > val)
                return false;
        }
        return true;
    }

    public boolean isSmallerOrEqualThan(Vector leftVector, Vector rightVector){
        int dim = leftVector.getDim();
        PrimitiveMatrix simpleLeftVector = fromVector(leftVector);
        PrimitiveMatrix simpleRightVector = fromVector(rightVector);

        for (int i=0; i < dim; i++){
            if (simpleLeftVector.get(i) > simpleRightVector.get(i))
                return false;
        }
        return true;
    }

    public boolean isLargerThan(Vector vector, double val){
        int dim = vector.getDim();
        PrimitiveMatrix simpleVector = fromVector(vector);

        for (int i=0; i < dim; i++){
            if (simpleVector.get(i) <= val)
                return false;
        }
        return true;
    }

    public boolean isLargerThan(Vector leftVector, Vector rightVector){
        int dim = leftVector.getDim();
        PrimitiveMatrix simpleLeftVector = fromVector(leftVector);
        PrimitiveMatrix simpleRightVector = fromVector(rightVector);

        for (int i=0; i < dim; i++){
            if (simpleLeftVector.get(i) <= simpleRightVector.get(i))
                return false;
        }
        return true;
    }

    public boolean isLargerOrEqualThan(Vector vector, double val){
        int dim = vector.getDim();
        PrimitiveMatrix simpleVector = fromVector(vector);

        for (int i=0; i < dim; i++){
            if (simpleVector.get(i) < val)
                return false;
        }
        return true;
    }

    public boolean isLargerOrEqualThan(Vector leftVector, Vector rightVector){
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

