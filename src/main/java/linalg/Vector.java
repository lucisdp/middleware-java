package linalg;

import exceptions.EmptyVectorException;
import exceptions.IncompatibleDimensionsException;
import exceptions.NormalizingZeroVectorException;

/**
 * <p>This module implements an euclidean vector. In other words, a vector is a fixed-size collection of real numbers with
 * which we can perform several operations (sum, subtract, multiply, ...).</p>
 *
 * <p>There are several Linear Algebra libraries in Java, but there is not a single better choice for all applications. Given
 * the still uncertain nature of our Middleware, we decided to create a wrapper for the most promising Linear Algebra
 * libraries.</p>
 *
 *
 * @author lucianodp
 *
 * @see Matrix
 */

public abstract class Vector {

    /**
     * FACTORY, as the name implies, is a simple factory class providing a shortcut for Vector creation. It instantiates
     * Vectors based on LinearAlgebraConfig configurations, so both APIs always give the same results. However,
     * we are not able to directly set a new library from here, use LinearAlgebraConfig instead.
     *
     * @see LinearAlgebraConfig
     */
    public static class FACTORY {
        public static Vector make(double[] values){
            return LinearAlgebraConfig.getVectorFactory().make(values);
        }

        public static Vector makeFilled(int dim, double value){
            return LinearAlgebraConfig.getVectorFactory().makeFilled(dim, value);
        }

        public static Vector makeZero(int dim){
            return LinearAlgebraConfig.getVectorFactory().makeZero(dim);
        }
    }

    /**
     * @return Vector's dimension.
     */
    public abstract int getDim();

    protected void checkDim(Vector vector){
        if (this.getDim() != vector.getDim())
            throw new IncompatibleDimensionsException(this.getDim(), vector.getDim());
    }

    /**
     * @param index: index of component to retrieve value
     * @return Value at position 'index'.
     * @throws ArrayIndexOutOfBoundsException if index is negative or larger than vector's size
     */
    public abstract double get(int index);

    /**
     * Sets the component 'index' to a new value.
     * @param index: index to set new value
     * @param newValue: new value to set in position
     * @throws ArrayIndexOutOfBoundsException if index is negative or larger than vector's size
     */
    public abstract void set(int index, double newValue);

    /**
     * @param value: value to add to each component of vecgor
     * @return sum result
     */
    public Vector add(double value){
        Vector result = FACTORY.makeZero(getDim());
        for(int i=0; i < getDim(); i++)
            result.set(i, this.get(i) + value);
        return result;
    }

    /**
     * @param vector: vector to perform element-wise addition.
     * @return sum result
     * @throws IncompatibleDimensionsException if vectors have different dimensions
     */
    public Vector add(Vector vector){
        checkDim(vector);
        Vector result = FACTORY.makeZero(getDim());
        for(int i=0; i < getDim(); i++)
            result.set(i, this.get(i) + vector.get(i));
        return result;
    }

    /**
     * @param value: value to subtract from each component of vector
     * @return subtraction result
     */
    public Vector subtract(double value){
        Vector result = FACTORY.makeZero(getDim());
        for(int i=0; i < getDim(); i++)
            result.set(i, this.get(i) - value);
        return result;
    }

    /**
     * @param vector: vector to perform element-wise subtraction.
     * @return subtraction result
     * @throws IncompatibleDimensionsException if vectors have different dimensions
     */
    public Vector subtract(Vector vector){
        checkDim(vector);
        Vector result = FACTORY.makeZero(getDim());
        for(int i=0; i < getDim(); i++)
            result.set(i, this.get(i) - vector.get(i));
        return result;
    }

    /**
     * @param value: value to multiply all components of vector with
     * @return multiplication result
     */
    public Vector multiply(double value){
        Vector result = FACTORY.makeZero(getDim());
        for(int i=0; i < getDim(); i++)
            result.set(i, this.get(i) * value);
        return result;
    }

    /**
     * @param vector: vector to perform element-wise multiplication.
     * @return multiplication result
     * @throws IncompatibleDimensionsException if vectors have different sizes
     */
    public Vector multiply(Vector vector){
        checkDim(vector);
        Vector result = FACTORY.makeZero(getDim());
        for(int i=0; i < getDim(); i++)
            result.set(i, this.get(i) * vector.get(i));
        return result;
    }

    /**
     * @param value: value to divide all components of vector with
     * @return division result
     */
    public Vector divide(double value){
        Vector result = FACTORY.makeZero(getDim());
        for(int i=0; i < getDim(); i++)
            result.set(i, this.get(i) / value);
        return result;
    }

    /**
     * @param vector: vector to perform element-wise division.
     * @return division result
     * @throws IncompatibleDimensionsException if vectors have different sizes
     */
    public Vector divide(Vector vector){
        checkDim(vector);
        Vector result = FACTORY.makeZero(getDim());
        for(int i=0; i < getDim(); i++)
            result.set(i, this.get(i) / vector.get(i));
        return result;
    }

