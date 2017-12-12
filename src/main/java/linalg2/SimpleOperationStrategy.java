package linalg2;

public class SimpleOperationStrategy implements VectorOperationStrategy {
    private double[] fromVector(Vector vector){
        return vector.getValues();
    }

    private Vector toVector(double[] simpleVector){
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
}
