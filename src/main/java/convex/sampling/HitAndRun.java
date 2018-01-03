package convex.sampling;

import convex.objects.ConvexBody;
import exceptions.convex.EmptyIntersectionException;
import linalg.Vector;

/**
 * This class implements the Hit-and-Run algorithm for sampling uniformly over convex bodies.
 *
 * This algorithm has been shown to possess a few interesting properties:
 * <ul>
 *  <li>Its limiting distribution is the uniform distribution over the convex body</li>
 *  <li>Theoretically, it requires roughly \(\mathcal{O}^*(n^3)\) iterations to converge, but the constants are prohibitively large</li>
 *  <li>In practice, a much smaller number of iterations are required</li>
 * </ul>
 *
 * We further assume that the convex body is bounded and full-dimensional (i.e. it has non-empty interior). If these assumptions
 * are not met, using this algorithm will probably result in error.
 *
 * Reference 1:
 * <ul>
 *  <li>Title:  Hit-and-run mixes fast</li>
 *  <li>Author: Lovasz, Laszlo</li>
 * </ul>
 *
 * Reference 2:
 * <ul>
 *  <li>Title: Hit-and-Run Algorithms for Generating Multivariate Distributions</li>
 *  <li>Authors: Belisle., Claude J.P.; Romeijn, H. Edwin; Smith, Robert L.</li>
 * </ul>
 * @see RandomWalk
 * @author lucianodp
 */
public class HitAndRun extends RandomWalk {
    public HitAndRun(int chainLength, int sampleSize){
        super(chainLength, sampleSize);
    }

    /**
     * Hit-and-Run algorithm core. This algorithm performs 3 actions:
     *
     *   1) It samples a random line L centered at the previous point \(X_t\)
     *   2) Compute the intersection of L with the convex body, which is a line segment
     *   3) Return \(X_{t+1}\) as a random point on this line segment
     *
     * If the convex body is unbounded in any direction or not full-dimensional, you may obtain a EmptyIntersectionException.
     *
     * @param convexBody: convex body from which we are sampling from
     * @param point: current point in the algorithm (\(X_t\) at iteration t+1)
     * @return next point \(X_{t+1}\) based on the Hit-and-Run rule
     * @throws EmptyIntersectionException may be thrown if the convexbody is unbounded
     */
    Vector step(ConvexBody convexBody, Vector point){
        Line randomLine = Line.sample(point);
        LineSegment segment = convexBody.intersect(randomLine);
        return segment.sample();
    }
}