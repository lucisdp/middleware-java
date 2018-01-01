package linalg;

import exceptions.EmptyVectorException;
import exceptions.IncompatibleDimensionsException;
import exceptions.NegativeDimensionException;
import exceptions.NormalizingZeroVectorException;

/**
 * <p>This module implements an euclidean vector. In other words, a vector is a fixed-size collection of real numbers with
 * which we can perform several operations (sum, subtract, multiply, ...).</p>
 *
 * <p>There are several Linear Algebra libraries in Java, but there is not a single better choice for all applications. Given
 * the still uncertain nature of our Middleware, we decided to create a wrapper for the most promising Linear Algebra
 * libraries.</p>
 *
 * <p></p>
 *
 * @author lucianodp
 *
 * @see Matrix
 * @see VectorOperation
 */
public class Vector {
    private VectorStorage storage;
    
    /**
     * Create new vector from double array
     * @param values: array of values to be composing the vector
     */
    public Vector(double[] values){
        this.storage = LinearAlgebraConfiguration.getVectorStorageFactory().make(values);
    }

    /**
     * Construct a vector of specified size, filling it with a given value.
     * @param dim: vector size
     * @param fill: value to fill vector
     */
    public Vector(int dim, double fill){
        if (dim <= 0)
            throw new NegativeDimensionException(dim);
        this.storage = LinearAlgebraConfiguration.getVectorStorageFactory().makeFilled(dim, fill);
    }

    /**
     * Construct a zero vector of given size
     * @param dim: vector size
     */
    public Vector(int dim){
        this(dim, 0);
    }

    Vector(VectorStorage storage){
        this.storage = storage;
    }

    VectorStorage getStorage() {
        return storage;
    }

    /**
     * Returns a copy of the vector's internal storage as a double[] array.
     * @return vector values in array
     */
    public double[] asArray() {
        return storage.asArray();
    }

    /**
     * Vector's dimension
     * @return dim attribute
     */
    public int getDim() {
        return storage.getDim();
    }

    private void checkDim(Vector vector){
        if (this.getDim() != vector.getDim())
            throw new IncompatibleDimensionsException(this.getDim(), vector.getDim());
    }

    /**
     * Get value at position 'index'.
     * @param index: index of component to retrieve value
     * @return value of component 'index'
     * @throws ArrayIndexOutOfBoundsException if index is not valid
     */
    public double get(int index){
        return this.storage.get(index);
    }

    /**
     * Sets the component 'index' to a new value.
     * @param index: index to set new value
     * @param newValue: new value to set in position
     * @throws ArrayIndexOutOfBoundsException if index is not valid
     */
    public void set(int index, double newValue){ this.storage.set(index, newValue); }

    /**
     * Adds a given value to all components of Vector.
     * @param val: value to add to all components of vector
     * @return new vector with the sum result
     */
    public Vector add(double val){
        return new Vector(LinearAlgebraConfiguration.getVectorOperation().add(this.storage, val));
    }

    /**
     * Performs vector addition.
     * @param vector: vector to perform sum
     * @return new vector with the sum result
     * @throws IncompatibleDimensionsException if vectors have different sizes
     */
    public Vector add(Vector vector){
        checkDim(vector);
        return new Vector(LinearAlgebraConfiguration.getVectorOperation().add(this.storage, vector.storage));
    }

    /**
     * Subtracts a given value to all components of Vector.
     * @param val: value to subtract to all components of vector
     * @return new vector with the subtraction result
     */
    public Vector subtract(double val){
        return new Vector(LinearAlgebraConfiguration.getVectorOperation().subtract(this.storage, val));
    }

    /**
     * Performs vector subtraction.
     * @param vector: vector to perform subtraction
     * @return new vector with the subtraction result
     * @throws IncompatibleDimensionsException if vectors have different sizes
     */
    public Vector subtract(Vector vector){
        checkDim(vector);
        return new Vector(LinearAlgebraConfiguration.getVectorOperation().subtract(this.storage, vector.storage));
    }

    /**
     * Multiplies a given value to all components of Vector.
     * @param val: value to multiply all components of vector with
     * @return new vector with the multiplication result
     */
    public Vector multiply(double val){
        return new Vector(LinearAlgebraConfiguration.getVectorOperation().multiply(this.storage, val));
    }

    /**
     * Performs vector element-wise multiplication.
     * @param vector: vector to perform element-wise multiplication.
     * @return new vector with the multiplication result
     * @throws IncompatibleDimensionsException if vectors have different sizes
     */
    public Vector multiply(Vector vector){
        checkDim(vector);
        return new Vector(LinearAlgebraConfiguration.getVectorOperation().multiply(this.storage, vector.storage));
    }

    /**
     * Divides a given value to all components of Vector.
     * @param val: value to divide all components of vector with
     * @return new vector with the division result
     */
    public Vector divide(double val){
        return new Vector(LinearAlgebraConfiguration.getVectorOperation().divide(this.storage, val));
    }

    /**
     * Performs vector element-wise division.
     * @param vector: vector to perform element-wise division.
     * @return new vector with the division result
     * @throws IncompatibleDimensionsException if vectors have different sizes
     */
    public Vector divide(Vector vector){
        checkDim(vector);
        return new Vector(LinearAlgebraConfiguration.getVectorOperation().divide(this.storage, vector.storage));
    }

    /**
     * Computes the dot product between two vectors.
     * @param vector to perform scalar product
     * @return Dot product result
     * @throws IncompatibleDimensionsException if vectors have different sizes
     */
    public double dot(Vector vector){
        checkDim(vector);
        return LinearAlgebraConfiguration.getVectorOperation().dot(this.storage, vector.storage);
    }

