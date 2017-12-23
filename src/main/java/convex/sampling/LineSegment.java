package convex.sampling;

import exceptions.IncompatibleBoundsException;
import exceptions.UnboundedSegmentException;
import linalg.Vector;
import utils.Sampler;


public class LineSegment {
    private double lower;
    private double upper;
    private Line line;

    public LineSegment(Line line, double lower, double upper) {
        if (lower >= upper)
            throw new IncompatibleBoundsException();
        this.line = line;
        this.lower = lower;
        this.upper = upper;
    }

    public double getLower() {
        return lower;
    }

    public double getUpper() {
        return upper;
    }

    public boolean isUnbounded(){
        return upper == Double.POSITIVE_INFINITY || lower == Double.NEGATIVE_INFINITY;
    }

    public Vector sample(){
        if (isUnbounded())
            throw new UnboundedSegmentException();
        return line.getPoint(Sampler.sampleUniform(lower, upper));
    }
}
