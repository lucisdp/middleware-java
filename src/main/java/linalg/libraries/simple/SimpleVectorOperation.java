package linalg.libraries.simple;

import exceptions.IncompatibleLinearAlgebraLibraryException;
import linalg.VectorStorage;
import linalg.VectorOperation;

/**
 * This is a naive implementation of all vector operations using double[] arrays.
 *
 * @author lucianodp
 */
public class SimpleVectorOperation implements VectorOperation {
    static double[] fromVector(VectorStorage vector){
        try {
            return ((SimpleVectorStorage) vector).getRawStorage();
        } catch (ClassCastException ex){
            throw new IncompatibleLinearAlgebraLibraryException();
        }
    }

    static VectorStorage toVector(double[] simpleVector){
        return new SimpleVectorStorage(simpleVector);
    }

    @Override
    public VectorStorage add(VectorStorage vector, double value){
        int dim = vector.getDim();
        double[] simpleVector = fromVector(vector);
        double[] res = new double[dim];

        for(int i=0; i < dim; i++)
            res[i] = simpleVector[i] + value;

        return toVector(res);
    }

    @Override
    public VectorStorage add(VectorStorage leftVector, VectorStorage rightVector){
        int dim = leftVector.getDim();
        double[] simpleLeftVector = fromVector(leftVector);
        double[] simpleRightVector = fromVector(rightVector);
        double[] res = new double[dim];

        for(int i=0; i < dim; i++)
            res[i] = simpleLeftVector[i] + simpleRightVector[i];

        return toVector(res);
    }

    @Override
    public VectorStorage subtract(VectorStorage vector, double value){
        int dim = vector.getDim();
        double[] simpleVector = fromVector(vector);
        double[] res = new double[dim];

        for(int i=0; i < dim; i++)
            res[i] = simpleVector[i] - value;

        return toVector(res);
    }

    @Override
    public VectorStorage subtract(VectorStorage leftVector, VectorStorage rightVector){
        int dim = leftVector.getDim();
        double[] simpleLeftVector = fromVector(leftVector);
        double[] simpleRightVector = fromVector(rightVector);
        double[] res = new double[dim];

        for(int i=0; i < dim; i++)
            res[i] = simpleLeftVector[i] - simpleRightVector[i];

        return toVector(res);
    }

    @Override
    public VectorStorage multiply(VectorStorage vector, double value){
        int dim = vector.getDim();
        double[] simpleVector = fromVector(vector);
        double[] res = new double[dim];

        for(int i=0; i < dim; i++)
            res[i] = simpleVector[i] * value;

        return toVector(res);
    }

    @Override
    public VectorStorage multiply(VectorStorage leftVector, VectorStorage rightVector){
        int dim = leftVector.getDim();
        double[] simpleLeftVector = fromVector(leftVector);
        double[] simpleRightVector = fromVector(rightVector);
        double[] res = new double[dim];

        for(int i=0; i < dim; i++)
            res[i] = simpleLeftVector[i] * simpleRightVector[i];

        return toVector(res);
    }

    @Override
    public VectorStorage divide(VectorStorage vector, double value){
        int dim = vector.getDim();
        double[] simpleVector = fromVector(vector);
        double[] res = new double[dim];

        for(int i=0; i < dim; i++)
            res[i] = simpleVector[i] / value;

        return toVector(res);
    }

    @Override
    public VectorStorage divide(VectorStorage leftVector, VectorStorage rightVector){
        int dim = leftVector.getDim();
        double[] simpleLeftVector = fromVector(leftVector);
        double[] simpleRightVector = fromVector(rightVector);
        double[] res = new double[dim];

        for(int i=0; i < dim; i++)
            res[i] = simpleLeftVector[i] / simpleRightVector[i];

        return toVector(res);
    }

