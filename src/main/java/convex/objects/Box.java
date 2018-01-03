package convex.objects;

import convex.sampling.Line;
import convex.sampling.LineSegment;
import exceptions.EmptyIntersectionException;
import exceptions.IncompatibleBoundsException;
import exceptions.IncompatibleDimensionsException;
import exceptions.NegativeLengthException;
import linalg.Vector;

/**
 * Implementation of a Box convex set. It is defined by two parameters: its lower-most edge point L and its upper-most edge
 * point U.
 *
 * @see ConvexBody
 * @author lucianodp
 */
public class Box implements ConvexBody {
    private Vector low, high;

    public Box(Vector low, Vector high){
        this.low = low;
        this.high = high;
        validateAttributes();
    }

    public Box(double[] low, double[] high){
        this(Vector.FACTORY.make(low), Vector.FACTORY.make(high));
    }


    /**
     * Construcs a Cube, given its center and side length. It is a particular case of an Box.
     * @param center: cube's center
     * @param length: cube's side length (must be positive)
     */
    public Box(Vector center, double length){
        if (length <= 0)
            throw new NegativeLengthException("Side-length");

        low = center.subtract(0.5*length);
        high = center.add(0.5*length);
    }

    public Box(double[] center, double length){
        this(Vector.FACTORY.make(center), length);
    }

    /**
     * Constructs an unit box, which is a cube centered at the origin with side length equal to 2.
     * @param dim: dimension of underlying euclidean space
     */
    public Box(int dim){
        low = Vector.FACTORY.makeFilled(dim, -1.0);
        high = Vector.FACTORY.makeFilled(dim, 1.0);
    }

    private void validateAttributes(){
        checkDim(low);
        checkDim(high);

        if(!low.isSmallerThan(high))
            throw new IncompatibleBoundsException();
    }

    @Override
    public int getDim() {
        return low.getDim();
    }

    public Vector getLow() {
        return low;
    }

    public Vector getHigh() {
        return high;
    }

    /**
     * If L and U are, respectively, the lowermost and uppermost points in this box, a given point X is inside it iff:
     *
     *                    \[ L_i &lt; X_i &lt; U_i, \forall i \]
     *
     * @param point: point in the euclidean space
     * @return boolean
     * @throws IncompatibleDimensionsException if point and box have different dimensions
     */
    @Override
    public boolean isInside(Vector point) {
        return low.isSmallerThan(point) && point.isSmallerThan(high);
    }

    /**
     * If \(c + t D\) is the line's equation, in order to find the intersection segment we solve the inequalities below:
     *
     *                     \[ L_i &lt; c_i + t D_i &lt; U_i, \forall i \]
     *
     * @param line: Line instance
     * @return LineSegment instance
     * @throws IncompatibleDimensionsException if line and box have different dimensions
     * @throws EmptyIntersectionException if line does not intercept box
     */
    @Override
    public LineSegment intersect(Line line) {
        checkDim(line);

        Vector normalizedLow = low.subtract(line.getCenter()).divide(line.getDirection());
        Vector normalizedHigh = high.subtract(line.getCenter()).divide(line.getDirection());

        double lowerBound = Double.NEGATIVE_INFINITY;
        double upperBound = Double.POSITIVE_INFINITY;

        for(int i=0; i < getDim(); i++){
            if(line.getDirection().get(i) > 0){
                lowerBound = Math.max(lowerBound, normalizedLow.get(i));
                upperBound = Math.min(upperBound, normalizedHigh.get(i));
            }

            else if(line.getDirection().get(i) < 0){
                lowerBound = Math.max(lowerBound, normalizedHigh.get(i));
                upperBound = Math.min(upperBound, normalizedLow.get(i));
            }

            // if D_i == 0, check that L_i < C_i < U_i
            else if(low.get(i) >= line.getCenter().get(i) || high.get(i) <= line.getCenter().get(i))
                throw new EmptyIntersectionException();
        }

        if(lowerBound >= upperBound)
            throw new EmptyIntersectionException();

        return new LineSegment(line, lowerBound, upperBound);
    }
}
