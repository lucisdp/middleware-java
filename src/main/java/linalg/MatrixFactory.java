package linalg;

interface MatrixFactory {
    Matrix makeMatrix(double[][] values);
    Matrix makeFilled(int rows, int cols, double fill);
    Matrix makeZero(int rows, int cols);
    Matrix makeEye(int dim);
}
