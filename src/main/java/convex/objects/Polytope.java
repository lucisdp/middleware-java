package convex.objects;


import exceptions.IncompatibleDimensionsException;
import linalg.Matrix;
import linalg.Vector;


class Polytope extends ConvexBody {
    /*
    * A Polytope is defined as the intersection of half-spaces. Mathematically speaking, given a matrix A
    * and a vector b, it is defined as the set of all points x satisfying Ax <= b.
    * */

    private Matrix A;
    private Vector b;

    public Polytope(Matrix A, Vector b){
        super(A.getCols());
        if(A.getRows() != b.getDim()){
            throw new IncompatibleDimensionsException(A.getRows(), b.getDim());
        }

        this.A = A;
        this.b = b;
    }

    public Polytope(double[][] A, double[] b){
        this(new Matrix(A), new Vector(b));
    }

    public Matrix getMatrix(){
        return A;
    }

    public Vector getVector(){
        return b;
    }

    @Override
    public boolean isInside(Vector point){
        return A.multiply(point).isSmallerThan(b);
    }
}


class PolyhedralCone extends Polytope{
    /*
    * A Polyhedral Cone is the intersection of CENTERED hyperplanes. Thus, we can describe it as a Polytope whose vector
    * b is zero.
    * */

    public PolyhedralCone(Matrix A){
        super(A, new Vector(A.getRows(), 0));
    }
    public PolyhedralCone(double[][] A){ this(new Matrix(A)); }
}