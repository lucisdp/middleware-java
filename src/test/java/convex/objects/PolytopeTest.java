package convex.objects;

import convex.sampling.Line;
import convex.sampling.LineSegment;
import exceptions.EmptyIntersectionException;
import exceptions.IncompatibleDimensionsException;
import linalg.Matrix;
import linalg.Vector;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PolytopeTest{
    double[][] A;
    double[] b;
    Polytope pol;

    @Before
    public void setUp() throws Exception
    {
        Vector.setVectorOperationStrategy("ojalgo");
        Matrix.setMatrixOperationStrategy("ojalgo");
        A = new double[][] {{1,0,0},{0,1,0},{0,0,1}};
        b = new double[] {-1,0,1};
        pol = new Polytope(A,b);

    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testWrongDimension(){
        b = new double[] {-1,0};
        new Polytope(A,b);
    }

    @Test
    public void testGetMatrix(){
        assertTrue(Arrays.deepEquals(pol.getMatrix().getValues(), A));
    }

    @Test
    public void testGetVector(){
        assertTrue(Arrays.equals(pol.getVector().getValues(), b));
    }

    @Test
    public void testGetVectorPolyhedralCone(){
        pol = new PolyhedralCone(A);
        assertArrayEquals(new double[] {0,0,0}, pol.getVector().getValues(), 1e-10);
    }

    @Test
    public void testGetDimPolytope(){
        assertEquals(3, pol.getDim());
    }

    @Test
    public void testGetDimPolyhedralCone(){
        pol = new PolyhedralCone(A);
        assertEquals(3, pol.getDim());
    }

    @Test
    public void testIsInsideOnInterior(){
        double[] point = {-2,-1,0};
        assertTrue(pol.isInside(point));
    }

    @Test
    public void testIsInsideOnExterior(){
        double[] point = {0,1,2};
        assertFalse(pol.isInside(point));
    }

    @Test
    public void testIsInsideOnBoundary(){
        double[] point = {-1,-1,-1};
        assertFalse(pol.isInside(point));
    }

    @Test
    public void testIsInsideOnInteriorCloseToBoundary(){
        double[] point = {-1 - 1e-6, 0 - 1e-6, 1 - 1e-6};
        assertTrue(pol.isInside(point));
    }

    @Test
    public void testIsInsideOnExteriorCloseToBoundary(){
        double[] point = {-1 + 1e-6, 0 + 1e-6, 1 + 1e-6};
        assertFalse(pol.isInside(point));
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testIsInsideWrongDimension(){
        pol.isInside(new double[] {1,2});
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testWrongLineDimension(){
        Line line = new Line(new Vector(new double[] {5,2}), new Vector(new double[] {0,1}));
        pol.intersect(line);
    }

    @Test
    public void testIntersectionWithCenterOnInterior(){
        Line line = new Line(new Vector(new double[] {-2,-1,0}), new Vector(new double[] {1,0,0}));
        LineSegment segment = pol.intersect(line);
        assertEquals(Double.NEGATIVE_INFINITY, segment.getLower(), 1e-10);
        assertEquals(1, segment.getUpper(), 1e-10);
    }

    @Test
    public void testIntersectionWithCenterOnBoundary(){
        Line line = new Line(new Vector(new double[] {-1,-1,0}), new Vector(new double[] {1,0,0}));
        LineSegment segment = pol.intersect(line);
        assertEquals(Double.NEGATIVE_INFINITY, segment.getLower(), 1e-10);
        assertEquals(0, segment.getUpper(), 1e-10);
    }

    @Test
    public void testIntersectionWithCenterOnExterior(){
        Line line = new Line(new Vector(new double[] {0,-1,0}), new Vector(new double[] {1,0,0}));
        LineSegment segment = pol.intersect(line);
        assertEquals(Double.NEGATIVE_INFINITY, segment.getLower(), 1e-10);
        assertEquals(-1, segment.getUpper(), 1e-10);
    }

    @Test(expected = EmptyIntersectionException.class)
    public void testTangentIntersectionWithCenterOnBoundary(){
        Line line = new Line(new Vector(new double[] {-1,-1,-1}), new Vector(new double[] {0,0,1}));
        pol.intersect(line);
    }

    @Test(expected = EmptyIntersectionException.class)
    public void testTangentIntersectionWithCenterNotOnBoundary(){
        Line line = new Line(new Vector(new double[] {-1,0,0}), new Vector(new double[] {0,0,1}));
        pol.intersect(line);
    }

    @Test(expected = EmptyIntersectionException.class)
    public void testNoIntersection(){
        Line line = new Line(new Vector(new double[] {1,2,3}), new Vector(new double[] {0,0,1}));
        pol.intersect(line);
    }
}