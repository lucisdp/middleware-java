package convex.sampling;

import convex.objects.ConvexBody;
import linalg.Vector;

public class HitAndRun extends RandomWalk{
    public HitAndRun(int chainLength, int sampleSize){
        super(chainLength, sampleSize);
    }

    Vector step(ConvexBody convexBody, Vector point){
        Line randomLine = Line.sample(point);
        // LineSegment segment = intersection(randomLine, convexBody)
        // return segment.sample();
        return new Vector(convexBody.getDim(), 0);
    }
}