    /**
     * @param vector to perform scalar product
     * @return Dot product between the two vectors.
     * @throws IncompatibleDimensionsException if vectors have different sizes
     */
    public double dot(Vector vector){
        checkDim(vector);

        int sum = 0;
        for(int i=0; i < getDim(); i++)
            sum += get(i) * vector.get(i);
        return sum;
    }

    /**
     * @return scalar product of the vector with itself.
     */
    public double sqNorm(){
        return this.dot(this);
    }

    /**
     * @return Vector's norm
     */
    public double norm(){
        return Math.sqrt(sqNorm());
    }

    /**
     * @return Unit vector parallel to this
     * @throws NormalizingZeroVectorException if vector is zero
     */
    public Vector normalize() {
        if(this.equals(0))
            throw new NormalizingZeroVectorException();
        return this.divide(norm());
    }

    /**
     * @return a copy of the vector's data in array format.
     */
    public double[] asArray(){
        double[] copy = new double[getDim()];
        for(int i=0; i < getDim(); i++)
            copy[i] = get(i);
        return copy;
    }

    /**
     * @param value: number to compare components
     * @return True if all components equal to given value or not
     */
    public boolean equals(double value) {
        for (int i=0; i < getDim(); i++){
            if (Math.abs(this.get(i) - value) > 1e-10)
                return false;
        }
        return true;
    }

    /**
     * @param vector: vector to compare with
     * @return True if all corresponding components of the two vectors are equal, up to a tolerance of 1e-10.
     * @throws IncompatibleDimensionsException if vectors have incompatible sizes
     */
    public boolean equals(Vector vector){
        checkDim(vector);
        for (int i=0; i < getDim(); i++){
            if (Math.abs(this.get(i) - vector.get(i)) > 1e-10)
                return false;
        }
        return true;
    }

    /**
     * @param value: value to compare with components
     * @return True if all components are strictly smaller than given value
     */
    public boolean isSmallerThan(double value){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) >= value)
                return false;
        }
        return true;
    }

    /**
     * @param vector: vector to compare with
     * @return Whether this.get(i) &lt; vector.get(i), for all i.
     * @throws IncompatibleDimensionsException if vectors have incompatible sizes
     */
    public boolean isSmallerThan(Vector vector){
        checkDim(vector);
        for (int i=0; i < getDim(); i++){
            if (this.get(i) >= vector.get(i))
                return false;
        }
        return true;
    }

    /**
     * @param value: value to compare with components
     * @return True if all components are smaller than given value
     */
    public boolean isSmallerOrEqualThan(double value){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) > value)
                return false;
        }
        return true;
    }

    /**
     * @param vector: vector to compare with
     * @return Whether this.get(i) \(\leq\) vector.get(i), for all i.
     * @throws IncompatibleDimensionsException if vectors have incompatible sizes
     */
    public boolean isSmallerOrEqualThan(Vector vector){
        checkDim(vector);
        for (int i=0; i < getDim(); i++){
            if (this.get(i) > vector.get(i))
                return false;
        }
        return true;
    }

    /**
     * @param value: value to compare with components
     * @return True if all components are strictly larger than given value
     */
    public boolean isLargerThan(double value){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) <= value)
                return false;
        }
        return true;
    }

    /**
     * @param vector: vector to compare with
     * @return Whether this.get(i) &gt; vector.get(i), for all i.
     * @throws IncompatibleDimensionsException if vectors have incompatible sizes
     */
    public boolean isLargerThan(Vector vector){
        checkDim(vector);
        for (int i=0; i < getDim(); i++){
            if (this.get(i) <= vector.get(i))
                return false;
        }
        return true;
    }

    /**
     * @param value: value to compare with components
     * @return True if all components larger than given value
     */
    public boolean isLargerOrEqualThan(double value){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) < value)
                return false;
        }
        return true;
    }

    /**
     * @param vector: vector to compare with
     * @return True if this.get(i) \(\geq\) vector.get(i), for all i.
     * @throws IncompatibleDimensionsException if vectors have incompatible sizes
     */
    public boolean isLargerOrEqualThan(Vector vector){
        checkDim(vector);
        for (int i=0; i < getDim(); i++){
            if (this.get(i) < vector.get(i))
                return false;
        }
        return true;
    }

    /**
     * Appends a value to the left of vector. This creates a new copy of the Vector.
     * @param value: value to append
     * @return new vector
     */

    public Vector appendLeft(double value) {
        double[] newVector = new double[getDim()+1];
        newVector[0] = value;
        for(int i=1; i < getDim()+1; i++)
            newVector[i] = this.get(i-1);
        return FACTORY.make(newVector);
    }

    /**
     * @return new vector with 0-th component excluded
     * @throws EmptyVectorException if resulting Vector would be empty
     */
    public Vector dropLeft(){
        if(getDim() == 1)
            throw new EmptyVectorException();
        double[] newVector = new double[getDim()-1];
        for(int i=1; i < getDim(); i++)
            newVector[i-1] = this.get(i);
        return FACTORY.make(newVector);
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
