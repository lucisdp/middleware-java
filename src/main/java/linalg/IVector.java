package linalg;

public interface IVector {
    long getDim();
    void checkDim(IVector vec);

    double get(long index);
    double set(long index, double val);
    Vector getSlice(long start, long stop);
    Vector setSlice(long start, long stop, double[] values);

    IVector add(IVector vec);
    IVector add(double val);
    IVector subtract(IVector vec);
    IVector subtract(double val);
    IVector multiply(IVector vec);
    IVector multiply(double val);
    IVector divide(IVector vec);
    IVector divide(double val);

    double dot(IVector vec);

    boolean equals(IVector val);
    boolean isSmallerThan(double val);
    boolean isSmallerThan(IVector val);
    boolean isSmallerOrEqualThan(double val);
    boolean isSmallerOrEqualThan(IVector val);
    boolean isLargerThan(double val);
    boolean isLargerThan(IVector val);
    boolean isLargerOrEqualThan(double val);
    boolean isLargerOrEqualThan(IVector val);

    double norm();
}

