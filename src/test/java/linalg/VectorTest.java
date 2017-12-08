package linalg;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class VectorTest {
    Vector vec;

    @Before
    public void setUp(){
        vec = new Vector(new double[] {1,2,3});
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
        assertEquals(2.0, vec.dot(new Vector(new double[] {-1,0,1})), 1e-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDotWrongDimension(){
        vec.dot(new Vector(new double[] {-1,1,2,3}));
    }

    @Test
    public void testEquals(){
        assertTrue(vec.equals(new Vector(new double[] {1,2,3})));
    }

    @Test
    public void testEqualsWithSmallImprecision(){
        Vector vec2 = new Vector(new double[] {1 + 1e-8, 2, 3});
        assertFalse(vec.equals(vec2));
    }

    @Test
    public void testEqualsWithVerySmallImprecision(){
        Vector vec2 = new Vector(new double[] {1 + 1e-10, 2, 3});
        assertTrue(vec.equals(vec2));
    }

    @Test
    public void testAdd(){
        Vector vec2 = new Vector(new double[] {-1,0,1});
        Vector res = vec.add(vec2);
        assertEquals(0, res.get(0), 1e-10);
        assertEquals(2, res.get(1), 1e-10);
        assertEquals(4, res.get(2), 1e-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWrongDimension(){
        vec.add(new Vector(new double[] {-1,1,2,3}));
    }

    @Test
    public void testSubtract(){
        Vector vec2 = new Vector(new double[] {-1,0,1});
        Vector res = vec.subtract(vec2);
        assertEquals(2, res.get(0), 1e-10);
        assertEquals(2, res.get(1), 1e-10);
        assertEquals(2, res.get(2), 1e-10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtractWrongDimension(){
        vec.subtract(new Vector(new double[] {-1,1,2,3}));
    }

    @Test
    public void testMultiply(){
        Vector res = vec.multiply(2);
        assertEquals(2, res.get(0), 1e-10);
        assertEquals(4, res.get(1), 1e-10);
        assertEquals(6, res.get(2), 1e-10);
    }

    @Test
    public void testDivide(){
        Vector res = vec.divide(2);
        assertEquals(0.5, res.get(0), 1e-10);
        assertEquals(1.0, res.get(1), 1e-10);
        assertEquals(1.5, res.get(2), 1e-10);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero(){
        Vector res = vec.divide(0);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByInfinite(){
        Vector res = vec.divide(1.0/0.0);
    }

    @Test
    public void testDivideByVector(){
        Vector vec2 = new Vector(new double[] {-1,2,4});
        Vector res = vec.divide(vec2);
        assertEquals(-1, res.get(0), 1e-10);
        assertEquals(1, res.get(1), 1e-10);
        assertEquals(0.75, res.get(2), 1e-10);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByVectorContainingZero(){
        Vector vec2 = new Vector(new double[] {-1,0,4});
        vec.divide(vec2);
    }

    @Test
    public void testNorm(){
        assertEquals(vec.norm(), Math.sqrt(1+4+9), 1e-10);
    }

    @Test
    public void testIterator(){
        int i=0;
        for (double d: vec){
            if (i==0)
                assertEquals(1, d,1e-10);
            if (i==1)
                assertEquals(2, d,1e-10);
            if (i==2)
                assertEquals(3, d,1e-10);
            i++;
        }
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
        assertTrue(vec.isSmallerOrEqualThan(new Vector(new double[] {1,2,3})));
        assertFalse(vec.isSmallerOrEqualThan(new Vector(new double[] {0,2,3})));
        assertFalse(vec.isSmallerOrEqualThan(new Vector(new double[] {1,1,3})));
        assertFalse(vec.isSmallerOrEqualThan(new Vector(new double[] {1,2,2})));
    }
}
