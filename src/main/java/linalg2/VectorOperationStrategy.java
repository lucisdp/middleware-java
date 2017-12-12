package linalg2;

import exceptions.LinearAlgebraClassNotFound;

public interface VectorOperationStrategy {
    static VectorOperationStrategy getStrategy(String strategyName){
        if (strategyName.equalsIgnoreCase("simple"))
            return new SimpleOperationStrategy();
        else if (strategyName.equalsIgnoreCase("ojalgo"))
            return new OjalgoOperationStrategy();
        else
            throw new LinearAlgebraClassNotFound(strategyName);
    }

    Vector add(Vector vector, double value);
    Vector add(Vector leftVector, Vector rightVector);

    Vector subtract(Vector vector, double value);
    Vector subtract(Vector leftVector, Vector rightVector);

    Vector multiply(Vector vector, double value);
    Vector multiply(Vector leftVector, Vector rightVector);

    Vector divide(Vector vector, double value);
    Vector divide(Vector leftVector, Vector rightVector);

    double dot(Vector leftVector, Vector rightVector);

    double norm(Vector vector);
}
