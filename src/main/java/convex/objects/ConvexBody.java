package convex.objects;

import convex.sampling.Line;
import convex.sampling.LineSegment;
import exceptions.IncompatibleDimensionsException;
import linalg.Matrix;
import linalg.Vector;

public interface ConvexBody {
    default void checkDim(Line line) {
        if (line.getDim() != getDim())
            throw new IncompatibleDimensionsException(getDim(), line.getDim());
    }

    default void checkDim(Vector point) {
        if (point.getDim() != getDim())
            throw new IncompatibleDimensionsException(getDim(), point.getDim());
    }

    default public void checkDim(Matrix points) {
        if (points.getNumCols() != getDim())
            throw new IncompatibleDimensionsException(getDim(), points.getNumCols());
    }

    int getDim();

    default boolean isInside(double[] point) {
        return isInside(new Vector(point));
    }

    boolean isInside(Vector point);
    // checks weather point is the interior of convex set (boundary does not count!)

    LineSegment intersect(Line line);
}
