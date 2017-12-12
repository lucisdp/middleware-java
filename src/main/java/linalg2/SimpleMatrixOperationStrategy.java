package linalg2;

public class SimpleMatrixOperationStrategy implements MatrixOperationStrategy {
    private double[][] fromMatrix(Matrix matrix){
        return matrix.getValues();
    }

    private Matrix toMatrix(double[][] simpleMatrix){
        return new Matrix(simpleMatrix);
    }

    @Override
    public Matrix add(Matrix matrix, double value){
        int rows = matrix.getRows();
        int cols = matrix.getCols();
        
        double[][] simpleMatrix = fromMatrix(matrix);
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
            res[i][j] = simpleMatrix[i][j] + value;

        return toMatrix(res);
    }

    @Override
    public Matrix add(Matrix leftMatrix, Matrix rightMatrix){
        int rows = leftMatrix.getRows();
        int cols = leftMatrix.getCols();
        
        double[][] simpleLeftMatrix = fromMatrix(leftMatrix);
        double[][] simpleRightMatrix = fromMatrix(rightMatrix);
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                res[i][j] = simpleLeftMatrix[i][j] + simpleRightMatrix[i][j];

        return toMatrix(res);
    }

    @Override
    public Matrix subtract(Matrix matrix, double value){
        int rows = matrix.getRows();
        int cols = matrix.getCols();

        double[][] simpleMatrix = fromMatrix(matrix);
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                res[i][j] = simpleMatrix[i][j] - value;

        return toMatrix(res);
    }

    @Override
    public Matrix subtract(Matrix leftMatrix, Matrix rightMatrix){
        int rows = leftMatrix.getRows();
        int cols = leftMatrix.getCols();

        double[][] simpleLeftMatrix = fromMatrix(leftMatrix);
        double[][] simpleRightMatrix = fromMatrix(rightMatrix);
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                res[i][j] = simpleLeftMatrix[i][j] - simpleRightMatrix[i][j];

        return toMatrix(res);
    }

    @Override
    public Matrix multiply(Matrix matrix, double value){
        int rows = matrix.getRows();
        int cols = matrix.getCols();
        double[][] simpleMatrix = fromMatrix(matrix);
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                res[i][j] = simpleMatrix[i][j] * value;

        return toMatrix(res);
    }

    @Override
    public Matrix multiply(Matrix leftMatrix, Matrix rightMatrix){
        int rows = leftMatrix.getRows();
        int cols = leftMatrix.getCols();

        double[][] simpleLeftMatrix = fromMatrix(leftMatrix);
        double[][] simpleRightMatrix = fromMatrix(rightMatrix);
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                res[i][j] = simpleLeftMatrix[i][j] * simpleRightMatrix[i][j];

        return toMatrix(res);
    }

    @Override
    public Matrix divide(Matrix matrix, double value){
        int rows = matrix.getRows();
        int cols = matrix.getCols();
        double[][] simpleMatrix = fromMatrix(matrix);
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                res[i][j] = simpleMatrix[i][j] / value;

        return toMatrix(res);
    }

    @Override
    public Matrix divide(Matrix leftMatrix, Matrix rightMatrix){
        int rows = leftMatrix.getRows();
        int cols = leftMatrix.getCols();

        double[][] simpleLeftMatrix = fromMatrix(leftMatrix);
        double[][] simpleRightMatrix = fromMatrix(rightMatrix);
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                res[i][j] = simpleLeftMatrix[i][j] / simpleRightMatrix[i][j];

        return toMatrix(res);
    }
}
