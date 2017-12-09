package linalg;

public interface IVector {
    int getDim();

    default void checkDim(IVector vec){
        if (this.getDim() != vec.getDim())
            throw new ArrayIndexOutOfBoundsException("Vectors have incompatible dimensions!");
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

    default boolean equals(IVector vec){
        checkDim(vec);
        for (int i=0; i < vec.getDim(); i++){
            if (Math.abs(this.get(i) - vec.get(i)) > 1e-10)
                return false;
        }
        return true;
    }

    default boolean isSmallerThan(double val){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) >= val)
                return false;
        }
        return true;
    }

    default boolean isSmallerThan(IVector vec){
        checkDim(vec);
        for (int i=0; i < getDim(); i++){
            if (this.get(i) >= vec.get(i))
                return false;
        }
        return true;
    }

    default boolean isSmallerOrEqualThan(double val){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) > val)
                return false;
        }
        return true;
    }

    default boolean isSmallerOrEqualThan(IVector vec){
        checkDim(vec);
        for (int i=0; i < getDim(); i++){
            if (this.get(i) > vec.get(i))
                return false;
        }
        return true;
    }

    default boolean isLargerThan(double val){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) <= val)
                return false;
        }
        return true;
    }

    default boolean isLargerThan(IVector vec){
        checkDim(vec);
        for (int i=0; i < getDim(); i++){
            if (this.get(i) <= vec.get(i))
                return false;
        }
        return true;
    }

    default boolean isLargerOrEqualThan(double val){
        for (int i=0; i < getDim(); i++){
            if (this.get(i) < val)
                return false;
        }
        return true;
    }

    default boolean isLargerOrEqualThan(IVector vec){
        checkDim(vec);
        for (int i=0; i < getDim(); i++){
            if (this.get(i) < vec.get(i))
                return false;
        }
        return true;
    }

    double norm();

    double[] toArray();

    IVector copy();

    IVector makeVector(double[] values);
    default IVector makeVector(IVector vec){
        return makeVector(vec.toArray());
    }
}
