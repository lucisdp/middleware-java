package linalg;

import exceptions.LinearAlgebraClassNotFound;

public interface MatrixOperationStrategy {
    static MatrixOperationStrategy getStrategy(String strategyName){
        if (strategyName.equalsIgnoreCase("simple"))
            return new SimpleMatrixOperationStrategy();
        else if (strategyName.equalsIgnoreCase("ojalgo"))
            return new OjalgoMatrixOperationStrategy();
        else
            throw new LinearAlgebraClassNotFound(strategyName);
    }

    Matrix add(Matrix matrix, double value);
    Matrix add(Matrix leftMatrix, Matrix rightMatrix);

    Matrix subtract(Matrix matrix, double value);
    Matrix subtract(Matrix leftMatrix, Matrix rightMatrix);

    Matrix multiply(Matrix matrix, double value);
    Vector multiply(Matrix matrix, Vector vector);
    Matrix multiplyElement(Matrix leftMatrix, Matrix rightMatrix);
    Matrix multiply(Matrix leftMatrix, Matrix rightMatrix);

    Matrix divide(Matrix matrix, double value);
    Matrix divide(Matrix leftMatrix, Matrix rightMatrix);
}
