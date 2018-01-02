package classifier;


import exceptions.IncompatibleDimensionsException;
import linalg.Matrix;
import linalg.Vector;

public class LinearMajorityVote implements Classifier{
    private Vector biases;
    private Matrix weights;

    public LinearMajorityVote(Vector biases, Matrix weights) {
        if(biases.getDim() != weights.getNumRows())
            throw new IncompatibleDimensionsException(biases.getDim(), weights.getNumRows());
        this.biases = biases;
        this.weights = weights;
    }

    @Override
    public Label predict(Vector point) {
        if(point.getDim() != weights.getNumCols())
            throw new IncompatibleDimensionsException(point.getDim(), weights.getNumCols());
        return getLabelFromThresholds(biases.add(weights.multiply(point)));
    }

    @Override
    public LabelCollection predict(Matrix points) {
        if(points.getNumCols() != weights.getNumCols())
            throw new IncompatibleDimensionsException(points.getNumCols(), weights.getNumCols());

        Matrix values = points.multiply(weights.transpose());
        LabelCollection labels = new LabelCollection();

        for(int i=0; i < values.getNumRows(); i++)
            labels.add(getLabelFromThresholds(values.getRow(i).add(biases)));

        return labels;
    }

    private Label getLabelFromThresholds(Vector thresholds){
        int count = 0;
        for(int i=0; i < thresholds.getDim(); i++){
            if(thresholds.get(i) >= 0)
                count += 1;
            else
                count -= 1;
        }
        return count >= 0 ? Label.POSITIVE: Label.NEGATIVE;
    }
}
