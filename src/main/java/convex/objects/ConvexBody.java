package convex.objects;

import convex.sampling.Line;
import convex.sampling.LineSegment;
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

    public void checkDim(Line line){
        if(line.getDim() != dim)
            throw new IncompatibleDimensionsException(getDim(), line.getDim());
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
    // checks weather point is the interior of convex set (boundary does not count!)

    public abstract LineSegment intersect(Line line);
}
