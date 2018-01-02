package version_space;

import classifier.Classifier;
import classifier.Label;
import classifier.LinearMajorityVote;
import convex.objects.ConvexBody;
import convex.objects.Ellipsoid;
import convex.sampling.HitAndRun;
import convex.sampling.Line;
import convex.sampling.LineSegment;
import exceptions.LinearProgramOptimizationFailed;
import exceptions.NegativeDimensionException;
import linalg.Matrix;
import linalg.Vector;
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
        this.dim = dim+1;  // bias + weight vector
        this.sampler = new HitAndRun(chainLength, sampleSize);
        this.constrains = new IncrementalPolyhedralCone();
        this.ball = new Ellipsoid(this.dim);
        this.solver = LinearProgramSolver.getLinearProgramSolver(this.dim+1);  // add dummy variable
        setSolverObjectiveFunction();
        setSolverBounds();
    }

    @Override
    public int getDim() {
        return dim;
    }

    @Override
    public Classifier sample() {
        Matrix samples = sampler.uniform(this, findInteriorPoint());
        return new LinearMajorityVote(samples.getColumn(0), samples.sliceColumns(1,samples.getNumCols()));
    }

    @Override
    public void addConstrain(Vector point, Label label) {
        Vector constrain = point.appendLeft(1).multiply(-label.getValue());  // -y_i (1, x_i) * (b, w) <= 0
        checkDim(constrain);
        constrains.addConstrain(constrain);
        solver.addLinearConstrain(constrain.appendLeft(-1), 0);
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

    private Vector findInteriorPoint() {
        if(constrains.isEmpty())
            return Vector.FACTORY.makeZero(dim);

        Vector solution = solver.findMinimizer();

        if(solution.get(0) >= 0)
            throw new LinearProgramOptimizationFailed();

        return solution.dropLeft().normalize().multiply(0.8);
    }

    private void setSolverObjectiveFunction(){
        Vector linProgObjective = Vector.FACTORY.makeZero(solver.getDim());
        linProgObjective.set(0, 1);
        solver.setObjectiveFunction(linProgObjective);
    }

    private void setSolverBounds(){
        solver.setLower(Vector.FACTORY.makeFilled(solver.getDim(), -1));
        solver.setUpper(Vector.FACTORY.makeFilled(solver.getDim(), 1));
    }
}
