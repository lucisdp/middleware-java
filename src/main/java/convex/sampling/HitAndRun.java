package convex.sampling;

import convex.objects.ConvexBody;
import linalg.IVector;
import linalg.OjalgoVector;

public class HitAndRun extends RandomWalk{
    public HitAndRun(int chainLength, int sampleSize){
        super(chainLength, sampleSize);
    }

    OjalgoVector step(ConvexBody convexBody, IVector point){
        // TODO: add sampleRandomDirection and lineIntersection method to ConvexBody objects
        return new OjalgoVector(convexBody.getDim(), 0);
    }
}