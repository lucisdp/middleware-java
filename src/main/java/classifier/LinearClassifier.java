package classifier;

import linalg.Matrix;
import linalg.Vector;

public class LinearClassifier implements Classifier{
    private double bias;
    private Vector weight;

    public LinearClassifier(double bias, Vector weight) {
        this.bias = bias;
        this.weight = weight;
    }

    @Override
    public Label predict(Vector point){
        if(bias + point.dot(weight) >= 0)
            return Label.POSITIVE;
        else
            return Label.NEGATIVE;
    }

    @Override
    public LabelCollection predict(Matrix points){
        Vector threshold = points.multiply(weight).add(bias);

        LabelCollection labels = new LabelCollection();
        for (int i=0; i < threshold.getDim(); i++) {
            if (threshold.get(i) >= 0)
                labels.add(Label.POSITIVE);
            else
                labels.add(Label.NEGATIVE);
        }
        return labels;
    }
}
