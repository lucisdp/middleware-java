package linalg;

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
    public Matrix multiply(Matrix leftMatrix, Matrix rightMatrix) {
        int leftRows = leftMatrix.getRows();
        int rightCols = rightMatrix.getCols();

        double[][] simpleLeftMatrix = fromMatrix(leftMatrix);
        double[][] simpleRightMatrix = fromMatrix(rightMatrix);
        double[][] res = new double[leftRows][rightCols];

        for(int i=0; i < leftRows; i++)
            for(int j=0; j < rightCols; j++)
                for(int k=0; k < leftMatrix.getCols(); k++)
                    res[i][j] += simpleLeftMatrix[i][k] * simpleRightMatrix[k][j];

        return toMatrix(res);
    }

    @Override
    public Vector multiply(Matrix matrix, Vector vector){
        int rows = matrix.getRows();
        double[][] simpleMatrix = fromMatrix(matrix);
        double[] simpleVector = SimpleVectorOperationStrategy.fromVector(vector);
        double[] res = new double[rows];

        for(int i=0; i < rows; i++)
            for(int k=0; k < matrix.getCols(); k++)
                res[i] += simpleMatrix[i][k] * simpleVector[k];

        return SimpleVectorOperationStrategy.toVector(res);
    }

    @Override
    public Matrix multiplyElement(Matrix leftMatrix, Matrix rightMatrix){
        int rows = leftMatrix.getRows();
        int cols = leftMatrix.getCols();

        double[][] simpleLeftMatrix = fromMatrix(leftMatrix);
        double[][] simpleRightMatrix = fromMatrix(rightMatrix);
        double[][] res = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                res[i][j] += simpleLeftMatrix[i][j] * simpleRightMatrix[i][j];

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
