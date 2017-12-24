package convex.objects;

import convex.sampling.Line;
import convex.sampling.LineSegment;
import exceptions.EmptyIntersectionException;
import exceptions.NegativeLengthException;
import linalg.Vector;


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

    public Ellipsoid(Vector center, double radius){
        this.center = center;

        if (radius <= 0)
            throw new NegativeLengthException("Radius");

        this.halfAxisLengths = new Vector((int) this.center.getDim(), radius);
    }

    public Ellipsoid(double[] center, double radius){
        this(new Vector(center), radius);
    }

    public Ellipsoid(int dim){
        this(new Vector(new double[dim]), 1);
    }

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

    @Override
    public boolean isInside(Vector point) {
        checkDim(point);
        return point.subtract(center).divide(halfAxisLengths).norm() < 1;
    }

    private void checkLengthsArePositive(Vector point){
        if (!point.isLargerThan(0))
            throw new NegativeLengthException("Half-axis");

    }

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

