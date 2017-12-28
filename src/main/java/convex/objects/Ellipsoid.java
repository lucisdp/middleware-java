package convex.objects;

import convex.sampling.Line;
import convex.sampling.LineSegment;
import exceptions.EmptyIntersectionException;
import exceptions.IncompatibleDimensionsException;
import exceptions.NegativeLengthException;
import linalg.Vector;

/**
 * Implementation of an Ellipsoid convex set. It is defined by two parameters: its geometrical center (any point in an
 * euclidean space) and the lengths of each half-axis (positive numbers).
 *
 * @see ConvexBody
 * @author lucianodp
 */

public class Ellipsoid implements ConvexBody {
    private Vector center;
    private Vector halfAxisLengths;

    public Ellipsoid(Vector center, Vector halfAxisLengths){
        this.center = center;
        this.halfAxisLengths = halfAxisLengths;
        checkDim(halfAxisLengths);
        checkLengthsArePositive(halfAxisLengths);
    }

    public Ellipsoid(double[] center, double[] halfAxisLengths){
        this(new Vector(center), new Vector(halfAxisLengths));
    }

    /**
     * Construct an ball given its center and a radius (which is a particular case of an Ellipsoid).
     * @param center: center of ball
     * @param radius: radius of ball (must be positive)
     */
    public Ellipsoid(Vector center, double radius){
        this.center = center;

        if (radius <= 0)
            throw new NegativeLengthException("Radius");

        this.halfAxisLengths = new Vector((int) this.center.getDim(), radius);
    }

    public Ellipsoid(double[] center, double radius){
        this(new Vector(center), radius);
    }

    /**
     * Constructs an unit ball (centered at origin, radius 1)
     * @param dim: dimension of underlying euclidean space
     */
    public Ellipsoid(int dim){ this(new Vector(dim), 1); }

    @Override
    public int getDim() {
        return this.center.getDim();
    }

    public Vector getCenter() {
        return center;
    }

    public Vector getHalfAxisLengths() {
        return halfAxisLengths;
    }

    /**
     * If C is the center and L_i is the size of the half-axis i, a point X is inside the Ellipsoid if:
     *
     *             \sum_i ((X_i - C_i) / L_i)^2 < 1
     *
     * @param point: point in the euclidean space
     * @return boolean telling whether point is inside this box
     * @throws IncompatibleDimensionsException if point and Polytope have different dimensions
     */
    @Override
    public boolean isInside(Vector point) {
        checkDim(point);
        return point.subtract(center).divide(halfAxisLengths).norm() < 1;
    }

    private void checkLengthsArePositive(Vector point){
        if (!point.isLargerThan(0))
            throw new NegativeLengthException("Half-axis");

    }

    /**
     * Finds line intersection by solving a second degree equation.
     * @param line: Line instance
     * @return Intersection result
     * @throws IncompatibleDimensionsException if line and Ellipsoid have different dimensions
     * @throws EmptyIntersectionException if line does not intercept Ellipsoid
     */
    @Override
    public LineSegment intersect(Line line){
        checkDim(line);
        Vector normalizedCenter = line.getCenter().subtract(this.center).divide(this.halfAxisLengths);
        Vector normalizedDirection = line.getDirection().divide(this.halfAxisLengths);

        double a = normalizedDirection.sqNorm();
        double b = normalizedCenter.dot(normalizedDirection);
        double c = normalizedCenter.sqNorm() - 1;

        double delta = b*b - a*c;
        if (delta <= 0)
            throw new EmptyIntersectionException();

        return new LineSegment(line, (-b - Math.sqrt(delta))/a,(-b + Math.sqrt(delta))/a);
    }
}

