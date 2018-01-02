package version_space;

import classifier.Classifier;
import classifier.Label;
import classifier.LabelCollection;
import exceptions.IncompatibleDimensionsException;
import linalg.Matrix;
import linalg.Vector;

public interface VersionSpace {
    //TODO: create sample (only a single classifier) and getMajorityVote(int sampleSize) (for sampling a MajorityVote classifiers)
    Classifier sample();

    void addConstrain(Vector point, Label label);

    default void addConstrains(Matrix points, LabelCollection labels){
        if(points.getNumRows() != labels.size())
            throw new IncompatibleDimensionsException(points.getNumRows(), labels.size());

        for(int i=0; i < labels.size(); i++)
            addConstrain(points.getRow(i), labels.get(i));
    }
}
