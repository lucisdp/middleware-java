package linalg;

public interface IVector {
    int getDim();

    default void checkDim(IVector vec){
        if (this.getDim() != vec.getDim())
            throw new IllegalArgumentException("Wrong input dimension.");
    }

    double get(int index);
    //double set(int index, double val);
    //IVector getSlice(int start, int stop);
    //IVector setSlice(int start, int stop, double[] values);

    IVector add(IVector vec);
    IVector add(double val);
    IVector subtract(IVector vec);
    IVector subtract(double val);
    IVector multiply(double val);
    IVector divide(IVector vec);
    IVector divide(double val);

    double dot(IVector vec);

    boolean equals(IVector val);
    boolean isSmallerThan(double val);
    boolean isSmallerThan(IVector vec);
    boolean isSmallerOrEqualThan(double val);
    boolean isSmallerOrEqualThan(IVector vec);
    boolean isLargerThan(double val);
    boolean isLargerThan(IVector vec);
    boolean isLargerOrEqualThan(double val);
    boolean isLargerOrEqualThan(IVector vec);

    double norm();

    double[] toArray();
}
