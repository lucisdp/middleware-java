package linalg2;

import org.ojalgo.matrix.PrimitiveMatrix;

public class OjalgoVectorOperationStrategy implements VectorOperationStrategy {

    private PrimitiveMatrix fromVector(Vector vector){
        return PrimitiveMatrix.FACTORY.columns(vector.getValues());
    }

    private Vector toVector(PrimitiveMatrix ojalgoVector){
        int dim = (int) ojalgoVector.countRows();

        double[] res = new double[dim];
        for (int i=0; i < dim; i++)
            res[i] = ojalgoVector.get(i);

        return new Vector(res);
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
}

