package version_space;

import convex.objects.Box;
import convex.objects.ConvexBody;
import convex.sampling.HitAndRun;
import convex.sampling.Line;
import convex.sampling.LineSegment;
import exceptions.NegativeDimensionException;
import linalg.Matrix;
import linalg.Vector;


public class LinearVersionSpace implements VersionSpace, ConvexBody {
    private final int dim;
    private final HitAndRun sampler;
    private final IncrementalPolyhedralCone constrains;
    private final Box box;

    public LinearVersionSpace(int dim, int chainLength, int sampleSize) {
        if(dim <= 0)
            throw new NegativeDimensionException(dim);
        this.dim = dim;
        this.sampler = new HitAndRun(chainLength, sampleSize);
        this.constrains = new IncrementalPolyhedralCone();
        this.box = new Box(dim);
    }

    @Override
    public Matrix sample() {
        return sampler.uniform(this, findInteriorPoint());
    }

    @Override
    public Vector findInteriorPoint() {
        if(constrains.isEmpty())
            return new Vector(dim,0);

        // TODO: LinProg solver
        return null;
    }

    @Override
    public void addConstrain(Vector point, double label) {
        checkDim(point);
        Vector constrain = point.multiply(-label);
        constrains.addConstrain(constrain);
    }

    @Override
    public int getDim() {
        return dim;
    }

    @Override
    public boolean isInside(Vector point) {
        checkDim(point);
        return constrains.isInside(point) && box.isInside(point);
    }

    @Override
    public LineSegment intersect(Line line) {
        checkDim(line);

        LineSegment constrainsSegment = constrains.intersect(line);
        LineSegment boxSegment = box.intersect(line);

        double lower = Math.max(constrainsSegment.getLower(), boxSegment.getLower());
        double upper = Math.min(constrainsSegment.getUpper(), boxSegment.getUpper());
        return new LineSegment(line, lower, upper);
    }
}
