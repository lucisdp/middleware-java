package linalg;

import java.util.Arrays;

public interface VectorStorageFactory {
    VectorStorage make(double[] values);

    default VectorStorage makeFilled(int dim, double fill){
        double[] values = new double[dim];
        Arrays.fill(values, fill);
        return make(values);
    }

    default VectorStorage makeZero(int dim){
        double[] values = new double[dim];
        return make(values);
    }
}

