package version_space;

import convex.sampling.Line;
import convex.sampling.LineSegment;
import exceptions.EmptyIntersectionException;
import exceptions.IncompatibleDimensionsException;
import linalg.LinearAlgebraConfig;
import linalg.LinearAlgebraLibrary;
import linalg.Vector;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class IncrementalPolyhedralConeTest{
    private IncrementalPolyhedralCone pol;

    @BeforeClass
    public static void setLinearAlgebraLibrary(){
        LinearAlgebraConfig.setLibrary(LinearAlgebraLibrary.OJALGO);
    }

    @Before
    public void setUp(){
        double[][] A = new double[][] {{-1,-1}, {1,-1}};
        pol = new IncrementalPolyhedralCone(A);
    }

    @Test
    public void testIsEmpty() throws Exception {
        pol = new IncrementalPolyhedralCone();
        assertTrue(pol.isEmpty());
    }

    @Test
    public void testIsNotEmpty() throws Exception {
        pol = new IncrementalPolyhedralCone();
        pol.addConstrain(Vector.FACTORY.make(new double[] {1,1}));
        assertTrue(!pol.isEmpty());
    }

    @Test
    public void testGetDimEmptyPolytope() throws Exception {
        pol = new IncrementalPolyhedralCone();
        assertEquals(0, pol.getDim());
    }

    @Test
    public void testGetNumConstrainsEmptyPolytope() throws Exception {
        pol = new IncrementalPolyhedralCone();
        assertEquals(0, pol.getNumConstrains());
    }

    @Test
    public void testGetNumConstrains() throws Exception {
        assertEquals(2, pol.getNumConstrains());
    }

    @Test
    public void testGetDim() throws Exception {
        assertEquals(2, pol.getDim());
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testAddConstrainWithWrongDim() throws Exception {
        pol.addConstrain(Vector.FACTORY.make(new double[] {1,2,3}));
    }

    @Test
    public void testIsInsideEmptyPolytope() throws Exception {
        pol = new IncrementalPolyhedralCone();
        assertTrue(pol.isInside(new double[] {1,1}));
    }

    @Test
    public void testIsInsideOnInterior(){
        double[] point = {0,1};
        assertTrue(pol.isInside(point));
    }

    @Test
    public void testIsNotInside(){
        double[] point = {-2,1};
        assertFalse(pol.isInside(point));
    }

    @Test
    public void testIsInsideOnBoundary(){
        double[] point = {-1,1};
        assertFalse(pol.isInside(point));
    }

    @Test
    public void testIsInsideOnInteriorCloseToBoundary(){
        double[] point = {-1 + 1e-9, 1 + 1e-9};
        assertTrue(pol.isInside(point));
    }

    @Test
    public void testIsInsideOnExteriorCloseToBoundary(){
        double[] point = {-1 - 1e-9, 0 - 1e-9};
        assertFalse(pol.isInside(point));
    }

    @Test
    public void testIntersectionWithEmptyPolytope() throws Exception {
        pol = new IncrementalPolyhedralCone();
        Line line = Line.sample(Vector.FACTORY.make(new double[] {1,1}));
        LineSegment segment = pol.intersect(line);
        assertEquals(Double.NEGATIVE_INFINITY, segment.getLower(), 1e-10);
        assertEquals(Double.POSITIVE_INFINITY, segment.getUpper(), 1e-10);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testIsInsideWrongDimension(){
        pol.isInside(new double[] {1,2,3,4});
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testWrongLineDimension(){
        Line line = new Line(Vector.FACTORY.make(new double[] {5,2,3}), Vector.FACTORY.make(new double[] {0,1,3}));
        pol.intersect(line);
    }

    @Test
    public void testIntersectionWithCenterOnInterior(){
        Line line = new Line(Vector.FACTORY.make(new double[] {0,1}), Vector.FACTORY.make(new double[] {0,1}));
        LineSegment segment = pol.intersect(line);
        assertEquals(-1, segment.getLower(), 1e-10);
        assertEquals(Double.POSITIVE_INFINITY, segment.getUpper(), 1e-10);
    }

    @Test
    public void testIntersectionWithCenterOnBoundary(){
        Line line = new Line(Vector.FACTORY.make(new double[] {0,0}), Vector.FACTORY.make(new double[] {0,1}));
        LineSegment segment = pol.intersect(line);
        assertEquals(0, segment.getLower(), 1e-10);
        assertEquals(Double.POSITIVE_INFINITY, segment.getUpper(), 1e-10);
    }

    @Test
    public void testIntersectionWithCenterOnExterior(){
        Line line = new Line(Vector.FACTORY.make(new double[] {0,-2}), Vector.FACTORY.make(new double[] {0,1}));
        LineSegment segment = pol.intersect(line);
        assertEquals(2, segment.getLower(), 1e-10);
        assertEquals(Double.POSITIVE_INFINITY, segment.getUpper(), 1e-10);
    }

    @Test(expected = EmptyIntersectionException.class)
    public void testTangentIntersectionWithCenterOnBoundary(){
        Line line = new Line(Vector.FACTORY.make(new double[] {0,0}), Vector.FACTORY.make(new double[] {1,1}));
        pol.intersect(line);
    }

    @Test(expected = EmptyIntersectionException.class)
    public void testTangentIntersectionWithCenterNotOnBoundary(){
        Line line = new Line(Vector.FACTORY.make(new double[] {-1,-1}), Vector.FACTORY.make(new double[] {1,1}));
        pol.intersect(line);
    }

    @Test(expected = EmptyIntersectionException.class)
    public void testNoIntersection(){
        Line line = new Line(Vector.FACTORY.make(new double[] {-2,0}), Vector.FACTORY.make(new double[] {1,0}));
        pol.intersect(line);
    }
}
