package linalg;

import exceptions.NegativeDimensionException;
import exceptions.IncompatibleDimensionsException;

import java.util.Arrays;

public class Vector {
    private double[] values;
    private int dim;
    private static VectorOperationStrategy opStrategy = null;

    public Vector(double[] values){
        this.values = values;
        this.dim = values.length;
    }

    public Vector(int dim, double fill){
        if (dim <= 0)
            throw new NegativeDimensionException(dim);

        this.dim = dim;
        this.values = new double[dim];
        Arrays.fill(this.values, fill);
    }

    public Vector(int dim){
        this(dim, 0);
    }

    public double[] getValues() {
        return values;
    }

    public int getDim() {
        return dim;
    }

    public static void setVectorOperationStrategy(String strategyName){
        Vector.opStrategy = VectorOperationStrategy.getStrategy(strategyName);
    }

    private void checkDim(Vector vector){
        if (this.getDim() != vector.getDim())
            throw new IncompatibleDimensionsException(this.getDim(), vector.getDim());
    }

    public double get(int index){
        return this.values[index];
    }

    public Vector add(double val){
        return opStrategy.add(this, val);
    }

    public Vector add(Vector vector){
        checkDim(vector);
        return opStrategy.add(this, vector);
    }

    public Vector subtract(double val){
        return opStrategy.subtract(this, val);
    }

    public Vector subtract(Vector vector){
        checkDim(vector);
        return opStrategy.subtract(this, vector);
    }

    public Vector multiply(double val){
        return opStrategy.multiply(this, val);
    }

    public Vector multiply(Vector vector){
        checkDim(vector);
        return opStrategy.multiply(this, vector);
    }

    public Vector divide(double val){
        return opStrategy.divide(this, val);
    }

    public Vector divide(Vector vector){
        checkDim(vector);
        return opStrategy.divide(this, vector);
    }

    public double dot(Vector vector){
        checkDim(vector);
        return opStrategy.dot(this, vector);
    }

    public double sqNorm(){
        return opStrategy.dot(this, this);
    }

    public double norm(){
        return opStrategy.norm(this);
    }
    //public Vector normalize() {return opStrategy.divide(this, norm()); }

    public boolean equals(Vector vector){
        checkDim(vector);
        return opStrategy.equals(this, vector);
    }

    public boolean equals(double val){
        return opStrategy.equals(this, val);
    }

    public boolean isSmallerThan(double val){
        return opStrategy.isSmallerThan(this, val);
    }

    public boolean isSmallerThan(Vector vector){
        checkDim(vector);
        return opStrategy.isSmallerThan(this, vector);
    }

    public boolean isSmallerOrEqualThan(double val){
        return opStrategy.isSmallerOrEqualThan(this, val);
    }

    public boolean isSmallerOrEqualThan(Vector vector){
        checkDim(vector);
        return opStrategy.isSmallerOrEqualThan(this, vector);
    }
    public boolean isLargerThan(double val){
        return opStrategy.isLargerThan(this, val);
    }

    public boolean isLargerThan(Vector vector){
        checkDim(vector);
        return opStrategy.isLargerThan(this, vector);
    }

    public boolean isLargerOrEqualThan(double val){
        return opStrategy.isLargerOrEqualThan(this, val);
    }

    public boolean isLargerOrEqualThan(Vector vector){
        checkDim(vector);
        return opStrategy.isLargerOrEqualThan(this, vector);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        for (int i=0; i < getDim(); i++) {
            builder.append(values[i]);
            builder.append(',');
            builder.append(' ');
        }
        builder.deleteCharAt(builder.length()-1);
        builder.deleteCharAt(builder.length()-1);
        builder.append('}');
        return builder.toString();
    }

}
