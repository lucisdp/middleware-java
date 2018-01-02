package convex.objects;


import convex.sampling.Line;
import convex.sampling.LineSegment;
import exceptions.EmptyIntersectionException;
import exceptions.IncompatibleDimensionsException;
import linalg.Matrix;
import linalg.Vector;

/**
 * Implementation of an Polytope convex set. It is determined by a collection of linear inequality constrains, which can
 * be described by two parameters: a matrix A and a vector b. Points are inside the polytope if they satisfy all constrains:
 *
 *                                  \[ Ax &lt; b \]
 *
 * @author lucianodp
 * @see ConvexBody
 */
public class Polytope implements ConvexBody {
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
        this(Matrix.FACTORY.makeMatrix(A), Vector.FACTORY.makeVector(b));
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


    /**
     * Checks whether for a given point x it satisfies:
     *             \[ A x &lt; b \]
     *
     * @param point: point in the euclidean space
     * @return boolean telling whether point is inside polytope
     * @throws IncompatibleDimensionsException if point and Polytope have different dimensions
     */
    @Override
    public boolean isInside(Vector point){
        return A.multiply(point).isSmallerThan(b);
    }

    /**
     * Finds line intersection by solving a set of inequalities:
     *
     *         \[ A (c + t D) &lt; b \]
     *
     * or, more explicitly,
     *
     *         \[ t \langle A_i , D \rangle &lt; b - \langle A_i , c \rangle, \forall i \]
     *
     * @param line: Line instance
     * @return line intersection result
     * @throws IncompatibleDimensionsException if line and Polytope have different dimensions
     * @throws EmptyIntersectionException if line does not intercept Polytope
     */
    @Override
    public LineSegment intersect(Line line) {
        Vector numerator = b.subtract(A.multiply(line.getCenter()));
        Vector denominator = A.multiply(line.getDirection());

        double lowerBound = Double.NEGATIVE_INFINITY;
        double upperBound = Double.POSITIVE_INFINITY;

        for(int i=0; i < getNumConstrains(); i++){
            if(denominator.get(i) > 0)
                upperBound = Math.min(upperBound, numerator.get(i)/denominator.get(i));

            else if(denominator.get(i) < 0)
                lowerBound = Math.max(lowerBound, numerator.get(i)/denominator.get(i));

            // if < A_i , D > == 0, check that
            else if(numerator.get(i) <= 0)
                throw new EmptyIntersectionException();
        }

        if(lowerBound >= upperBound)
            throw new EmptyIntersectionException();

        return new LineSegment(line, lowerBound, upperBound);
    }
}
