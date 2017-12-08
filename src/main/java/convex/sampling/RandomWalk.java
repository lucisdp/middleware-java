package convex.sampling;

import convex.objects.ConvexBody;
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

    public double[][] sampleChain(ConvexBody convexBody, Vector initialPoint){
        checkInitialPoint(convexBody, initialPoint);

        double[][] chain = new double[chainLength+1][(int) initialPoint.getDim()];
        chain[0] = initialPoint.toArray();

        for (int i=1; i <= chainLength; i++)
            chain[i] = step(convexBody, new Vector(chain[i-1]));

        return chain;
    }

    public double[][] sampleUniform(ConvexBody convexBody, Vector initialPoint){
        checkInitialPoint(convexBody, initialPoint);

        double[][] sample = new double[sampleSize][(int) initialPoint.getDim()];

        for (int i=0; i < sampleSize; i++)
            sample[i] = sampleSinglePoint(convexBody, initialPoint);

        return sample;
    }

    private double[] sampleSinglePoint(ConvexBody convexBody, Vector initialPoint){
        double[] sample = new double[(int) initialPoint.getDim()];

        for (int i=0; i < chainLength; i++)
            sample = step(convexBody, new Vector(sample));

        return sample;
    }

    abstract double[] step(ConvexBody convexBody, Vector point);

    private void checkInitialPoint(ConvexBody convexBody, Vector initialPoint){
        convexBody.checkDim(initialPoint);

        if (!convexBody.isInside(initialPoint))
            throw new IllegalArgumentException("Initial point outside convexbody!");
    }
}

