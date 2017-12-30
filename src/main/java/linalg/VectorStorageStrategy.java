package linalg;

public interface VectorStorageStrategy {
    double get(int index);
    void set(int index, double value);
    int getDim();
    double[] asArray();
    Object getRawStorage();
}
