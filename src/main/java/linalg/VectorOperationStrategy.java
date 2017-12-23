package linalg;

import exceptions.LinearAlgebraClassNotFound;

public interface VectorOperationStrategy {
    static VectorOperationStrategy getStrategy(String strategyName){
        if (strategyName.equalsIgnoreCase("simple"))
            return new SimpleVectorOperationStrategy();
        else if (strategyName.equalsIgnoreCase("ojalgo"))
            return new OjalgoVectorOperationStrategy();
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

    boolean equals(Vector leftVector, Vector rightVector);
    boolean equals(Vector vector, double val);
    boolean isSmallerThan(Vector vector, double val);
    boolean isSmallerThan(Vector leftVector, Vector rightVector);
    boolean isSmallerOrEqualThan(Vector vector, double val);
    boolean isSmallerOrEqualThan(Vector leftVector, Vector rightVector);
    boolean isLargerThan(Vector vector, double val);
    boolean isLargerThan(Vector leftVector, Vector rightVector);
    boolean isLargerOrEqualThan(Vector vector, double val);
    boolean isLargerOrEqualThan(Vector leftVector, Vector rightVector);
}
