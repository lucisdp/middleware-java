package linalg;

/**
 * <p>This interface provides all vector-related operations needed by our software. For every Linear Algebra third-party library,
 * we create a corresponding object implementing this interface responsible for performing the desired Vector operation.
 * In this way, we can decouple our code from the Linear Algebra library performing the operations. However, this comes with
 * the cost of, for every iteration, converting to and from our Vector class and the corresponding Vector storage class of
 * these libraries, which may be costly for large vectors. </p>
 *
 * <p>We note that we have not implemented inplace vector operations. These may be added in the future if the need arises.</p>
 *
 * @author lucianodp
 * @see Vector
 */
public interface VectorOperationStrategy {
    /**
     * Adds a given value to all components of given vector.
     * @param vector: vector to perform sum
     * @param value: value to add to all components of vector
     * @return sum result
     */
    Vector add(Vector vector, double value);

    /**
     * Adds two given vectors
     * @param leftVector: LHS of sum
     * @param rightVector: RHS of sum
     * @return sum result
     */
    Vector add(Vector leftVector, Vector rightVector);

    /**
     * Subtracts a given value from all components of given vector.
     * @param vector: vector to perform subtraction
     * @param value: value to subtract to all components of vector
     * @return subtraction result
     */
    Vector subtract(Vector vector, double value);

    /**
     * Subtracts two given vectors
     * @param leftVector: LHS of subtraction
     * @param rightVector: RHS of subtraction
     * @return subtraction result
     */
    Vector subtract(Vector leftVector, Vector rightVector);

    /**
     * Multiplies a given value to all components of given vector.
     * @param vector: vector to perform multiplication
     * @param value: value to multiply all components of vector with
     * @return multiplication result
     */
    Vector multiply(Vector vector, double value);

    /**
     * Element-wise multiplication two given vectors
     * @param leftVector: LHS of multiplication
     * @param rightVector: RHS of multiplication
     * @return multiplication result
     */
    Vector multiply(Vector leftVector, Vector rightVector);

    /**
     * Divides a given value to all components of given vector.
     * @param vector: vector to perform division
     * @param value: value to divide all components of vector with
     * @return division result
     */
    Vector divide(Vector vector, double value);

    /**
     * Element-wise division two given vectors
     * @param leftVector: Numerator of division
     * @param rightVector: Denominator of division
     * @return division result
     */
    Vector divide(Vector leftVector, Vector rightVector);

    /**
     * Computes the dot product between two vectors.
     * @param leftVector LHS of scalar product
     * @param rightVector RHS of scalar product
     * @return Dot product result
     */
    double dot(Vector leftVector, Vector rightVector);

    /**
     * Computes a vector's norm
     * @param vector: vector to compute norm
     * @return vector's norm
     */
    double norm(Vector vector);


    boolean equals(Vector vector, double val);

    /**
     * Checks whether the corresponding components of the two vectors are equal, up to a tolerance of 1e-10.
     * @param leftVector: LHS of comparison
     * @param rightVector: RHS of comparison
     * @return is equal or not
     */
    boolean equals(Vector leftVector, Vector rightVector);

    /**
     * Checks vector's components are all strictly smaller than the given value.
     * @param vector: vector to compare with value
     * @param val: value to compare with components
     * @return are all components strictly smaller than given value or not
     */
    boolean isSmallerThan(Vector vector, double val);

    /**
     * Checks whether the corresponding components of the two vectors are equal, up to a tolerance of 1e-10.
     * @param leftVector: LHS of comparison
     * @param rightVector: RHS of comparison
     * @return is equal or not
     */
    boolean isSmallerThan(Vector leftVector, Vector rightVector);

    /**
     * Checks vector's components are all smaller than or equal to the given value.
     * @param vector: vector to compare with value
     * @param val: value to compare with components
     * @return are all components smaller or equal than given value or not
     */
    boolean isSmallerOrEqualThan(Vector vector, double val);

    /**
     * Checks whether leftVector is smaller than or equal to rightVector component-wise
     * @param leftVector: LHS of comparison
     * @param rightVector: RHS of comparison
     * @return is equal or not
     */
    boolean isSmallerOrEqualThan(Vector leftVector, Vector rightVector);

    /**
     * Checks vector's components are all strictly larger than the given value.
     * @param vector: vector to compare with value
     * @param val: value to compare with components
     * @return are all components strictly larger than given value or not
     */
    boolean isLargerThan(Vector vector, double val);

    /**
     * Checks whether leftVector is stricly larger than rightVector component-wise
     * @param leftVector: LHS of comparison
     * @param rightVector: RHS of comparison
     * @return is equal or not
     */
    boolean isLargerThan(Vector leftVector, Vector rightVector);

    /**
     * Checks vector's components are all larger than or equal to the given value.
     * @param vector: vector to compare with value
     * @param val: value to compare with components
     * @return are all components strictly smaller than given value or not
     */
    boolean isLargerOrEqualThan(Vector vector, double val);

    /**
     * Checks whether leftVector is larger than or equal to rightVector component-wise
     * @param leftVector: LHS of comparison
     * @param rightVector: RHS of comparison
     * @return is equal or not
     */
    boolean isLargerOrEqualThan(Vector leftVector, Vector rightVector);
}
