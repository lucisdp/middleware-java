package convex.objects;

import convex.sampling.Line;
import convex.sampling.LineSegment;
import exceptions.IncompatibleDimensionsException;
import exceptions.NegativeDimensionException;
import linalg.Matrix;
import linalg.Vector;

/**
 * This interface defines a convex set in Euclidean space. We assume implementing classes to provide two pieces of
 * functionality: an isInside method, telling weather a point is inside or outside the convex body; and a intersect
 * method, returning the LineSegment formed by the intersection between the ConvexBody and a Line.
 *
 * There are no checks guaranteeing whether a given implementing class defines a true convex set, we assume it is the
 * responsibility of the developer to ensure his implementations conform to this assumption.
 *
 * @author lucianodp
* */

public interface ConvexBody {
    default void checkDim(int dim){
        if (dim <= 0)
            throw new NegativeDimensionException(dim);
    }

    default void checkDim(Line line) {
        if (line.getDim() != getDim())
            throw new IncompatibleDimensionsException(getDim(), line.getDim());
    }

    default void checkDim(Vector point) {
        if (point.getDim() != getDim())
            throw new IncompatibleDimensionsException(getDim(), point.getDim());
    }

    default void checkDim(Matrix points) {
        if (points.getNumCols() != getDim())
            throw new IncompatibleDimensionsException(getDim(), points.getNumCols());
    }

    /**
     * Returns the underlying euclidean space dimension.
     * @return dimension (positive integer)
     */
    int getDim();

    /**
     * Checks weather a point is inside this convex set or not. Note that we consider boundary points as EXTERIOR to
     * the body (so any boundary point should return false).
     *
     * Any implementing class should call checkDim() to ensure the point and this body are in the same underlying euclidean space.
     *
     * @param point: point in the euclidean space
     * @return boolean telling whether point is inside this body
     */
    boolean isInside(Vector point);
    default boolean isInside(double[] point) {
        return isInside(new Vector(point));
    }


    /**
     * Computes the intersection of this set with a Line, returning the corresponding line segment. If the line does not
     * intercept this body, it throws an EmptyIntersectionException.
     *
     * Any implementing class should call checkDim() to ensure the line and this body are in the same underlying euclidean space.
     *
     * @param line: Line instance
     * @return Intersection result
     * @see Line
     * @see LineSegment
     */
    LineSegment intersect(Line line);
}
