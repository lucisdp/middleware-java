package linalg;

import java.util.Arrays;

public interface VectorStorageFactory {
    VectorStorageStrategy makeVectorStorage(double[] values);

    default VectorStorageStrategy makeVectorStorage(int dim, double fill){
        double[] values = new double[dim];
        Arrays.fill(values, fill);
        return makeVectorStorage(values);
    }
}

