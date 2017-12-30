package linalg;

/**
 * <p>This interface provides all matrix-related operations needed by our software. For every Linear Algebra third-party library,
 * we create a corresponding object implementing this interface responsible for performing the desired matrix operation.
 * In this way, we can decouple our code from the Linear Algebra library performing the operations. However, this comes with
 * the cost of, for every iteration, converting to and from our Matrix class and the corresponding matrix storage class of
 * these libraries, which may be costly for large matrices. </p>
 *
 * <p>We note that we have not implemented inplace Matrix operations. These may be added in the future if the need arises.</p>
 *
 * @author lucianodp
 * @see Matrix
 */
public interface MatrixOperation {
    /**
     * Adds a value to all elements of matrix.
     * @param matrix: matrix to perform sum
     * @param value: value to add to matrix
     * @return sum result
     */
    Matrix add(Matrix matrix, double value);

    /**
     * Adds two matrices
     * @param leftMatrix: LHS of sum
     * @param rightMatrix: RHS of sum
     * @return sum result
     */
    Matrix add(Matrix leftMatrix, Matrix rightMatrix);

    /**
     * Subtracts a value from all elements of matrix.
     * @param matrix: matrix to perform subtraction
     * @param value: value to subtract all components of matrix
     * @return subtraction result
     */
    Matrix subtract(Matrix matrix, double value);

    /**
     * Subtracts two given matrices
     * @param leftMatrix: LHS of subtraction
     * @param rightMatrix: RHS of subtraction
     * @return subtraction result
     */
    Matrix subtract(Matrix leftMatrix, Matrix rightMatrix);


    /**
     * Multiplies a value to all elements of matrix.
     * @param matrix: matrix to perform multiplication
     * @param value: value to multiply all components of matrix with
     * @return multiplication result
     */
    Matrix multiply(Matrix matrix, double value);

    /**
     * Matrix-Matrix multiplication
     * @param matrix: LHS of multiplication
     * @param vector: RHS of multiplication
     * @return multiplication result
     */
    Vector multiply(Matrix matrix, Vector vector);

    /**
     * Element-wise multiplication two matrices
     * @param leftMatrix: LHS of multiplication
     * @param rightMatrix: RHS of multiplication
     * @return multiplication result
     */
    Matrix multiplyElement(Matrix leftMatrix, Matrix rightMatrix);

    /**
     * Element-wise multiplication two given matrixs
     * @param leftMatrix: LHS of multiplication
     * @param rightMatrix: RHS of multiplication
     * @return multiplication result
     */
    Matrix multiply(Matrix leftMatrix, Matrix rightMatrix);

    /**
     * Divides all elements in matrix by a value
     * @param matrix: matrix to perform division
     * @param value: value to divide all components of matrix with
     * @return division result
     */
    Matrix divide(Matrix matrix, double value);

    /**
     * Element-wise division of two matrices
     * @param leftMatrix: Numerator of division
     * @param rightMatrix: Denominator of division
     * @return division result
     */
    Matrix divide(Matrix leftMatrix, Matrix rightMatrix);
}