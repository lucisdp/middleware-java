package linalg;

import java.util.Arrays;

public interface VectorStorageFactory {
    VectorStorage makeVectorStorage(double[] values);

    default VectorStorage makeVectorStorage(int dim, double fill){
        double[] values = new double[dim];
        Arrays.fill(values, fill);
        return makeVectorStorage(values);
    }
}

