package linalg.libraries.simple;

import linalg.Vector;
import linalg.VectorOperationStrategy;

/**
 * This is a naive implementation of all vector operations using double[] arrays.
 *
 * @author lucianodp
 */
public class SimpleVectorOperationStrategy implements VectorOperationStrategy {
    static double[] fromVector(Vector vector){
        return vector.getValues();
    }

    static Vector toVector(double[] simpleVector){
        return new Vector(simpleVector);
    }

    @Override
    public Vector add(Vector vector, double value){
        int dim = vector.getDim();
        double[] simpleVector = fromVector(vector);
        double[] res = new double[dim];

        for(int i=0; i < dim; i++)
            res[i] = simpleVector[i] + value;

        return toVector(res);
    }

    @Override
    public Vector add(Vector leftVector, Vector rightVector){
        int dim = leftVector.getDim();
        double[] simpleLeftVector = fromVector(leftVector);
        double[] simpleRightVector = fromVector(rightVector);
        double[] res = new double[dim];

        for(int i=0; i < dim; i++)
            res[i] = simpleLeftVector[i] + simpleRightVector[i];

        return toVector(res);
    }

    @Override
    public Vector subtract(Vector vector, double value){
        int dim = vector.getDim();
        double[] simpleVector = fromVector(vector);
        double[] res = new double[dim];

        for(int i=0; i < dim; i++)
            res[i] = simpleVector[i] - value;

        return toVector(res);
    }

    @Override
    public Vector subtract(Vector leftVector, Vector rightVector){
        int dim = leftVector.getDim();
        double[] simpleLeftVector = fromVector(leftVector);
        double[] simpleRightVector = fromVector(rightVector);
        double[] res = new double[dim];

        for(int i=0; i < dim; i++)
            res[i] = simpleLeftVector[i] - simpleRightVector[i];

        return toVector(res);
    }

    @Override
    public Vector multiply(Vector vector, double value){
        int dim = vector.getDim();
        double[] simpleVector = fromVector(vector);
        double[] res = new double[dim];

        for(int i=0; i < dim; i++)
            res[i] = simpleVector[i] * value;

        return toVector(res);
    }

    @Override
    public Vector multiply(Vector leftVector, Vector rightVector){
        int dim = leftVector.getDim();
        double[] simpleLeftVector = fromVector(leftVector);
        double[] simpleRightVector = fromVector(rightVector);
        double[] res = new double[dim];

        for(int i=0; i < dim; i++)
            res[i] = simpleLeftVector[i] * simpleRightVector[i];

        return toVector(res);
    }

    @Override
    public Vector divide(Vector vector, double value){
        int dim = vector.getDim();
        double[] simpleVector = fromVector(vector);
        double[] res = new double[dim];

        for(int i=0; i < dim; i++)
            res[i] = simpleVector[i] / value;

        return toVector(res);
    }

    @Override
    public Vector divide(Vector leftVector, Vector rightVector){
        int dim = leftVector.getDim();
        double[] simpleLeftVector = fromVector(leftVector);
        double[] simpleRightVector = fromVector(rightVector);
        double[] res = new double[dim];

        for(int i=0; i < dim; i++)
            res[i] = simpleLeftVector[i] / simpleRightVector[i];

        return toVector(res);
    }

    @Override
    public double dot(Vector leftVector, Vector rightVector){
        int dim = leftVector.getDim();
        double[] simpleLeftVector = fromVector(leftVector);
        double[] simpleRightVector = fromVector(rightVector);
        double sum = 0;

        for(int i=0; i < dim; i++)
            sum += simpleLeftVector[i] * simpleRightVector[i];

        return sum;
    }

    @Override
    public double norm(Vector vector) {
        return Math.sqrt(this.dot(vector, vector));
    }

    @Override
    public boolean equals(Vector vector, double val) {
        double[] simpleVector = fromVector(vector);

        for (int i=0; i < vector.getDim(); i++){
            if (Math.abs(simpleVector[i] - val) > 1e-10)
                return false;
        }
        return true;
    }

    @Override
    public boolean equals(Vector leftVector, Vector rightVector){
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
    public boolean isSmallerThan(Vector vector, double val){
        int dim = vector.getDim();
        double[] simpleVector = fromVector(vector);

        for (int i=0; i < dim; i++){
            if (simpleVector[i] >= val)
                return false;
        }
        return true;
    }

    @Override
    public boolean isSmallerThan(Vector leftVector, Vector rightVector){
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
    public boolean isSmallerOrEqualThan(Vector vector, double val){
         int dim = vector.getDim();
         double[] simpleVector = fromVector(vector);

         for (int i=0; i < dim; i++){
             if (simpleVector[i] > val)
                 return false;
         }
         return true;
    }

    @Override
    public boolean isSmallerOrEqualThan(Vector leftVector, Vector rightVector){
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
    public boolean isLargerThan(Vector vector, double val){
         int dim = vector.getDim();
         double[] simpleVector = fromVector(vector);

         for (int i=0; i < dim; i++){
             if (simpleVector[i] <= val)
                 return false;
         }
         return true;
    }

    @Override
    public boolean isLargerThan(Vector leftVector, Vector rightVector){
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
    public boolean isLargerOrEqualThan(Vector vector, double val){
         int dim = vector.getDim();
         double[] simpleVector = fromVector(vector);

         for (int i=0; i < dim; i++){
             if (simpleVector[i] < val)
                 return false;
         }
         return true;
    }

    @Override
    public boolean isLargerOrEqualThan(Vector leftVector, Vector rightVector){
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
