package version_space;

import convex.sampling.Line;
import convex.sampling.LineSegment;
import exceptions.IncompatibleDimensionsException;
import exceptions.NegativeDimensionException;
import linalg.Matrix;
import linalg.Vector;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class LinearVersionSpaceTest {
    int dim, chainLength, sampleSize;
    LinearVersionSpace versionSpace;

    @Before
    public void setUp() throws Exception {
        Vector.setVectorOperationStrategy("simple");
        Matrix.setMatrixOperationStrategy("simple");
        dim = 2;
        chainLength = 64;
        sampleSize = 8;
        versionSpace = new LinearVersionSpace(dim, chainLength, sampleSize);
    }

    @Test(expected = NegativeDimensionException.class)
    public void testNegativeDimensionException() throws Exception {
        new LinearVersionSpace(-dim, chainLength, sampleSize);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeChainLengthException() throws Exception {
        new LinearVersionSpace(dim, -chainLength, sampleSize);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeSampleSizeException() throws Exception {
        new LinearVersionSpace(dim, chainLength, -sampleSize);
    }

    @Test
    public void testGetDim() throws Exception {
        assertEquals(dim, versionSpace.getDim());
    }

    @Test
    public void testSampleDimensions() throws Exception {
        Matrix sample = versionSpace.sample();
        assertEquals(sampleSize, sample.getNumRows());
        assertEquals(dim, sample.getNumCols());
    }

    @Test
    public void testFindInteriorPointWithEmptyConstrains() throws Exception {
        assertTrue(versionSpace.findInteriorPoint().equals(0));
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testAddConstrainOfWrongDimension() throws Exception {
        versionSpace.addConstrain(new Vector(new double[] {1,2,3}), -1);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testIsInsideWrongDimension() throws Exception {
       versionSpace.isInside(new double[]{0,0,0});
    }

    @Test
    public void testIsInsideWithNoConstrains() throws Exception {
        assertTrue(versionSpace.isInside(new double[] {0,0}));
        assertFalse(versionSpace.isInside(new double[] {1,0}));
        assertFalse(versionSpace.isInside(new double[] {2,0}));
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testLineWithWrongDimension() throws Exception {
        Line line = new Line(new Vector(new double[] {0,0,0}), new Vector(new double[] {1,0,-1}));
        versionSpace.intersect(line);
    }

    @Test
    public void testIntersectionWithEmptyConstrains() throws Exception {
        Line line = new Line(new Vector(new double[] {0,0}), new Vector(new double[] {1,0}));
        LineSegment segment = versionSpace.intersect(line);
        assertEquals(-1, segment.getLower(), 1e-10);
        assertEquals(1, segment.getUpper(), 1e-10);
    }

    // TODO: add tests with non-empty constrains
}
