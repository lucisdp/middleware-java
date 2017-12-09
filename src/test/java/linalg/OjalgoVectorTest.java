package linalg;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class OjalgoVectorTest {
    IVector vec;

    @Before
    public void setUp(){
        vec = new OjalgoVector(new double[] {1,2,3});
    }

    @Test
    public void testDim(){
        assertEquals(3, vec.getDim());
    }

    @Test
    public void testGet(){
        assertEquals(1, vec.get(0), 1e-10);
        assertEquals(2, vec.get(1), 1e-10);
        assertEquals(3, vec.get(2), 1e-10);
    }

    @Test(expected = java.lang.ArrayIndexOutOfBoundsException.class)
    public void testGetNegativeIndex(){
        vec.get(-1);
    }

    @Test(expected = java.lang.ArrayIndexOutOfBoundsException.class)
    public void testGetLargerThanSizeIndex(){
        vec.get(3);
    }

    @Test
    public void testDot(){
        assertEquals(2.0, vec.dot(new OjalgoVector(new double[] {-1,0,1})), 1e-10);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testDotWrongDimension(){
        vec.dot(new OjalgoVector(new double[] {-1,1,2,3}));
    }

    @Test
    public void testEquals(){
        assertTrue(vec.equals(new OjalgoVector(new double[] {1,2,3})));
    }

    @Test
    public void testEqualsWithSmallImprecision(){
        IVector vec2 = new OjalgoVector(new double[] {1 + 1e-8, 2, 3});
        assertFalse(vec.equals(vec2));
    }

    @Test
    public void testEqualsWithVerySmallImprecision(){
        IVector vec2 = new OjalgoVector(new double[] {1 + 1e-10, 2, 3});
        assertTrue(vec.equals(vec2));
    }

    @Test
    public void testAdd(){
        IVector vec2 = new OjalgoVector(new double[] {-1,0,1});
        IVector res = vec2.add(vec);
        assertEquals(0, res.get(0), 1e-10);
        assertEquals(2, res.get(1), 1e-10);
        assertEquals(4, res.get(2), 1e-10);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testAddWrongDimension(){
        vec.add(new OjalgoVector(new double[] {-1,1,2,3}));
    }

    @Test
    public void testSubtract(){
        IVector vec2 = new OjalgoVector(new double[] {-1,0,1});
        IVector res = vec.subtract(vec2);
        assertEquals(2, res.get(0), 1e-10);
        assertEquals(2, res.get(1), 1e-10);
        assertEquals(2, res.get(2), 1e-10);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSubtractWrongDimension(){
        vec.subtract(new OjalgoVector(new double[] {-1,1,2,3}));
    }

    @Test
    public void testMultiply(){
        IVector res = vec.multiply(2);
        assertEquals(2, res.get(0), 1e-10);
        assertEquals(4, res.get(1), 1e-10);
        assertEquals(6, res.get(2), 1e-10);
    }

    @Test
    public void testDivide(){
        IVector res = vec.divide(2);
        assertEquals(0.5, res.get(0), 1e-10);
        assertEquals(1.0, res.get(1), 1e-10);
        assertEquals(1.5, res.get(2), 1e-10);
    }

    @Test
    public void testDivideByVector(){
        IVector vec2 = new OjalgoVector(new double[] {-1,2,4});
        IVector res = vec.divide(vec2);
        assertEquals(-1, res.get(0), 1e-10);
        assertEquals(1, res.get(1), 1e-10);
        assertEquals(0.75, res.get(2), 1e-10);
    }

    @Test
    public void testNorm(){
        assertEquals(vec.norm(), Math.sqrt(1+4+9), 1e-10);
    }

    @Test
    public void testIsSmallerOrEqualWithDouble(){
        assertTrue(vec.isSmallerOrEqualThan(4));
        assertTrue(vec.isSmallerOrEqualThan(3));
        assertFalse(vec.isSmallerOrEqualThan(2.9));
        assertFalse(vec.isSmallerOrEqualThan(0));
    }

    @Test
    public void testIsSmallerOrEqualWithVector(){
        assertTrue(vec.isSmallerOrEqualThan(new OjalgoVector(new double[] {1,2,3})));
        assertFalse(vec.isSmallerOrEqualThan(new OjalgoVector(new double[] {0,2,3})));
        assertFalse(vec.isSmallerOrEqualThan(new OjalgoVector(new double[] {1,1,3})));
        assertFalse(vec.isSmallerOrEqualThan(new OjalgoVector(new double[] {1,2,2})));
    }
}
