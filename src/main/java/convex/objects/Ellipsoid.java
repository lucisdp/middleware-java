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
        this(Vector.FACTORY.makeVector(center), Vector.FACTORY.makeVector(halfAxisLengths));
    }

    /**
     * Construct a ball given its center and a radius.
     * @param center: center of ball
     * @param radius: radius of ball (must be positive)
     */
    public Ellipsoid(Vector center, double radius){
        this.center = center;

        if (radius <= 0)
            throw new NegativeLengthException("Radius");

        this.halfAxisLengths = Vector.FACTORY.makeFilled(this.center.getDim(), radius);
    }

    public Ellipsoid(double[] center, double radius){
        this(Vector.FACTORY.makeVector(center), radius);
    }

    /**
     * Constructs an unit ball (centered at origin, radius 1)
     * @param dim: dimension of underlying euclidean space
     */
    public Ellipsoid(int dim){ this(Vector.FACTORY.makeZero(dim), 1); }

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
     * If \( C \) is the center and \( L_i \) is the size of the half-axis i, a point \( X \) is inside the Ellipsoid if:
     *
     *            \[ \sum_i \left(\frac{X_i - C_i}{L_i}\right)^2 &lt; 1 \]
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
     * If \(c + t D\) is the line's equation, in order to find the intersection segment we solve the equation:
     *
     *                \[ \sum_i \left(\frac{t D_i + c_i - C_i}{L_i}\right)^2 &lt; 1 \]
     *
     * By rearranging the terms, we arrive at a simple second degree equation for \( t \).
     *
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

        // solve second degree equation
        double a = normalizedDirection.sqNorm();
        double b = normalizedCenter.dot(normalizedDirection);
        double c = normalizedCenter.sqNorm() - 1;

        double delta = b*b - a*c;
        if (delta <= 0)  // only two real solutions determine a segment
            throw new EmptyIntersectionException();

        return new LineSegment(line, (-b - Math.sqrt(delta))/a,(-b + Math.sqrt(delta))/a);
    }
}

