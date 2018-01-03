package convex.objects;

import convex.sampling.Line;
import convex.sampling.LineSegment;
import exceptions.convex.EmptyIntersectionException;
import exceptions.IncompatibleDimensionsException;
import linalg.LinearAlgebraConfig;
import linalg.LinearAlgebraLibrary;
import linalg.Vector;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PolytopeTest{
    private double[][] A;
    private double[] b;
    private Polytope pol;

    @BeforeClass
    public static void setLinearAlgebraLibrary(){
        LinearAlgebraConfig.setLibrary(LinearAlgebraLibrary.OJALGO);
    }

    @Before
    public void setUp() throws Exception {
        A = new double[][] {{-1,0}, {0,-1}, {0,1}};
        b = new double[] {1,0,2};
        pol = new Polytope(A,b);

    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testWrongDimension(){
        b = new double[] {-1,0};
        new Polytope(A,b);
    }

    @Test
    public void testGetMatrix(){
        assertTrue(Arrays.deepEquals(pol.getMatrix().asArray(), A));
    }

    @Test
    public void testGetVector(){
        assertTrue(Arrays.equals(pol.getVector().asArray(), b));
    }

    @Test
    public void testGetDimPolytope(){
        assertEquals(2, pol.getDim());
    }

    @Test
    public void testGetNumConstrains() throws Exception {
        assertEquals(3, pol.getNumConstrains());
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
        double[] point = {-1 + 1e-9, 0 + 1e-9};
        assertTrue(pol.isInside(point));
    }

    @Test
    public void testIsInsideOnExteriorCloseToBoundary(){
        double[] point = {-1 - 1e-9, 0 - 1e-9};
        assertFalse(pol.isInside(point));
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
        Line line = new Line(Vector.FACTORY.make(new double[] {0,1}), Vector.FACTORY.make(new double[] {1,0}));
        LineSegment segment = pol.intersect(line);
        assertEquals(-1, segment.getLower(), 1e-10);
        assertEquals(Double.POSITIVE_INFINITY, segment.getUpper(), 1e-10);
    }

    @Test
    public void testIntersectionWithCenterOnBoundary(){
        Line line = new Line(Vector.FACTORY.make(new double[] {-1,1}), Vector.FACTORY.make(new double[] {1,0}));
        LineSegment segment = pol.intersect(line);
        assertEquals(0, segment.getLower(), 1e-10);
        assertEquals(Double.POSITIVE_INFINITY, segment.getUpper(), 1e-10);
    }

    @Test
    public void testIntersectionWithCenterOnExterior(){
        Line line = new Line(Vector.FACTORY.make(new double[] {-2,1}), Vector.FACTORY.make(new double[] {1,0}));
        LineSegment segment = pol.intersect(line);
        assertEquals(1, segment.getLower(), 1e-10);
        assertEquals(Double.POSITIVE_INFINITY, segment.getUpper(), 1e-10);
    }

    @Test(expected = EmptyIntersectionException.class)
    public void testTangentIntersectionWithCenterOnBoundary(){
        Line line = new Line(Vector.FACTORY.make(new double[] {-1,1}), Vector.FACTORY.make(new double[] {0,1}));
        pol.intersect(line);
    }

    @Test(expected = EmptyIntersectionException.class)
    public void testTangentIntersectionWithCenterNotOnBoundary(){
        Line line = new Line(Vector.FACTORY.make(new double[] {-1,-1}), Vector.FACTORY.make(new double[] {0,1}));
        pol.intersect(line);
    }

    @Test(expected = EmptyIntersectionException.class)
    public void testNoIntersection(){
        Line line = new Line(Vector.FACTORY.make(new double[] {-2,0}), Vector.FACTORY.make(new double[] {1,-1}));
        pol.intersect(line);
    }
}