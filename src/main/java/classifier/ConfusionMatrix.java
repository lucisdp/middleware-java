package classifier;

import exceptions.IncompatibleDimensionsException;

public class ConfusionMatrix {
    private int 
            truePositives = 0, 
            trueNegatives = 0, 
            falsePositives = 0,
            falseNegatives = 0;

    public ConfusionMatrix(LabelCollection trueLabels, LabelCollection predictedLabels) {
        countMetrics(trueLabels, predictedLabels);
    }

    public int getTruePositives() {
        return truePositives;
    }

    public int getTrueNegatives() {
        return trueNegatives;
    }

    public int getFalsePositives() {
        return falsePositives;
    }

    public int getFalseNegatives() {
        return falseNegatives;
    }

    public double getAccuracy(){
        return (truePositives + trueNegatives)/(truePositives + trueNegatives + falsePositives + falseNegatives);
    }

    public double getPrecision(){
        return (truePositives + falsePositives == 0) ? 0 : truePositives / (truePositives + falsePositives);
    }

    public double getRecall(){
        return (truePositives + falseNegatives == 0) ? 0 : truePositives / (truePositives + falseNegatives);
    }

    public double getFScore(){
        return (2*truePositives + falsePositives + falseNegatives == 0) ? 0 : truePositives / (2*truePositives + falsePositives + falseNegatives);
    }

    private void countMetrics(LabelCollection trueLabels, LabelCollection predictedLabels){
        if(trueLabels.size() != predictedLabels.size())
            throw new IncompatibleDimensionsException(trueLabels.size(), predictedLabels.size());
        
        for(int i=0; i < trueLabels.size(); i++){
            if(trueLabels.get(i) == Label.POSITIVE && predictedLabels.get(i) == Label.POSITIVE)
                truePositives++;
            else if(trueLabels.get(i) == Label.POSITIVE && predictedLabels.get(i) == Label.NEGATIVE)
                falseNegatives++;
            else if(trueLabels.get(i) == Label.NEGATIVE && predictedLabels.get(i) == Label.POSITIVE)
                falsePositives++;
            else
                trueNegatives++;
        }
    }
}
