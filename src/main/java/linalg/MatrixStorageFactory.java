package linalg;

import java.util.Arrays;

public interface MatrixStorageFactory {
    MatrixStorage makeMatrixStorage(double[][] values);

    default MatrixStorage makeMatrixStorage(int rows, int cols, double fill){
        double[][] values = new double[rows][cols];
        for (int i=0; i< rows; i++)
            Arrays.fill(values[i], fill);
        return makeMatrixStorage(values);
    }

    default MatrixStorage makeEye(int dim){
        double[][] res = new double[dim][dim];
        for (int i=0; i < dim; i++)
            res[i][i] = 1.0;
        return makeMatrixStorage(res);
    }
}
