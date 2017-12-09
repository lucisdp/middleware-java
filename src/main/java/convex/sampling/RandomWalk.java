package convex.sampling;

import convex.objects.ConvexBody;
import linalg.IMatrix;
import linalg.IVector;
import linalg.OjalgoMatrix;

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

    public IMatrix sampleChain(ConvexBody convexBody, IVector initialPoint){
        checkInitialPoint(convexBody, initialPoint);
        IVector sample = initialPoint;

        double[][] chain = new double[chainLength+1][(int) initialPoint.getDim()];
        chain[0] = initialPoint.toArray();

        for (int i=1; i <= chainLength; i++) {
            sample = step(convexBody, sample);
            chain[i] = sample.toArray();
        }

        return new OjalgoMatrix(chain);
    }

    public IMatrix sampleUniform(ConvexBody convexBody, IVector initialPoint){
        checkInitialPoint(convexBody, initialPoint);

        double[][] sample = new double[sampleSize][(int) initialPoint.getDim()];

        for (int i=0; i < sampleSize; i++)
            sample[i] = sampleSinglePoint(convexBody, initialPoint).toArray();

        return new OjalgoMatrix(sample);
    }

    private IVector sampleSinglePoint(ConvexBody convexBody, IVector initialPoint){
        IVector sample = initialPoint;

        for (int i=0; i < chainLength; i++)
            sample = step(convexBody, initialPoint);

        return sample;
    }

    abstract IVector step(ConvexBody convexBody, IVector point);

    private void checkInitialPoint(ConvexBody convexBody, IVector initialPoint){
        convexBody.checkDim(initialPoint);

        if (!convexBody.isInside(initialPoint))
            throw new IllegalArgumentException("Initial point outside convexbody!");
    }
}

