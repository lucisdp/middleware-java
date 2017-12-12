package convex.objects;

import linalg.Vector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class EllipsoidTest {
    Vector center, axisLengths;
    Ellipsoid elp;

    @Before
    public void setUp(){
        Vector.setVectorOperationStrategy("ojalgo");
        center = new Vector(new double[] {1,2,3});
        axisLengths = new Vector(new double[] {4,5,6});
        elp = new Ellipsoid(center, axisLengths);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testWrongDimensions() throws Exception {
        new Ellipsoid(center, new Vector(new double[] {1,2}));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNegativeAxisLength() throws Exception {
        new Ellipsoid(center, new Vector(new double[] {-1,2,3}));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testZeroAxisLength() throws Exception {
        new Ellipsoid(center, new Vector(new double[] {0,2,3}));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNegativeRadius() throws Exception {
        new Ellipsoid(center, -1);
    }

    @Test(expected=NegativeArraySizeException.class)
    public void testNegativeDim() throws Exception {
        new Ellipsoid(-1);
    }

    @Test
    public void testGetCenter() throws Exception {
        Ellipsoid elp = new Ellipsoid(center, axisLengths);
        assertArrayEquals(center.getValues(), elp.getCenter().getValues(), 1e-10);
    }

    @Test
    public void testGetCenterWithRadiusConstructor() throws Exception {
        elp = new Ellipsoid(center, 4);
        assertArrayEquals(center.getValues(), elp.getCenter().getValues(), 1e-10);
    }

    @Test
    public void testGetCenterWithDimConstructor() throws Exception {
        elp = new Ellipsoid(3);
        assertArrayEquals(new double[] {0,0,0}, elp.getCenter().getValues(), 1e-10);
    }

    @Test
    public void testGetAxisLength() throws Exception {
        assertArrayEquals(axisLengths.getValues(), elp.getHalfAxisLengths().getValues(), 1e-10);
    }

    @Test
    public void testGetAxisLengthWithRadiusConstructor() throws Exception {
        elp = new Ellipsoid(center, 4);
        assertArrayEquals(new double[] {4,4,4}, elp.getHalfAxisLengths().getValues(), 1e-10);
    }

    @Test
    public void testGetAxisLengthWithDimConstructor() throws Exception {
        elp = new Ellipsoid(3);
        assertArrayEquals(new double[] {1,1,1}, elp.getHalfAxisLengths().getValues(), 1e-10);
    }

    @Test
    public void testGetDim() throws Exception {
        double[] center = {1,2,3};
        double[] axisLengths = {4,5,6};
        Ellipsoid elp = new Ellipsoid(center, axisLengths);
        assertEquals(3, elp.getDim());
    }

    @Test
    public void testGetDimWithRadiusConstructor() throws Exception {
        elp = new Ellipsoid(center, 4);
        assertEquals(3, elp.getDim());
    }

    @Test
    public void testGetDimWithDimConstructor() throws Exception {
        elp = new Ellipsoid(3);
        assertEquals(3, elp.getDim());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsInsideWrongDimension() throws Exception{
        elp.isInside(new double[] {0,0});
    }

    @Test
    public void testIsInsideOnInterior() throws Exception{
        assertTrue(elp.isInside(new double[] {1,2,3}));
    }

    @Test
    public void testIsInsideOnBoundary() throws Exception{
        assertFalse(elp.isInside(new double[] {5,2,3}));
    }

    @Test
    public void testIsInsideOnExterior() throws Exception{
        assertFalse(elp.isInside(new double[] {7,2,3}));
    }

    @Test
    public void testIsInsideOnInteriorCloseToBoundary() throws Exception{
        assertTrue(elp.isInside(new double[] {4.99999,2,3}));
    }

    @Test
    public void testIsInsideOnExteriorCloseToBoundary() throws Exception{
        assertFalse(elp.isInside(new double[] {5.00000001,2,3}));
    }

}