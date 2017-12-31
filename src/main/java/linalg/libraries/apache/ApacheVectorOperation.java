package linalg.libraries.apache;

import linalg.VectorOperation;
import linalg.VectorStorage;
import org.apache.commons.math3.linear.RealVector;

/**
 * This class implements all vector operations by means of the Apache Commons Math third-party library.
 *
 * @see <a href="http://commons.apache.org/proper/commons-math/">Apache Commons Math website</a>
 * @see <a href="http://commons.apache.org/proper/commons-math/userguide/linear.html">Apache Commons Math linear algebra user guide</a>
 * @author lucianodp
 */
public class ApacheVectorOperation implements VectorOperation {
    
    static RealVector fromVector(VectorStorage vector){
        return ((ApacheVectorStorage) vector).getRawStorage();
    }

    static VectorStorage toVector(RealVector apacheVector){
        return new ApacheVectorStorage(apacheVector);
    }

    @Override
    public VectorStorage add(VectorStorage vector, double value){
        RealVector apacheVector = fromVector(vector);
        return toVector(apacheVector.mapAdd(value));
    }

    @Override
    public VectorStorage add(VectorStorage leftVector, VectorStorage rightVector){
        RealVector leftApacheVector = fromVector(leftVector);
        RealVector rightApacheVector = fromVector(rightVector);
        return toVector(leftApacheVector.add(rightApacheVector));
    }

    @Override
    public VectorStorage subtract(VectorStorage vector, double value){
        RealVector apacheVector = fromVector(vector);
        return toVector(apacheVector.mapSubtract(value));
    }

    @Override
    public VectorStorage subtract(VectorStorage leftVector, VectorStorage rightVector){
        RealVector leftApacheVector = fromVector(leftVector);
        RealVector rightApacheVector = fromVector(rightVector);
        return toVector(leftApacheVector.subtract(rightApacheVector));
    }

    @Override
    public VectorStorage multiply(VectorStorage vector, double value){
        RealVector apacheVector = fromVector(vector);
        return toVector(apacheVector.mapMultiply(value));
    }

    @Override
    public VectorStorage multiply(VectorStorage leftVector, VectorStorage rightVector){
        RealVector leftApacheVector = fromVector(leftVector);
        RealVector rightApacheVector = fromVector(rightVector);
        return toVector(leftApacheVector.ebeMultiply(rightApacheVector));
    }

    @Override
    public VectorStorage divide(VectorStorage vector, double value){
        RealVector apacheVector = fromVector(vector);
        return toVector(apacheVector.mapDivide(value));
    }

    @Override
    public VectorStorage divide(VectorStorage leftVector, VectorStorage rightVector){
        RealVector leftApacheVector = fromVector(leftVector);
        RealVector rightApacheVector = fromVector(rightVector);
        return toVector(leftApacheVector.ebeDivide(rightApacheVector));
    }

    @Override
    public double dot(VectorStorage leftVector, VectorStorage rightVector){
        RealVector leftApacheVector = fromVector(leftVector);
        RealVector rightApacheVector = fromVector(rightVector);
        return leftApacheVector.dotProduct(rightApacheVector);
    }

    @Override
    public double norm(VectorStorage vector) {
        RealVector apacheVector = fromVector(vector);
        return apacheVector.getNorm();
    }

    @Override
    public boolean equals(VectorStorage vector, double val) {
        RealVector apacheVector = fromVector(vector);

        for (int i=0; i < vector.getDim(); i++){
            if (Math.abs(apacheVector.getEntry(i) - val) > 1e-10)
                return false;
        }
        return true;
    }

    public boolean equals(VectorStorage leftVector, VectorStorage rightVector){
        RealVector apacheLeftVector = fromVector(leftVector);
        RealVector apacheRightVector = fromVector(rightVector);
        return apacheLeftVector.equals(apacheRightVector);
    }

    public boolean isSmallerThan(VectorStorage vector, double val){
        int dim = vector.getDim();
        RealVector apacheVector = fromVector(vector);

        for (int i=0; i < dim; i++){
            if (apacheVector.getEntry(i) >= val)
                return false;
        }
        return true;
    }

    public boolean isSmallerThan(VectorStorage leftVector, VectorStorage rightVector){
        int dim = leftVector.getDim();
        RealVector apacheLeftVector = fromVector(leftVector);
        RealVector apacheRightVector = fromVector(rightVector);

        for (int i=0; i < dim; i++){
            if (apacheLeftVector.getEntry(i) >= apacheRightVector.getEntry(i))
                return false;
        }
        return true;
    }

    public boolean isSmallerOrEqualThan(VectorStorage vector, double val){
        int dim = vector.getDim();
        RealVector apacheVector = fromVector(vector);

        for (int i=0; i < dim; i++){
            if (apacheVector.getEntry(i) > val)
                return false;
        }
        return true;
    }

    public boolean isSmallerOrEqualThan(VectorStorage leftVector, VectorStorage rightVector){
        int dim = leftVector.getDim();
        RealVector apacheLeftVector = fromVector(leftVector);
        RealVector apacheRightVector = fromVector(rightVector);

        for (int i=0; i < dim; i++){
            if (apacheLeftVector.getEntry(i) > apacheRightVector.getEntry(i))
                return false;
        }
        return true;
    }

    public boolean isLargerThan(VectorStorage vector, double val){
        int dim = vector.getDim();
        RealVector apacheVector = fromVector(vector);

        for (int i=0; i < dim; i++){
            if (apacheVector.getEntry(i) <= val)
                return false;
        }
        return true;
    }

    public boolean isLargerThan(VectorStorage leftVector, VectorStorage rightVector){
        int dim = leftVector.getDim();
        RealVector apacheLeftVector = fromVector(leftVector);
        RealVector apacheRightVector = fromVector(rightVector);

        for (int i=0; i < dim; i++){
            if (apacheLeftVector.getEntry(i) <= apacheRightVector.getEntry(i))
                return false;
        }
        return true;
    }

    public boolean isLargerOrEqualThan(VectorStorage vector, double val){
        int dim = vector.getDim();
        RealVector apacheVector = fromVector(vector);

        for (int i=0; i < dim; i++){
            if (apacheVector.getEntry(i) < val)
                return false;
        }
        return true;
    }

    public boolean isLargerOrEqualThan(VectorStorage leftVector, VectorStorage rightVector){
        int dim = leftVector.getDim();
        RealVector apacheLeftVector = fromVector(leftVector);
        RealVector apacheRightVector = fromVector(rightVector);

        for (int i=0; i < dim; i++)
            if (apacheLeftVector.getEntry(i) < apacheRightVector.getEntry(i))
                return false;
        return true;
    }
}