    /**
     * Computes the scalar product of the vector with itself.
     * @return squared norm of vector
     */
    public double sqNorm(){
        return LinearAlgebraConfiguration.getVectorOperation().dot(this.storage, this.storage);
    }

    /**
     * Computes the vector's norm
     * @return vector's norm
     */
    public double norm(){
        return LinearAlgebraConfiguration.getVectorOperation().norm(this.storage);
    }

    /**
     * Normalizes a vector (divides it by its norm)
     * @return normalized vector
     * @throws NormalizingZeroVectorException if vector is zero
     */
    public Vector normalize() {
        if(this.equals(0))
            throw new NormalizingZeroVectorException();
        return new Vector(LinearAlgebraConfiguration.getVectorOperation().divide(this.storage, norm()));
    }

    /**
     * Checks whether the corresponding components of the two vectors are equal, up to a tolerance of 1e-10.
     * @param vector: vector to compare with
     * @return is equal or not
     * @throws IncompatibleDimensionsException if vectors have incompatible sizes
     */
    public boolean equals(Vector vector){
        checkDim(vector);
        return LinearAlgebraConfiguration.getVectorOperation().equals(this.storage, vector.storage);
    }

    /**
     * Checks whether all vector's components are equal to a given number, up to a tolerance of 1e-10.
     * @param val: number to compare components
     * @return are all components equal to given value or not
     */
    public boolean equals(double val){
        return LinearAlgebraConfiguration.getVectorOperation().equals(this.storage, val);
    }

    /**
     * Checks if vector's components are all strictly smaller than value.
     * @param val: value to compare with components
     * @return are all components strictly smaller than given value or not
     */
    public boolean isSmallerThan(double val){
        return LinearAlgebraConfiguration.getVectorOperation().isSmallerThan(this.storage, val);
    }

    /**
     * Checks whether this.get(i) &lt; vector.get(i), for all i.
     * @param vector: vector to compare with
     * @return is equal or not
     * @throws IncompatibleDimensionsException if vectors have incompatible sizes
     */
    public boolean isSmallerThan(Vector vector){
        checkDim(vector);
        return LinearAlgebraConfiguration.getVectorOperation().isSmallerThan(this.storage, vector.storage);
    }

    /**
     * Checks if vector's components are all smaller or equal than given value.
     * @param val: value to compare with components
     * @return are all components smaller than given value or not
     */
    public boolean isSmallerOrEqualThan(double val){
        return LinearAlgebraConfiguration.getVectorOperation().isSmallerOrEqualThan(this.storage, val);
    }

    /**
     * Checks whether this.get(i) \(\leq\) vector.get(i), for all i.
     * @param vector: vector to compare with
     * @return is equal or not
     * @throws IncompatibleDimensionsException if vectors have incompatible sizes
     */
    public boolean isSmallerOrEqualThan(Vector vector){
        checkDim(vector);
        return LinearAlgebraConfiguration.getVectorOperation().isSmallerOrEqualThan(this.storage, vector.storage);
    }

    /**
     * Checks if vector's components are all larger than given value.
     * @param val: value to compare with components
     * @return are all components strictly larger than given value or not
     */
    public boolean isLargerThan(double val){
        return LinearAlgebraConfiguration.getVectorOperation().isLargerThan(this.storage, val);
    }

    /**
     * Checks whether this.get(i) &gt; vector.get(i), for all i.
     * @param vector: vector to compare with
     * @return is equal or not
     * @throws IncompatibleDimensionsException if vectors have incompatible sizes
     */
    public boolean isLargerThan(Vector vector){
        checkDim(vector);
        return LinearAlgebraConfiguration.getVectorOperation().isLargerThan(this.storage, vector.storage);
    }

    /**
     * Checks if vector's components are all larger or equal than given value.
     * @param val: value to compare with components
     * @return are all components larger than given value or not
     */
    public boolean isLargerOrEqualThan(double val){
        return LinearAlgebraConfiguration.getVectorOperation().isLargerOrEqualThan(this.storage, val);
    }

    /**
     * Checks whether this.get(i) \(\geq\) vector.get(i), for all i.
     * @param vector: vector to compare with
     * @return is equal or not
     * @throws IncompatibleDimensionsException if vectors have incompatible sizes
     */
    public boolean isLargerOrEqualThan(Vector vector){
        checkDim(vector);
        return LinearAlgebraConfiguration.getVectorOperation().isLargerOrEqualThan(this.storage, vector.storage);
    }

    /**
     * TODO: move to VectorStorage
     * Append a value to the left of vector. This creates a new copy of the Vector.
     * @param value: value to append
     * @return new vector with 'value' appended to the left
     */
    public Vector appendLeft(double value){
        double[] newVector = new double[getDim()+1];
        newVector[0] = value;
        for(int i=1; i < getDim()+1; i++)
            newVector[i] = this.get(i-1);
        return new Vector(newVector);
    }

    /**
     * TODO: move to VectorStorage
     * Drops the 0-th component of vector, creating a new copy of Vector. It throws an exception is operation results
     * would result in empty vector.
     * @return new vector with 0-th component excluded
     * @throws EmptyVectorException if Vector has a single component
     */
    public Vector dropLeft(){
        if(getDim() == 1)
            throw new EmptyVectorException();
        double[] newVector = new double[getDim()-1];
        for(int i=1; i < getDim(); i++)
            newVector[i-1] = this.get(i);
        return new Vector(newVector);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        for (int i=0; i < getDim(); i++) {
            builder.append(this.get(i));
            builder.append(',');
            builder.append(' ');
        }
        builder.deleteCharAt(builder.length()-1);
        builder.deleteCharAt(builder.length()-1);
        builder.append('}');
        return builder.toString();
    }

}
