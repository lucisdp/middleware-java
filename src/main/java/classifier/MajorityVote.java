package classifier;

import linalg.Vector;

import java.util.List;

public class MajorityVote implements Classifier {
    private List<Classifier> classifiers;

    public MajorityVote(List<Classifier> classifiers){
        if(classifiers.isEmpty())
            throw new IllegalArgumentException("Cannot receive empty classifier list.");

        this.classifiers = classifiers;
    }

    public void addClassifier(Classifier classifier){
        classifiers.add(classifier);
    }

    @Override
    public Label predict(Vector point) {
        int sum = 0;
        for(Classifier clf: classifiers)
            sum += clf.predict(point).getValue();
        return sum >= 0 ? Label.POSITIVE : Label.NEGATIVE;
    }
}
