package convex.objects;

import exceptions.IncompatibleBoundsException;
import exceptions.IncompatibleDimensionsException;
import exceptions.NegativeDimensionException;
import exceptions.NegativeLengthException;
import linalg.Vector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class BoxTest {
    Vector low, high;
    Box box;

    @Before
    public void setUp(){
        Vector.setVectorOperationStrategy("ojalgo");
        low = new Vector(new double[] {1,2,3});
        high =  new Vector(new double[] {4,5,6});
        box = new Box(low, high);
    }

    @Test(expected=IncompatibleDimensionsException.class)
    public void testWrongDimensions() throws Exception {
        high = new Vector(new double[] {2,3});
        new Box(low, high);
    }

    @Test(expected= IncompatibleBoundsException.class)
    public void testNegativeAxisLength() throws Exception {
        high = new Vector(new double[] {0,4,5});
        Box box = new Box(low, high);
        System.out.println(low);
        System.out.println(high);
        System.out.println(low.isSmallerOrEqualThan(high));
    }

    @Test(expected=IncompatibleBoundsException.class)
    public void testZeroAxisLength() throws Exception {
        high = new Vector(new double[] {2,2,5});
        new Box(low, high);
    }

    @Test(expected=NegativeLengthException.class)
    public void testNegativeLength() throws Exception {
        new Box(new Vector(new double[] {1,2,3}), -1);
    }

    @Test(expected= NegativeDimensionException.class)
    public void testNegativeDim() throws Exception {
        new Box(-1);
    }

    @Test
    public void testGetHigh() throws Exception {
        assertTrue(high.equals(box.getHigh()));
    }

    @Test
    public void testGetHighWithCenterConstructor() throws Exception {
        box = new Box(new Vector(new double[] {1,2,3}), 2);
        assertArrayEquals(new double[] {2,3,4}, box.getHigh().getValues(), 1e-10);
    }

    @Test
    public void testGetHighWithDimConstructor() throws Exception {
        box = new Box(3);
        assertArrayEquals(new double[] {1,1,1}, box.getHigh().getValues(), 1e-10);
    }

    @Test
    public void testGetLow() throws Exception {
        assertArrayEquals(low.getValues(), box.getLow().getValues(), 1e-10);
    }

    @Test
    public void testGetLowWithCenterConstructor() throws Exception {
        Box box = new Box(low, 2);
        assertArrayEquals(new double[] {0,1,2}, box.getLow().getValues(), 1e-10);
    }

    @Test
    public void testGetLowWithDimConstructor() throws Exception {
        Box box = new Box(3);
        assertArrayEquals(new double[] {-1,-1,-1}, box.getLow().getValues(), 1e-10);
    }


    @Test
    public void testGetDim() throws Exception {
        assertEquals(3, box.getDim());
    }

    @Test
    public void testGetDimWithRadiusConstructor() throws Exception {
        box = new Box(new Vector(new double[] {1,2,3}), 4);
        assertEquals(3, box.getDim());
    }

    @Test
    public void testGetDimWithDimConstructor() throws Exception {
        box = new Box(3);
        assertEquals(3, box.getDim());
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testIsInsideWrongDimension() throws Exception{
        box.isInside(new Vector(new double[] {1,2}));
    }

    @Test
    public void testIsInsideOnInterior() throws Exception{
        assertTrue(box.isInside(new Vector(new double[] {2,3,4})));
    }

    @Test
    public void testIsInsideOnBoundary() throws Exception{
        assertFalse(box.isInside(new Vector(new double[] {1,3,4})));
    }

    @Test
    public void testIsInsideNearEdge() throws Exception{
        assertTrue(box.isInside(new Vector(new double[] {3.99999,4.99999,5.99999})));
    }

    @Test
    public void testIsInsideOnExterior() throws Exception{
        assertFalse(box.isInside(new Vector(new double[] {5,6,7})));
    }

    @Test
    public void testIsInsideOnInteriorCloseToBoundary() throws Exception{
        assertTrue(box.isInside(new Vector(new double[] {1.0000001,3,4})));
    }

    @Test
    public void testIsInsideOnExteriorCloseToBoundary() throws Exception{
        assertFalse(box.isInside(new Vector(new double[] {0.99999999,3,4})));
    }
}