    @Override
    public double dot(VectorStorage leftVector, VectorStorage rightVector){
        int dim = leftVector.getDim();
        double[] simpleLeftVector = fromVector(leftVector);
        double[] simpleRightVector = fromVector(rightVector);
        double sum = 0;

        for(int i=0; i < dim; i++)
            sum += simpleLeftVector[i] * simpleRightVector[i];

        return sum;
    }

    @Override
    public double norm(VectorStorage vector) {
        return Math.sqrt(this.dot(vector, vector));
    }

    @Override
    public boolean equals(VectorStorage vector, double val) {
        double[] simpleVector = fromVector(vector);

        for (int i=0; i < vector.getDim(); i++){
            if (Math.abs(simpleVector[i] - val) > 1e-10)
                return false;
        }
        return true;
    }

    @Override
    public boolean equals(VectorStorage leftVector, VectorStorage rightVector){
        int dim = leftVector.getDim();
        double[] simpleLeftVector = fromVector(leftVector);
        double[] simpleRightVector = fromVector(rightVector);

        for (int i=0; i < dim; i++){
            if (Math.abs(simpleLeftVector[i] - simpleRightVector[i]) > 1e-10)
                return false;
        }
        return true;
    }

    @Override
    public boolean isSmallerThan(VectorStorage vector, double val){
        int dim = vector.getDim();
        double[] simpleVector = fromVector(vector);

        for (int i=0; i < dim; i++){
            if (simpleVector[i] >= val)
                return false;
        }
        return true;
    }

    @Override
    public boolean isSmallerThan(VectorStorage leftVector, VectorStorage rightVector){
         int dim = leftVector.getDim();
         double[] simpleLeftVector = fromVector(leftVector);
         double[] simpleRightVector = fromVector(rightVector);

         for (int i=0; i < dim; i++){
             if (simpleLeftVector[i] >= simpleRightVector[i])
                 return false;
         }
         return true;
    }

    @Override
    public boolean isSmallerOrEqualThan(VectorStorage vector, double val){
         int dim = vector.getDim();
         double[] simpleVector = fromVector(vector);

         for (int i=0; i < dim; i++){
             if (simpleVector[i] > val)
                 return false;
         }
         return true;
    }

    @Override
    public boolean isSmallerOrEqualThan(VectorStorage leftVector, VectorStorage rightVector){
         int dim = leftVector.getDim();
         double[] simpleLeftVector = fromVector(leftVector);
         double[] simpleRightVector = fromVector(rightVector);

         for (int i=0; i < dim; i++){
             if (simpleLeftVector[i] > simpleRightVector[i])
                 return false;
         }
         return true;
    }

    @Override
    public boolean isLargerThan(VectorStorage vector, double val){
         int dim = vector.getDim();
         double[] simpleVector = fromVector(vector);

         for (int i=0; i < dim; i++){
             if (simpleVector[i] <= val)
                 return false;
         }
         return true;
    }

    @Override
    public boolean isLargerThan(VectorStorage leftVector, VectorStorage rightVector){
         int dim = leftVector.getDim();
         double[] simpleLeftVector = fromVector(leftVector);
         double[] simpleRightVector = fromVector(rightVector);

         for (int i=0; i < dim; i++){
             if (simpleLeftVector[i] <= simpleRightVector[i])
                 return false;
         }
         return true;
    }

    @Override
    public boolean isLargerOrEqualThan(VectorStorage vector, double val){
         int dim = vector.getDim();
         double[] simpleVector = fromVector(vector);

         for (int i=0; i < dim; i++){
             if (simpleVector[i] < val)
                 return false;
         }
         return true;
    }

    @Override
    public boolean isLargerOrEqualThan(VectorStorage leftVector, VectorStorage rightVector){
         int dim = leftVector.getDim();
         double[] simpleLeftVector = fromVector(leftVector);
         double[] simpleRightVector = fromVector(rightVector);

         for (int i=0; i < dim; i++){
             if (simpleLeftVector[i] < simpleRightVector[i])
                 return false;
         }
         return true;
    }
}
