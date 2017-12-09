package convex.objects;

import linalg.IVector;
import linalg.OjalgoVector;


class Ellipsoid extends ConvexBody {
    private IVector center;
    private IVector halfAxisLengths;

    public Ellipsoid(IVector center, IVector halfAxisLengths){
        super(center.getDim());
        this.center = center;
        this.halfAxisLengths = halfAxisLengths;
        checkDim(halfAxisLengths);
        checkLengthsArePositive(halfAxisLengths);
    }

    public Ellipsoid(double[] center, double[] halfAxisLengths){
        this(new OjalgoVector(center), new OjalgoVector(halfAxisLengths));
    }

    public Ellipsoid(IVector center, double radius){
        super(center.getDim());
        this.center = center;

        if (radius <= 0)
            throw new IllegalArgumentException("Radius parameter must be positive.");

        this.halfAxisLengths = new OjalgoVector((int) this.center.getDim(), radius);
    }

    public Ellipsoid(double[] center, double radius){
        this(new OjalgoVector(center), radius);
    }

    public Ellipsoid(int dim){
        this(new OjalgoVector(new double[dim]), 1);
    }

    public IVector getCenter() {
        return center;
    }

    public IVector getHalfAxisLengths() {
        return halfAxisLengths;
    }

    @Override
    public boolean isInside(IVector point) {
        checkDim(point);
        return point.subtract(center).divide(halfAxisLengths).norm() < 1;
    }

    private void checkLengthsArePositive(IVector point){
        if (!point.isLargerThan(0))
            throw new IllegalArgumentException("Half-axis must consist of positive values.");

    }
}

