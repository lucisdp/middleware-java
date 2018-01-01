package linalg;

/**
 * This module encapsulates a Vector data object in a given third-party library. This interface provides methods for
 * accessing and manipulating its data.
 */
public interface VectorStorage {
    /**
     * Returns element at position 'index'
     * @return element at given position.
     * @throws ArrayIndexOutOfBoundsException if index is negative or larger than the array's dimension
     */
    double get(int index);

    /**
     * Sets value at position 'index' to 'value'.
     * @throws ArrayIndexOutOfBoundsException if index is negative or larger than the array's dimension
     */
    void set(int index, double value);

    /**
     * Get vector size
     * @return vector size
     */
    int getDim();

    /**
     * Returns a copy of the vector in a double[] array
     * @return copy of vector
     */
    double[] asArray();

    /**
     * Gets the underlying vector storage object (RealVector for Apache Commons Math, PrimitiveMatrix for OjAlgo, ...)
     * @return underlying vector storage
     */
    Object getRawStorage();
}
