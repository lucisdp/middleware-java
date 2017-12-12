package convex.sampling;

import convex.objects.ConvexBody;
import linalg.Vector;

public class HitAndRun extends RandomWalk{
    public HitAndRun(int chainLength, int sampleSize){
        super(chainLength, sampleSize);
    }

    Vector step(ConvexBody convexBody, Vector point){
        // TODO: add sampleRandomDirection and lineIntersection method to ConvexBody objects
        return new Vector(convexBody.getDim(), 0);
    }
}