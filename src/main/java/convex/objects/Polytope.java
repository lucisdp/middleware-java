package convex.objects;


import convex.sampling.Line;
import convex.sampling.LineSegment;
import exceptions.EmptyIntersectionException;
import exceptions.IncompatibleDimensionsException;
import linalg.Matrix;
import linalg.Vector;


public class Polytope implements ConvexBody {
    /*
    * A Polytope is defined as the intersection of half-spaces. Mathematically speaking, given a matrix A
    * and a vector b, it is defined as the set of all points x satisfying Ax <= b.
    * */

    private Matrix A;
    private Vector b;

    public Polytope(Matrix A, Vector b){
        if(A.getNumRows() != b.getDim()){
            throw new IncompatibleDimensionsException(A.getNumRows(), b.getDim());
        }

        this.A = A;
        this.b = b;
    }

    public Polytope(double[][] A, double[] b){
        this(new Matrix(A), new Vector(b));
    }

    @Override
    public int getDim() {
        return A.getNumCols();
    }

    public Matrix getMatrix(){
        return A;
    }

    public Vector getVector(){
        return b;
    }

    public int getNumConstrains() {return b.getDim(); }

    @Override
    public boolean isInside(Vector point){
        return A.multiply(point).isSmallerThan(b);
    }

    @Override
    public LineSegment intersect(Line line) {
        checkDim(line);

        Vector numerator = b.subtract(A.multiply(line.getCenter()));
        Vector denominator = A.multiply(line.getDirection());

        double lowerBound = Double.NEGATIVE_INFINITY;
        double upperBound = Double.POSITIVE_INFINITY;

        for(int i=0; i < getNumConstrains(); i++){
            if(denominator.get(i) > 0)
                upperBound = Math.min(upperBound, numerator.get(i)/denominator.get(i));

            else if(denominator.get(i) < 0)
                lowerBound = Math.max(lowerBound, numerator.get(i)/denominator.get(i));

            else if(numerator.get(i) <= 0)
                throw new EmptyIntersectionException();
        }

        if(lowerBound >= upperBound)
            throw new EmptyIntersectionException();

        return new LineSegment(line, lowerBound, upperBound);
    }
}
