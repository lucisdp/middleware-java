package convex.sampling;

import exceptions.UnboundedSegmentException;
import linalg.Vector;
import utils.Sampler;


public class LineSegment {
    private double lower;
    private double upper;
    private Line line;

    public LineSegment(Line line, double lower, double upper) {
        if (lower >= upper)
            throw new IllegalArgumentException("Lower bound must be smaller than upper bound!");
        this.line = line;
        this.lower = lower;
        this.upper = upper;
    }

    private boolean isUnbounded(){
        return upper == Double.POSITIVE_INFINITY || lower == Double.NEGATIVE_INFINITY;
    }

    public Vector sample(){
        if (isUnbounded())
            throw new UnboundedSegmentException();
        return line.getPoint(Sampler.sampleUniform(lower, upper));
    }
}
