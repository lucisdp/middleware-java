package version_space;

import classifier.Label;
import convex.sampling.Line;
import convex.sampling.LineSegment;
import exceptions.IncompatibleDimensionsException;
import exceptions.linalg.NegativeDimensionException;
import linalg.LinearAlgebraConfig;
import linalg.LinearAlgebraLibrary;
import linalg.Vector;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class LinearVersionSpaceTest {
    private int dim, chainLength, sampleSize;
    private LinearVersionSpace versionSpace;

    @BeforeClass
    public static void setLinearAlgebraLibrary(){
        LinearAlgebraConfig.setLibrary(LinearAlgebraLibrary.OJALGO);
    }

    @Before
    public void setUp() throws Exception {
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
        assertEquals(dim+1, versionSpace.getDim());
    }


    @Test
    public void testFindInteriorPointWithEmptyConstrains() throws Exception {
        assertTrue(versionSpace.findInteriorPoint().equals(0));
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testAddConstrainOfWrongDimension() throws Exception {
        versionSpace.addConstrain(Vector.FACTORY.make(new double[] {1,2,3}), Label.NEGATIVE);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testIsInsideWrongDimension() throws Exception {
       versionSpace.isInside(new double[]{0,0,0,0});
    }

    @Test
    public void testIsInsideWithNoConstrains() throws Exception {
        assertTrue(versionSpace.isInside(new double[] {0,0,0}));
        assertFalse(versionSpace.isInside(new double[] {1,0,0}));
        assertFalse(versionSpace.isInside(new double[] {2,0,0}));
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testLineWithWrongDimension() throws Exception {
        Line line = new Line(Vector.FACTORY.makeZero(4), Vector.FACTORY.make(new double[] {1,0,-1,0}));
        versionSpace.intersect(line);
    }

    @Test
    public void testIntersectionWithEmptyConstrains() throws Exception {
        Line line = new Line(Vector.FACTORY.makeZero(3), Vector.FACTORY.make(new double[] {1,0,0}));
        LineSegment segment = versionSpace.intersect(line);
        assertEquals(-1, segment.getLower(), 1e-10);
        assertEquals(1, segment.getUpper(), 1e-10);
    }

    @Test
    public void testFindInteriorPoint() throws Exception {
        versionSpace.addConstrain(Vector.FACTORY.makeFilled(2, 1.0), Label.NEGATIVE);
        Vector interiorPoint = versionSpace.findInteriorPoint();
        assertTrue(versionSpace.isInside(interiorPoint));
    }

    // TODO: add tests with non-empty constrains
}
