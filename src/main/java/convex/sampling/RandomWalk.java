package convex.sampling;

import convex.objects.ConvexBody;
import linalg.Matrix;
import linalg.Vector;

abstract class RandomWalk{
    private int chainLength, sampleSize;

    public RandomWalk(int chainLength, int sampleSize){
        setChainLength(chainLength);
        setSampleSize(sampleSize);
    }

    public int getChainLength() {
        return chainLength;
    }

    public int getSampleSize() {
        return sampleSize;
    }

    public void setChainLength(int chainLength) {
        if (chainLength <= 0)
            throw new IllegalArgumentException("Chain length must be a positive integer");
        this.chainLength = chainLength;
    }

    public void setSampleSize(int sampleSize) {
        if (sampleSize <= 0)
            throw new IllegalArgumentException("Sample size must be a positive integer");
        this.sampleSize = sampleSize;
    }

    public Matrix sampleChain(ConvexBody convexBody, Vector initialPoint){
        checkInitialPoint(convexBody, initialPoint);
        Vector sample = initialPoint;

        double[][] chain = new double[chainLength+1][(int) initialPoint.getDim()];
        chain[0] = initialPoint.getValues();

        for (int i=1; i <= chainLength; i++) {
            sample = step(convexBody, sample);
            chain[i] = sample.getValues();
        }

        return new Matrix(chain);
    }

    public Matrix sampleUniform(ConvexBody convexBody, Vector initialPoint){
        checkInitialPoint(convexBody, initialPoint);

        double[][] sample = new double[sampleSize][(int) initialPoint.getDim()];

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
            throw new IllegalArgumentException("Initial point outside convexbody!");
    }
}

