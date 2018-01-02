package linalg;

interface VectorFactory {
    Vector makeVector(double[] values);
    Vector makeFilled(int dim, double values);
    Vector makeZero(int dim);
}
