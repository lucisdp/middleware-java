package classifier;

import linalg.Matrix;
import linalg.Vector;

public interface Classifier {
    Label predict(Vector point);

    default LabelCollection predict(Matrix points){
        LabelCollection labels = new LabelCollection();

        for(int i=0; i < points.getNumRows(); i++)
            labels.add(predict(points.getRow(i)));

        return labels;
    }
}
