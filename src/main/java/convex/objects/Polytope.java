package convex.objects;


import linalg.IMatrix;
import linalg.IVector;
import linalg.OjalgoMatrix;
import linalg.OjalgoVector;

class Polytope extends ConvexBody {
    /*
    * A Polyhedral Cone is defined as the intersection of half-spaces. Mathematically speaking, given a matrix A
    * and a vector b, a Polyhedral Cone is defined as the set of x satisfying Ax <= b.
    * */
    private IMatrix A;
    private IVector b;

    public Polytope(IMatrix A, IVector b){
        super(A.getNumCols());
        if(A.getNumRows() != b.getDim()){
            throw new IllegalArgumentException("A and b have incompatible dimensions.");
        }

        this.A = A;
        this.b = b;
    }

    public Polytope(double[][] A, double[] b){
        this(new OjalgoMatrix(A), new OjalgoVector(b));
    }

    public IMatrix getMatrix(){
        return A;
    }

    public IVector getVector(){
        return b;
    }

    public long numConstrains(){
        return b.getDim();
    }

    @Override
    public boolean isInside(IVector point){
        IVector prod = A.multiply(point);
        return prod.isSmallerThan(b);
    }
}


class PolyhedralCone extends Polytope{
    /*
    * A Polyhedral Cone is the intersection of CENTERED hyperplanes. Thus, we can describe it as a Polytope whose vector
    * b is zero.
    * */

    public PolyhedralCone(IMatrix A){
        super(A, new OjalgoVector(A.getNumRows(), 0));
    }
    public PolyhedralCone(double[][] A){ this(new OjalgoMatrix(A)); }
}