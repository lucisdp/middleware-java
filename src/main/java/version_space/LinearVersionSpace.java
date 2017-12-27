package version_space;

import convex.objects.ConvexBody;
import convex.objects.Ellipsoid;
import convex.sampling.HitAndRun;
import convex.sampling.Line;
import convex.sampling.LineSegment;
import exceptions.NegativeDimensionException;
import linalg.Matrix;
import linalg.Vector;
import linear_programming.ApacheLinearProgramSolver;
import linear_programming.LinearProgramSolver;


public class LinearVersionSpace implements VersionSpace, ConvexBody {
    private final int dim;
    private final HitAndRun sampler;
    private final IncrementalPolyhedralCone constrains;
    private final Ellipsoid ball;
    private final LinearProgramSolver solver;

    public LinearVersionSpace(int dim, int chainLength, int sampleSize) {
        if(dim <= 0)
            throw new NegativeDimensionException(dim);
        this.dim = dim;
        this.sampler = new HitAndRun(chainLength, sampleSize);
        this.constrains = new IncrementalPolyhedralCone();
        this.ball = new Ellipsoid(dim);
        this.solver = new ApacheLinearProgramSolver(dim);
        setSolverObjectiveFunction();
        setSolverBounds();
    }

    @Override
    public Matrix sample() {
        return sampler.uniform(this, findInteriorPoint());
    }

    @Override
    public void addConstrain(Vector point, double label) {
        checkDim(point);
        Vector constrain = point.multiply(-label);
        constrains.addConstrain(constrain);
        solver.addLinearConstrain(constrain.appendLeft(-1), 0);
    }

    @Override
    public int getDim() {
        return dim;
    }

    @Override
    public boolean isInside(Vector point) {
        checkDim(point);
        return constrains.isInside(point) && ball.isInside(point);
    }

    @Override
    public LineSegment intersect(Line line) {
        checkDim(line);

        LineSegment constrainsSegment = constrains.intersect(line);
        LineSegment ballSegment = ball.intersect(line);

        double lower = Math.max(constrainsSegment.getLower(), ballSegment.getLower());
        double upper = Math.min(constrainsSegment.getUpper(), ballSegment.getUpper());
        return new LineSegment(line, lower, upper);
    }

    @Override
    public Vector findInteriorPoint() {
        if(constrains.isEmpty())
            return new Vector(dim,0);

        Vector solution = solver.findMinimizer();

        if(solution.get(0) >= 0)
            throw new RuntimeException();

        return solution;
    }

    private void setSolverObjectiveFunction(){
        Vector linProgObjective = new Vector(getDim()+1);
        linProgObjective.set(0, 1);
        solver.setObjectiveFunction(linProgObjective);
    }

    private void setSolverBounds(){
        solver.setLower(new Vector(getDim(), -1));
        solver.setUpper(new Vector(getDim(), 1));
    }
}
