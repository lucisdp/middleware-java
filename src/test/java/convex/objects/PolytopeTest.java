package convex.objects;

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
        A = new double[][] {{1,0,0},{0,1,0},{0,0,1}};
        b = new double[] {-1,0,1};
        pol = new Polytope(A,b);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongDimension(){
        b = new double[] {-1,0};
        new Polytope(A,b);
    }

    @Test
    public void testGetMatrix(){
        assertTrue(Arrays.deepEquals(pol.getMatrix().toArray(), A));
    }

    @Test
    public void testGetVector(){
        assertTrue(Arrays.equals(pol.getVector().toArray(), b));
    }

    @Test
    public void testGetVectorPolyhedralCone(){
        pol = new PolyhedralCone(A);
        assertArrayEquals(new double[] {0,0,0}, pol.getVector().toArray(), 1e-10);
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
}