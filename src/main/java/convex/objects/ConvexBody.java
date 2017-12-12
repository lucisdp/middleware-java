package convex.objects;

import linalg.Matrix;
import linalg.Vector;

abstract public class ConvexBody {
    private int dim;

    public ConvexBody(int dim){
        if (dim <= 0)
            throw new IllegalArgumentException("Dimension must be body positive value.");
        this.dim = dim;
    }

    public void checkDim(double[] point){
        if(point.length != dim)
            throw new IllegalArgumentException(
                    String.format("Wrong dimension. Expected %d, obtained %d", getDim(), point.length));
    }

    public void checkDim(Vector point){
        if(point.getDim() != dim)
            throw new IllegalArgumentException(
                    String.format("Wrong dimension. Expected %d, obtained %d", getDim(), point.getDim()));
    }

    public void checkDim(Matrix points){
        if(points.getCols() != dim)
            throw new IllegalArgumentException(
                    String.format("Wrong dimension. Expected %d, obtained %d", getDim(), points.getCols()));
    }

    public int getDim(){
        return dim;
    }

    public boolean isInside(double[] point){
        return isInside(new Vector(point));
    }

    public abstract boolean isInside(Vector point);
    // checks weather body1 point is the interior of convex set (boundary does not count!)

    public static ConvexBody intersect(ConvexBody body1, ConvexBody body2){
        if (body1.getDim() != body2.getDim())
            throw new IllegalArgumentException(String.format(
                    "Incompatible dimensions in intersection: %d and %d", body1.getDim(), body2.getDim()));
        return new ConvexBodyIntersectionResult(body1, body2);
    }

    public ConvexBody intersect(ConvexBody body){
        return intersect(this, body);
    }
}


class ConvexBodyIntersectionResult extends ConvexBody{
    private ConvexBody body1, body2;

    public ConvexBodyIntersectionResult(ConvexBody body1, ConvexBody body2){
        super(body1.getDim());
        this.body1 = body1;
        this.body2 = body2;
    }

    @Override
    public boolean isInside(double[] point) {
        return body1.isInside(point) && body2.isInside(point);
    }

    @Override
    public boolean isInside(Vector point) {
        return false;
    }
}