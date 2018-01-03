package linalg;

/**
 * Interface for creating Vector objects. Factories shouldn't (but can) be directly instantiated; you can access its methods 
 * by requesting one factory from LinearAlgebraConfig.getVectorLibrary() or by using Vector.FACTORY.{method of choice}.
 */
public interface VectorFactory {
    /**
     * Create Vector from a double[] array.
     * @param values: double[] vector of values
     * @return Vector
     */
    Vector makeVector(double[] values);

    /**
     * Create vector of given size populated with the same value.
     * @param dim: size of vector
     * @param fill: value used to populate vector
     * @return Vector
     * @throws exceptions.NegativeDimensionException if dim is negative
     */
    Vector makeFilled(int dim, double fill);

    /**
     * Create zero vector of given size.
     * @param dim: size of vector
     * @return Vector
     * @throws exceptions.NegativeDimensionException if dim is negative
     */
    Vector makeZero(int dim);
}
