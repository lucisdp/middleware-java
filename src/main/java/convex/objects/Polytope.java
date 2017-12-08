package convex.objects;


import linalg.Matrix;
import linalg.Vector;

class Polytope extends ConvexBody {
    /*
    * A Polyhedral Cone is defined as the intersection of half-spaces. Mathematically speaking, given a matrix A
    * and a vector b, a Polyhedral Cone is defined as the set of x satisfying Ax <= b.
    * */
    private Matrix A;
    private Vector b;

    public Polytope(Matrix A, Vector b){
        super(A.getNumCols());
        if(A.getNumRows() != b.getDim()){
            throw new IllegalArgumentException("A and b have incompatible dimensions.");
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

    public long numConstrains(){
        return b.getDim();
    }

    @Override
    public boolean isInside(Vector point){
        Vector prod = A.multiply(point);
        return prod.isSmallerThan(b);
    }
}


class PolyhedralCone extends Polytope{
    /*
    * A Polyhedral Cone is the intersection of CENTERED hyperplanes. Thus, we can describe it as a Polytope whose vector
    * b is zero.
    * */

    public PolyhedralCone(Matrix A){
        super(A, new Vector(A.getNumRows(), 0));
    }
    public PolyhedralCone(double[][] A){ this(new Matrix(A)); }
}