package convex.sampling;

import convex.objects.ConvexBody;
import exceptions.PointOutsideConvexBodyException;
import linalg.Matrix;
import linalg.Vector;

abstract class RandomWalk{
    private int chainLength, sampleSize;

    public RandomWalk(int chainLength, int sampleSize){
        checkParameters(chainLength, sampleSize);
        this.chainLength = chainLength;
        this.sampleSize = sampleSize;
    }

    private void checkParameters(int chainLength, int sampleSize){
        if (chainLength <= 0)
            throw new IllegalArgumentException("Chain length must be a positive integer");

        if (sampleSize <= 0)
            throw new IllegalArgumentException("Sample size must be a positive integer");
    }

    public Matrix chain(ConvexBody convexBody, Vector initialPoint){
        checkInitialPoint(convexBody, initialPoint);
        Vector sample = initialPoint;

        double[][] chain = new double[chainLength+1][initialPoint.getDim()];
        chain[0] = initialPoint.getValues();

        for (int i=1; i <= chainLength; i++) {
            sample = step(convexBody, sample);
            chain[i] = sample.getValues();
        }

        return new Matrix(chain);
    }

    public Matrix uniform(ConvexBody convexBody, Vector initialPoint){
        checkInitialPoint(convexBody, initialPoint);

        double[][] sample = new double[sampleSize][initialPoint.getDim()];

        for (int i=0; i < sampleSize; i++)
            sample[i] = sampleSinglePoint(convexBody, initialPoint).getValues();

        return new Matrix(sample);
    }

    private Vector sampleSinglePoint(ConvexBody convexBody, Vector initialPoint){
        Vector sample = initialPoint;

        for (int i=0; i < chainLength; i++)
            sample = step(convexBody, initialPoint);

        return sample;
    }

    abstract Vector step(ConvexBody convexBody, Vector point);

    private void checkInitialPoint(ConvexBody convexBody, Vector initialPoint){
        convexBody.checkDim(initialPoint);

        if (!convexBody.isInside(initialPoint))
            throw new PointOutsideConvexBodyException();
    }
}

