package linalg;


import java.util.Arrays;

class SimpleVector implements IVector {
    double[] values;

    private SimpleVector(double[] values){
        this.values = values;
    }

    @Override
    public int getDim() {
        return this.values.length;
    }

    @Override
    public double get(int index) {
        return values[index];
    }

    @Override
    public IVector add(double val) {
        double[] res = new double[getDim()];
        for(int i=0; i < getDim(); i++)
            res[i] = this.get(i) + val;
        return makeVector(res);
    }

    @Override
    public IVector add(IVector vec) {
        double[] res = new double[getDim()];
        for(int i=0; i < getDim(); i++)
            res[i] = this.get(i) + vec.get(i);
        return makeVector(res);
    }

    @Override
    public IVector subtract(double val) {
        double[] res = new double[getDim()];
        for(int i=0; i < getDim(); i++)
            res[i] = this.get(i) - val;
        return makeVector(res);
    }

    @Override
    public IVector subtract(IVector vec) {
        double[] res = new double[getDim()];
        for(int i=0; i < getDim(); i++)
            res[i] = this.get(i) - vec.get(i);
        return makeVector(res);
    }

    @Override
    public IVector multiply(double val) {
        double[] res = new double[getDim()];
        for(int i=0; i < getDim(); i++)
            res[i] = this.get(i) * val;
        return makeVector(res);
    }

    @Override
    public IVector divide(double val) {
        double[] res = new double[getDim()];
        for(int i=0; i < getDim(); i++)
            res[i] = this.get(i) / val;
        return makeVector(res);
    }

    @Override
    public IVector divide(IVector vec) {
        double[] res = new double[getDim()];
        for(int i=0; i < getDim(); i++)
            res[i] = this.get(i) / vec.get(i);
        return makeVector(res);
    }

    public double dot(IVector vec){
        double sum = 0.0;
        for (int i=0; i < getDim(); i++)
            sum += this.get(i) * vec.get(i);
        return sum;
    }

    public double norm(){
        double sum = 0.0;
        for (int i=0; i < getDim(); i++)
            sum += this.get(i) * this.get(i);
        return Math.sqrt(sum);
    }

    public double[] toArray(){
        return Arrays.copyOf(values, getDim());
    }

    @Override
    public IVector copy() {
        return makeVector(toArray());
    }

    public IVector makeVector(double[] values) {
        return new SimpleVector(values);
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

