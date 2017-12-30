package linalg;

import java.util.Arrays;

public interface MatrixStorageFactory {
    MatrixStorageStrategy makeMatrixStorage(double[][] values);

    default MatrixStorageStrategy makeMatrixStorage(int rows, int cols, double fill){
        double[][] values = new double[rows][cols];
        for (int i=0; i< rows; i++)
            Arrays.fill(values[i], fill);
        return makeMatrixStorage(values);
    }

    default MatrixStorageStrategy makeEye(int dim){
        double[][] res = new double[dim][dim];
        for (int i=0; i < dim; i++)
            res[i][i] = 1.0;
        return makeMatrixStorage(res);
    }
}
