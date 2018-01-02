package linalg;

import exceptions.EmptyVectorException;
import exceptions.IncompatibleDimensionsException;
import exceptions.LinearAlgebraLibraryNotFound;
import exceptions.NormalizingZeroVectorException;

/**
 * <p>This module implements an euclidean vector. In other words, a vector is a fixed-size collection of real numbers with
 * which we can perform several operations (sum, subtract, multiply, ...).</p>
 *
 * <p>There are several Linear Algebra libraries in Java, but there is not a single better choice for all applications. Given
 * the still uncertain nature of our Middleware, we decided to create a wrapper for the most promising Linear Algebra
 * libraries.</p>
 *
 * @author lucianodp
 *
 * @see Matrix
 */
public abstract class Vector {

    public static class FACTORY{
        static private VectorFactory vectorFactory;

        public static void setFactory(LinearAlgebraLibrary library){
            switch (library){
                case APACHE:
                    vectorFactory = null;
                    break;
                case OJALGO:
                    vectorFactory = new OjalgoVectorFactory();
                    break;
                case SIMPLE:
                    vectorFactory = null;
                    break;
                default:
                    throw new LinearAlgebraLibraryNotFound(library.name());
            }
        }

        public static Vector makeVector(double[] values){
            return vectorFactory.makeVector(values);
        }

        public static Vector makeFilled(int dim, double value){
            return vectorFactory.makeFilled(dim, value);
        }

        public static Vector makeZero(int dim){
            return vectorFactory.makeZero(dim);
        }
    }

    /**
     * Vector's dimension
     * @return dim attribute
     */
    public abstract int getDim();

    protected void checkDim(Vector vector){
        if (this.getDim() != vector.getDim())
            throw new IncompatibleDimensionsException(this.getDim(), vector.getDim());
    }

    /**
     * Get value at position 'index'.
     * @param index: index of component to retrieve value
     * @return value of component 'index'
     * @throws ArrayIndexOutOfBoundsException if index is not valid
     */
    public abstract double get(int index);

    /**
     * Sets the component 'index' to a new value.
     * @param index: index to set new value
     * @param newValue: new value to set in position
     * @throws ArrayIndexOutOfBoundsException if index is not valid
     */
    public abstract void set(int index, double newValue);

    /**
     * Adds a given value to all components of Vector.
     * @param value: value to add to all components of vector
     * @return new vector with the sum result
     */
    public abstract Vector add(double value);

    /**
     * Performs vector addition.
     * @param vector: vector to perform sum
     * @return new vector with the sum result
     * @throws IncompatibleDimensionsException if vectors have different sizes
     */
    public abstract Vector add(Vector vector);

    /**
     * Subtracts a given value to all components of Vector.
     * @param value: value to subtract to all components of vector
     * @return new vector with the subtraction result
     */
    public abstract Vector subtract(double value);

    /**
     * Performs vector subtraction.
     * @param vector: vector to perform subtraction
     * @return new vector with the subtraction result
     * @throws IncompatibleDimensionsException if vectors have different sizes
     */
    public abstract Vector subtract(Vector vector);

    /**
     * Multiplies a given value to all components of Vector.
     * @param value: value to multiply all components of vector with
     * @return new vector with the multiplication result
     */
    public abstract Vector multiply(double value);

    /**
     * Performs vector element-wise multiplication.
     * @param vector: vector to perform element-wise multiplication.
     * @return new vector with the multiplication result
     * @throws IncompatibleDimensionsException if vectors have different sizes
     */
    public abstract Vector multiply(Vector vector);

    /**
     * Divides a given value to all components of Vector.
     * @param value: value to divide all components of vector with
     * @return new vector with the division result
     */
    public abstract Vector divide(double value);

    /**
     * Performs vector element-wise division.
     * @param vector: vector to perform element-wise division.
     * @return new vector with the division result
     * @throws IncompatibleDimensionsException if vectors have different sizes
     */
    public abstract Vector divide(Vector vector);

    /**
     * Computes the dot product between two vectors.
     * @param vector to perform scalar product
     * @return Dot product result
     * @throws IncompatibleDimensionsException if vectors have different sizes
     */
    public abstract double dot(Vector vector);

    /**
     * Computes the scalar product of the vector with itself.
     * @return squared norm of vector
     */
    public double sqNorm(){
        return this.dot(this);
    }

    /**
     * Computes the vector's norm
     * @return vector's norm
     */
    public abstract double norm();

    /**
     * Normalizes a vector (divides it by its norm)
     * @return normalized vector
     * @throws NormalizingZeroVectorException if vector is zero
     */
    public Vector normalize() {
        if(this.equals(0))
            throw new NormalizingZeroVectorException();
        return this.divide(norm());
    }

    /**
     * Returns a copy of the vector's data in array format.
     * @return values attribute
     */
    public abstract double[] asArray();

    /**
     * Checks whether all vector's components are equal to a given number, up to a tolerance of 1e-10.
     * @param value: number to compare components
     * @return are all components equal to given value or not
     */
    public boolean equals(double value) {
        for (int i=0; i < getDim(); i++){
            if (Math.abs(this.get(i) - value) > 1e-10)
                return false;
        }
        return true;
    }

    /**
     * Checks whether the corresponding components of the two vectors are equal, up to a tolerance of 1e-10.
     * @param vector: vector to compare with
     * @return is equal or not
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
     * Checks if vector's components are all strictly smaller than value.
     * @param value: value to compare with components
     * @return are all components strictly smaller than given value or not
     */
    public boolean isSmallerThan(double value){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) >= value)
                return false;
        }
        return true;
    }

    /**
     * Checks whether this.get(i) &lt; vector.get(i), for all i.
     * @param vector: vector to compare with
     * @return is equal or not
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
     * Checks if vector's components are all smaller or equal than given value.
     * @param value: value to compare with components
     * @return are all components smaller than given value or not
     */
    public boolean isSmallerOrEqualThan(double value){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) > value)
                return false;
        }
        return true;
    }

    /**
     * Checks whether this.get(i) \(\leq\) vector.get(i), for all i.
     * @param vector: vector to compare with
     * @return is equal or not
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
     * Checks if vector's components are all larger than given value.
     * @param value: value to compare with components
     * @return are all components strictly larger than given value or not
     */
    public boolean isLargerThan(double value){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) <= value)
                return false;
        }
        return true;
    }

    /**
     * Checks whether this.get(i) &gt; vector.get(i), for all i.
     * @param vector: vector to compare with
     * @return is equal or not
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
     * Checks if vector's components are all larger or equal than given value.
     * @param value: value to compare with components
     * @return are all components larger than given value or not
     */
    public boolean isLargerOrEqualThan(double value){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) < value)
                return false;
        }
        return true;
    }

    /**
     * Checks whether this.get(i) \(\geq\) vector.get(i), for all i.
     * @param vector: vector to compare with
     * @return is equal or not
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
     * Append a value to the left of vector. This creates a new copy of the Vector.
     * @param value: value to append
     * @return new vector with 'value' appended to the left
     */

    public Vector appendLeft(double value) {
        double[] newVector = new double[getDim()+1];
        newVector[0] = value;
        for(int i=1; i < getDim()+1; i++)
            newVector[i] = this.get(i-1);
        return FACTORY.makeVector(newVector);
    }

    /**
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
        return FACTORY.makeVector(newVector);
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
