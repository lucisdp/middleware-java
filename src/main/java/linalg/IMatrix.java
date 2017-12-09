package linalg;

public interface IMatrix {
    int getNumRows();
    int getNumCols();

    default void checkDim(IMatrix mat){
        if (this.getNumRows() != mat.getNumRows() || this.getNumCols() != mat.getNumCols())
            throw new IllegalArgumentException("Wrong input dimension.");
    }

    default void isMultCompatible(IMatrix mat){
        if (this.getNumCols() != mat.getNumRows())
            throw new IllegalArgumentException("Incompatible dimensions.");
    }

    default void isMultCompatible(IVector vec){
        if (this.getNumCols() != vec.getDim())
            throw new IllegalArgumentException("Incompatible dimensions.");
    }

    double get(int row, int col);
    //double set(int row, int col, double val);
    //IVector getSlice(int rowStart, int rowStop, int colStart, int colStop);
    //IVector setSlice(int rowStart, int rowStop, int colStart, int colStop, double[][] values);

    IMatrix add(IMatrix vec);
    IMatrix add(double val);
    IMatrix subtract(IMatrix vec);
    IMatrix subtract(double val);
    IMatrix multiply(double val);
    IVector multiply(IVector mat);
    IMatrix multiply(IMatrix mat);
    //IMatrix divide(IMatrix vec);
    IMatrix divide(double val);

    double[][] toArray();
}

