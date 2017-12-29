package convex.sampling;

import exceptions.IncompatibleBoundsException;
import exceptions.UnboundedSegmentException;
import linalg.Vector;
import utils.Sampler;

/**
 * This module represents a line segment, that is, a subset of a line. It can be bounded or unbounded. More generally,
 * given a line \( C + t D \), a line segment is defined as:
 *
 * \[ \{ C + t D: t_{min} \leq t \leq t_{max} \} \]
 */
public class LineSegment {
    private double lower;
    private double upper;
    private Line line;

    /**
     * Constructs a LineSegment instance from a line, a lower bound and an upper bound.
     * @param line: line the segment is a subset of
     * @param lower: lower bound t_{min}
     * @param upper: upper bound t_{max}
     * @throws IncompatibleBoundsException if lower is larger or equal to upper
     */
    public LineSegment(Line line, double lower, double upper) {
        if (lower >= upper)
            throw new IncompatibleBoundsException();
        this.line = line;
        this.lower = lower;
        this.upper = upper;
    }

    /**
     * @return the value t_{min}. It may be \( -\infty \), but never \( +\infty \)
     */
    public double getLower() {
        return lower;
    }

    /**
     * @return the value t_{max}. It may be \( +\infty \), but never \( -\infty \)
     */
    public double getUpper() {
        return upper;
    }

    /**
     * @return Whether the line segment is unbounded or not (i.e. if any of its extremes is not finite)
     */
    public boolean isUnbounded(){
        return upper == Double.POSITIVE_INFINITY || lower == Double.NEGATIVE_INFINITY;
    }

    /**
     * Returns a random point on the line segment. If segment is unbounded, throws an exception.
     * @return random point on the line segment.
     * @throws UnboundedSegmentException if segment is unbounded
     */
    public Vector sample(){
        if (isUnbounded())
            throw new UnboundedSegmentException();
        return line.getPoint(Sampler.sampleUniform(lower, upper));
    }
}
