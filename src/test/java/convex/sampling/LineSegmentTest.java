package convex.sampling;

import exceptions.IncompatibleBoundsException;
import exceptions.UnboundedSegmentException;
import linalg.LinearAlgebraLibrary;
import linalg.Matrix;
import linalg.Vector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LineSegmentTest {
    Line line;
    LineSegment segment;

    private void setLibrary(LinearAlgebraLibrary lib){
        Vector.FACTORY.setFactory(lib);
        Matrix.FACTORY.setFactory(lib);
    }

    @Before
    public void setUp() throws Exception {
        setLibrary(LinearAlgebraLibrary.OJALGO);
        line = new Line(Vector.FACTORY.makeVector(new double[] {1,2,3}), Vector.FACTORY.makeVector(new double[] {-1,0,1}));
        segment = new LineSegment(line, -2, 3);
    }

    @Test
    public void testGetLower() throws Exception {
        assertEquals(-2, segment.getLower(), 1e-10);
    }

    @Test
    public void testGetUpper() throws Exception {
        assertEquals(3, segment.getUpper(), 1e-10);
    }

    @Test(expected = IncompatibleBoundsException.class)
    public void testEqualBoundsException() throws Exception {
        new LineSegment(line, 0, 0);
    }

    @Test(expected = IncompatibleBoundsException.class)
    public void testIncompatibleBoundsException() throws Exception {
        new LineSegment(line, 10, 0);
    }

    @Test(expected = UnboundedSegmentException.class)
    public void testUnboundedLowerSegmentException() throws Exception {
        segment = new LineSegment(line, Double.NEGATIVE_INFINITY, 3);
        segment.sample();
    }

    @Test(expected = UnboundedSegmentException.class)
    public void testUnboundedUpperSegmentException() throws Exception {
        segment = new LineSegment(line, -2, Double.POSITIVE_INFINITY);
        segment.sample();
    }

    // TODO: test sampling method
}
