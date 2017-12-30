package linalg;

public interface VectorStorage {
    double get(int index);
    void set(int index, double value);
    int getDim();
    double[] asArray();
    Object getRawStorage();
}
