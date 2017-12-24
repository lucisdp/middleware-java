package convex.objects;

import convex.sampling.Line;
import convex.sampling.LineSegment;
import exceptions.EmptyIntersectionException;
import exceptions.IncompatibleBoundsException;
import exceptions.NegativeLengthException;
import linalg.Vector;

public class Box implements ConvexBody {
    private Vector low, high;

    public Box(Vector low, Vector high){
        this.low = low;
        this.high = high;
        validateAttributes();
    }

    public Box(double[] low, double[] high){
        this(new Vector(low), new Vector(high));
    }

    public Box(Vector center, double length){
        if (length <= 0)
            throw new NegativeLengthException("Side-length");

        low = center.subtract(0.5*length);
        high = center.add(0.5*length);
    }

    public Box(double[] center, double length){
        this(new Vector(center), length);
    }

    public Box(int dim){
        low = new Vector(dim, -1);
        high = new Vector(dim, 1);
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

    @Override
    public boolean isInside(Vector point) {
        return low.isSmallerThan(point) && point.isSmallerThan(high);
    }

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

            else if(low.get(i) >= line.getCenter().get(i) || high.get(i) <= line.getCenter().get(i) || lowerBound >= upperBound)
                throw new EmptyIntersectionException();
        }

        return new LineSegment(line, lowerBound, upperBound);
    }
}
