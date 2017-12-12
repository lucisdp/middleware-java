package linalg;

import java.util.Arrays;

public class Vector {
    double[] values;
    int dim;

    public Vector(double[] values){
        this.values = values;
        this.dim = values.length;
    }

    public Vector(int dim, double fill){
        this.dim = dim;
        this.values = new double[dim];
        Arrays.fill(this.values, fill);
    }

    public int getDim() {
        return dim;
    }

    void checkDim(Vector vec){
        if (this.getDim() != vec.getDim())
            throw new ArrayIndexOutOfBoundsException("Vectors have incompatible dimensions!");
    }

    public double[] getValues() {
        return values;
    }

    public double get(int index){
        return this.values[index];
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
