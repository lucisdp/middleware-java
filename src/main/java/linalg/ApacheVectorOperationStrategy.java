package linalg;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

/**
 * This class implements all vector operations by means of the Apache Commons Math third-party library.
 *
 * @see <a href="http://commons.apache.org/proper/commons-math/">Apache Commons Math website</a>
 * @see <a href="http://commons.apache.org/proper/commons-math/userguide/linear.html">Apache Commons Math linear algebra user guide</a>
 * @author lucianodp
 */
public class ApacheVectorOperationStrategy implements VectorOperationStrategy {
    
    static RealVector fromVector(Vector vector){ return new ArrayRealVector(vector.getValues()); }

    static Vector toVector(RealVector apacheVector){
        return new Vector(apacheVector.toArray());
    }

    @Override
    public Vector add(Vector vector, double value){
        RealVector apacheVector = fromVector(vector);
        return toVector(apacheVector.mapAdd(value));
    }

    @Override
    public Vector add(Vector leftVector, Vector rightVector){
        RealVector leftApacheVector = fromVector(leftVector);
        RealVector rightApacheVector = fromVector(rightVector);
        return toVector(leftApacheVector.add(rightApacheVector));
    }

    @Override
    public Vector subtract(Vector vector, double value){
        RealVector apacheVector = fromVector(vector);
        return toVector(apacheVector.mapSubtract(value));
    }

    @Override
    public Vector subtract(Vector leftVector, Vector rightVector){
        RealVector leftApacheVector = fromVector(leftVector);
        RealVector rightApacheVector = fromVector(rightVector);
        return toVector(leftApacheVector.subtract(rightApacheVector));
    }

    @Override
    public Vector multiply(Vector vector, double value){
        RealVector apacheVector = fromVector(vector);
        return toVector(apacheVector.mapMultiply(value));
    }

    @Override
    public Vector multiply(Vector leftVector, Vector rightVector){
        RealVector leftApacheVector = fromVector(leftVector);
        RealVector rightApacheVector = fromVector(rightVector);
        return toVector(leftApacheVector.ebeMultiply(rightApacheVector));
    }

    @Override
    public Vector divide(Vector vector, double value){
        RealVector apacheVector = fromVector(vector);
        return toVector(apacheVector.mapDivide(value));
    }

    @Override
    public Vector divide(Vector leftVector, Vector rightVector){
        RealVector leftApacheVector = fromVector(leftVector);
        RealVector rightApacheVector = fromVector(rightVector);
        return toVector(leftApacheVector.ebeDivide(rightApacheVector));
    }

    @Override
    public double dot(Vector leftVector, Vector rightVector){
        RealVector leftApacheVector = fromVector(leftVector);
        RealVector rightApacheVector = fromVector(rightVector);
        return leftApacheVector.dotProduct(rightApacheVector);
    }

    @Override
    public double norm(Vector vector) {
        RealVector apacheVector = fromVector(vector);
        return apacheVector.getNorm();
    }

    @Override
    public boolean equals(Vector vector, double val) {
        RealVector apacheVector = fromVector(vector);

        for (int i=0; i < vector.getDim(); i++){
            if (Math.abs(apacheVector.getEntry(i) - val) > 1e-10)
                return false;
        }
        return true;
    }

    public boolean equals(Vector leftVector, Vector rightVector){
        RealVector apacheLeftVector = fromVector(leftVector);
        RealVector apacheRightVector = fromVector(rightVector);
        return apacheLeftVector.equals(apacheRightVector);
    }

    public boolean isSmallerThan(Vector vector, double val){
        int dim = vector.getDim();
        RealVector apacheVector = fromVector(vector);

        for (int i=0; i < dim; i++){
            if (apacheVector.getEntry(i) >= val)
                return false;
        }
        return true;
    }

    public boolean isSmallerThan(Vector leftVector, Vector rightVector){
        int dim = leftVector.getDim();
        RealVector apacheLeftVector = fromVector(leftVector);
        RealVector apacheRightVector = fromVector(rightVector);

        for (int i=0; i < dim; i++){
            if (apacheLeftVector.getEntry(i) >= apacheRightVector.getEntry(i))
                return false;
        }
        return true;
    }

    public boolean isSmallerOrEqualThan(Vector vector, double val){
        int dim = vector.getDim();
        RealVector apacheVector = fromVector(vector);

        for (int i=0; i < dim; i++){
            if (apacheVector.getEntry(i) > val)
                return false;
        }
        return true;
    }

    public boolean isSmallerOrEqualThan(Vector leftVector, Vector rightVector){
        int dim = leftVector.getDim();
        RealVector apacheLeftVector = fromVector(leftVector);
        RealVector apacheRightVector = fromVector(rightVector);

        for (int i=0; i < dim; i++){
            if (apacheLeftVector.getEntry(i) > apacheRightVector.getEntry(i))
                return false;
        }
        return true;
    }

    public boolean isLargerThan(Vector vector, double val){
        int dim = vector.getDim();
        RealVector apacheVector = fromVector(vector);

        for (int i=0; i < dim; i++){
            if (apacheVector.getEntry(i) <= val)
                return false;
        }
        return true;
    }

    public boolean isLargerThan(Vector leftVector, Vector rightVector){
        int dim = leftVector.getDim();
        RealVector apacheLeftVector = fromVector(leftVector);
        RealVector apacheRightVector = fromVector(rightVector);

        for (int i=0; i < dim; i++){
            if (apacheLeftVector.getEntry(i) <= apacheRightVector.getEntry(i))
                return false;
        }
        return true;
    }

    public boolean isLargerOrEqualThan(Vector vector, double val){
        int dim = vector.getDim();
        RealVector apacheVector = fromVector(vector);

        for (int i=0; i < dim; i++){
            if (apacheVector.getEntry(i) < val)
                return false;
        }
        return true;
    }

    public boolean isLargerOrEqualThan(Vector leftVector, Vector rightVector){
        int dim = leftVector.getDim();
        RealVector apacheLeftVector = fromVector(leftVector);
        RealVector apacheRightVector = fromVector(rightVector);

        for (int i=0; i < dim; i++)
            if (apacheLeftVector.getEntry(i) < apacheRightVector.getEntry(i))
                return false;
        return true;
    }
}
