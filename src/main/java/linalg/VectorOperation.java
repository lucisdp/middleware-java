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
public interface VectorOperation {
    /**
     * Adds a value to all components of vector.
     * @param vector: vector to perform sum
     * @param value: value to add to all components of vector
     * @return sum result
     */
    VectorStorage add(VectorStorage vector, double value);

    /**
     * Adds two  vectors
     * @param leftVector: LHS of sum
     * @param rightVector: RHS of sum
     * @return sum result
     */
    VectorStorage add(VectorStorage leftVector, VectorStorage rightVector);

    /**
     * Subtracts a  value from all components of  vector.
     * @param vector: vector to perform subtraction
     * @param value: value to subtract to all components of vector
     * @return subtraction result
     */
    VectorStorage subtract(VectorStorage vector, double value);

    /**
     * Subtracts two  vectors
     * @param leftVector: LHS of subtraction
     * @param rightVector: RHS of subtraction
     * @return subtraction result
     */
    VectorStorage subtract(VectorStorage leftVector, VectorStorage rightVector);

    /**
     * Multiplies a  value to all components of  vector.
     * @param vector: vector to perform multiplication
     * @param value: value to multiply all components of vector with
     * @return multiplication result
     */
    VectorStorage multiply(VectorStorage vector, double value);

    /**
     * Element-wise multiplication two  vectors
     * @param leftVector: LHS of multiplication
     * @param rightVector: RHS of multiplication
     * @return multiplication result
     */
    VectorStorage multiply(VectorStorage leftVector, VectorStorage rightVector);

    /**
     * Divides a  value to all components of  vector.
     * @param vector: vector to perform division
     * @param value: value to divide all components of vector with
     * @return division result
     */
    VectorStorage divide(VectorStorage vector, double value);

    /**
     * Element-wise division of two  vectors
     * @param leftVector: Numerator of division
     * @param rightVector: Denominator of division
     * @return division result
     */
    VectorStorage divide(VectorStorage leftVector, VectorStorage rightVector);

    /**
     * Computes the dot product between two vectors.
     * @param leftVector LHS of scalar product
     * @param rightVector RHS of scalar product
     * @return Dot product result
     */
    double dot(VectorStorage leftVector, VectorStorage rightVector);

    /**
     * Computes a vector's norm
     * @param vector: vector to compute norm
     * @return vector's norm
     */
    double norm(VectorStorage vector);


    boolean equals(VectorStorage vector, double val);

    /**
     * Checks whether the corresponding components of the two vectors are equal, up to a tolerance of 1e-10.
     * @param leftVector: LHS of comparison
     * @param rightVector: RHS of comparison
     * @return is equal or not
     */
    boolean equals(VectorStorage leftVector, VectorStorage rightVector);

    /**
     * Checks that vector's components are all strictly smaller than value.
     * @param vector: vector to compare with value
     * @param val: value to compare with components
     * @return are all components strictly smaller than value or not
     */
    boolean isSmallerThan(VectorStorage vector, double val);

    /**
     * Checks whether the corresponding components of the two vectors are equal, up to a tolerance of 1e-10.
     * @param leftVector: LHS of comparison
     * @param rightVector: RHS of comparison
     * @return is equal or not
     */
    boolean isSmallerThan(VectorStorage leftVector, VectorStorage rightVector);

    /**
     * Checks vector's components are all smaller than or equal to the  value.
     * @param vector: vector to compare with value
     * @param val: value to compare with components
     * @return are all components smaller or equal than value or not
     */
    boolean isSmallerOrEqualThan(VectorStorage vector, double val);

    /**
     * Checks whether leftVector is smaller than or equal to rightVector component-wise
     * @param leftVector: LHS of comparison
     * @param rightVector: RHS of comparison
     * @return is equal or not
     */
    boolean isSmallerOrEqualThan(VectorStorage leftVector, VectorStorage rightVector);

    /**
     * Checks vector's components are all strictly larger than value.
     * @param vector: vector to compare with value
     * @param val: value to compare with components
     * @return are all components strictly larger than value or not
     */
    boolean isLargerThan(VectorStorage vector, double val);

    /**
     * Checks whether leftVector is stricly larger than rightVector component-wise
     * @param leftVector: LHS of comparison
     * @param rightVector: RHS of comparison
     * @return is equal or not
     */
    boolean isLargerThan(VectorStorage leftVector, VectorStorage rightVector);

    /**
     * Checks vector's components are all larger than or equal to value.
     * @param vector: vector to compare with value
     * @param val: value to compare with components
     * @return are all components strictly smaller than value or not
     */
    boolean isLargerOrEqualThan(VectorStorage vector, double val);

    /**
     * Checks whether leftVector is larger than or equal to rightVector component-wise
     * @param leftVector: LHS of comparison
     * @param rightVector: RHS of comparison
     * @return is equal or not
     */
    boolean isLargerOrEqualThan(VectorStorage leftVector, VectorStorage rightVector);
}
