package convex.objects;

import linalg.Vector;


class Ellipsoid extends ConvexBody {
    private Vector center;
    private Vector halfAxisLengths;

    public Ellipsoid(Vector center, Vector halfAxisLengths){
        super(center.getDim());
        this.center = center;
        this.halfAxisLengths = halfAxisLengths;
        checkDim(halfAxisLengths);
        checkLengthsArePositive(halfAxisLengths);
    }

    public Ellipsoid(double[] center, double[] halfAxisLengths){
        this(new Vector(center), new Vector(halfAxisLengths));
    }

    public Ellipsoid(Vector center, double radius){
        super(center.getDim());
        this.center = center;

        if (radius <= 0)
            throw new IllegalArgumentException("Radius parameter must be positive.");

        this.halfAxisLengths = new Vector((int) this.center.getDim(), radius);
    }

    public Ellipsoid(double[] center, double radius){
        this(new Vector(center), radius);
    }

    public Ellipsoid(int dim){
        this(new Vector(new double[dim]), 1);
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
            throw new IllegalArgumentException("Half-axis must consist of positive values.");

    }
}

