package convex.objects;

import exceptions.IncompatibleDimensionsException;
import exceptions.NegativeDimensionException;
import linalg.Matrix;
import linalg.Vector;

abstract public class ConvexBody {
    private int dim;

    public ConvexBody(int dim){
        if (dim <= 0)
            throw new NegativeDimensionException(dim);
        this.dim = dim;
    }

    public void checkDim(double[] point){
        if(point.length != dim)
            throw new IncompatibleDimensionsException(getDim(), point.length);
    }

    public void checkDim(Vector point){
        if(point.getDim() != dim)
            throw new IncompatibleDimensionsException(getDim(), point.getDim());
    }

    public void checkDim(Matrix points){
        if(points.getCols() != dim)
            throw new IncompatibleDimensionsException(getDim(), points.getCols());
    }

    public int getDim(){
        return dim;
    }

    public boolean isInside(double[] point){
        return isInside(new Vector(point));
    }

    public abstract boolean isInside(Vector point);
    // checks weather body1 point is the interior of convex set (boundary does not count!)
}
