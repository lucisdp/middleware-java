package convex.sampling;

import exceptions.IncompatibleDimensionsException;
import linalg.Vector;
import utils.Sampler;

/**
 * This class represents a geometrical, unbounded line. It is defined by a center C (any point on the line) and its
 * direction vector D. Mathematically speaking, it is the set of points:
 *
 * \[ \{ C + t D: t \in \mathbb{R} \} \]
 */
public class Line {

    private Vector center, direction;

    /**
     * Creates a new line from a given Center and Direction vectors.
     * @param center: any point on the line
     * @param direction: vector parallel to line. The Direction cannot be the zero vector. It does not have to be normalized.
     * @throws IncompatibleDimensionsException if center and direction do not have the same dimensions
     * @throws IllegalArgumentException if all of direction components are close to 0 (i.e. \(&lt; 10^{-10}\)
     */
    public Line(Vector center, Vector direction) {
        if (center.getDim() != direction.getDim())
            throw new IncompatibleDimensionsException(center.getDim(), direction.getDim());
        if (direction.equals(0.0))
            throw new IllegalArgumentException("Direction cannot be zero vector.");
        this.center = center;
        this.direction = direction;
    }

    public Vector getCenter() {
        return center;
    }

    public Vector getDirection() {
        return direction;
    }

    public int getDim(){
        return center.getDim();
    }

    /**
     * Given the parameter t, it returns the point \( C + t D \)
     * @param position: \( t \) parameter
     * @return  \( C + t D \)
     */
    public Vector getPoint(double position){
        return center.add(direction.multiply(position));
    }

    /**
     * Returns a random line centered at the given point.
     * @param point: center of random line
     * @return random line centered at point
     */
    public static Line sample(Vector point){
        Vector randomDirection = Sampler.sampleGaussian(point.getDim());
        return new Line(point, randomDirection);
    }
}
