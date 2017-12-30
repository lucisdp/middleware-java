package linalg;

import exceptions.EmptyVectorException;
import exceptions.IncompatibleDimensionsException;
import exceptions.NormalizingZeroVectorException;
import org.junit.Before;
import org.junit.Test;
import utils.LinearAlgebraConfiguration;

import static org.junit.Assert.*;

public abstract class VectorTest {
    Vector vec, vec2;

    protected abstract String getLibraryName();

    @Before
    public void setUp(){
        LinearAlgebraConfiguration.setLibraryName(getLibraryName());
        vec = new Vector(new double[] {1,2,3});
        vec2 = new Vector(new double[] {-1,0,1});
    }

    @Test
    public void testLibraryName() throws Exception {
        assertEquals(getLibraryName(), LinearAlgebraConfiguration.getLibraryName());
    }

    @Test
    public void testGetDim(){
        assertEquals(3, vec.getDim());
    }

    @Test
    public void testGetValues(){
        assertArrayEquals(new double[] {1,2,3}, vec.getValues(), 1e-10);
    }

    @Test
    public void testSetValues() throws Exception {
        vec.set(0,10);
        vec.set(1,20);
        vec.set(2,30);
        assertArrayEquals(new double[] {10,20,30}, vec.getValues(), 1e-10);
    }

    @Test
    public void testAppendLeft() throws Exception {
        assertArrayEquals(new double[] {-3, 1, 2, 3}, vec.appendLeft(-3).getValues(), 1e-10);
    }

    @Test
    public void testDropLeft() throws Exception {
        assertArrayEquals(new double[] {2, 3}, vec.dropLeft().getValues(), 1e-10);
    }

    @Test(expected = EmptyVectorException.class)
    public void testDropLeftUntilEmpty() throws Exception {
        vec.dropLeft().dropLeft().dropLeft();
    }

    @Test
    public void testFillConstructor(){
        assertArrayEquals(new double[] {1,1,1}, (new Vector(3, 1)).getValues(), 1e-10);
    }

    @Test
    public void testDimConstructor(){
        assertArrayEquals(new double[] {0,0,0}, (new Vector(3)).getValues(), 1e-10);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testAddVectorOfWrongDimension(){
        vec.add(new Vector(new double[] {-1,1,2,3}));
    }

    @Test
    public void testAddValue(){
        Vector res = vec.add(5);
        assertArrayEquals(new double[] {6,7,8}, res.getValues(), 1e-10);
    }

    @Test
    public void testAddVector(){
        Vector res = vec2.add(vec);
        assertArrayEquals(new double[] {0,2,4}, res.getValues(), 1e-10);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testSubtractVectorOfWrongDimension(){ vec.subtract(new Vector(new double[] {-1,1,2,3})); }

    @Test
    public void testSubtractValue(){
        Vector res = vec.subtract(5);
        assertArrayEquals(new double[] {-4,-3,-2}, res.getValues(), 1e-10);
    }

    @Test
    public void testSubtractVector(){
        Vector res = vec.subtract(vec2);
        assertArrayEquals(new double[] {2,2,2}, res.getValues(), 1e-10);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testMultiplyByVectorOfWrongDimension(){ vec.multiply(new Vector(new double[] {1,2,3,4})); }

    @Test
    public void testMultiplyByValue(){
        Vector res = vec.multiply(2);
        assertArrayEquals(new double[] {2,4,6}, res.getValues(), 1e-10);
    }

    @Test
    public void testMultiplyByVector(){
        Vector res = vec.multiply(vec2);
        assertArrayEquals(new double[] {-1,0,3}, res.getValues(), 1e-10);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testDivideByVectorOfWrongDimension(){ vec.divide(new Vector(new double[] {1,2,3,4})); }


    @Test
    public void testDivideByValue(){
        Vector res = vec.divide(2);
        assertArrayEquals(new double[] {0.5,1.0,1.5}, res.getValues(), 1e-10);
    }

    @Test
    public void testDivideByVector(){
        vec2 = new Vector(new double[] {-1,2,4});
        Vector res = vec.divide(vec2);
        assertArrayEquals(new double[] {-1,1,0.75}, res.getValues(), 1e-10);
    }

    @Test
    public void testNorm(){ assertEquals( Math.sqrt(1+4+9), vec.norm(), 1e-10); }

    @Test(expected = NormalizingZeroVectorException.class)
    public void testNormalizeZeroVector() throws Exception {
        (new Vector(3)).normalize();
    }

    @Test
    public void testNormalize() throws Exception {
        assertArrayEquals(new double[] {1.0/Math.sqrt(14), 2.0/Math.sqrt(14), 3.0/Math.sqrt(14)}, vec.normalize().getValues(), 1e-10);
    }

    @Test
    public void testSqNorm(){ assertEquals( 14, vec.sqNorm(), 1e-10); }

    @Test
    public void testDot(){ assertEquals(2, vec.dot(vec2), 1e-10); }

    @Test
    public void testEqualsVal(){
        Vector vec = new Vector(3);
        assertTrue(vec.equals(0));
        assertTrue(vec.equals(1e-11));
        assertFalse(vec.equals(1e-9));
    }

    @Test
    public void testEquals(){
        assertTrue(vec.equals(new Vector(new double[] {1,2,3})));
        assertTrue(vec.equals(new Vector(new double[] {1+1e-18,2,3})));
        assertFalse(vec.equals(new Vector(new double[] {1+1e-9,2,3})));
    }

    @Test
    public void testIsSmallerOrEqualThanDouble(){
        assertFalse(vec.isSmallerOrEqualThan(0));
        assertFalse(vec.isSmallerOrEqualThan(1-1e-10));
        assertFalse(vec.isSmallerOrEqualThan(1));
        assertFalse(vec.isSmallerOrEqualThan(1+1e-10));
        assertFalse(vec.isSmallerOrEqualThan(3-1e-10));
        assertTrue(vec.isSmallerOrEqualThan(3));
        assertTrue(vec.isSmallerOrEqualThan(4));
    }

    @Test
    public void testIsSmallerOrEqualThanVector(){
        assertFalse(vec.isSmallerOrEqualThan(new Vector(new double[] {1-1e-10,2,3})));
        assertTrue(vec.isSmallerOrEqualThan(new Vector(new double[] {1+1e-10,2,3})));
        assertFalse(vec.isSmallerOrEqualThan(new Vector(new double[] {1,2-1e-10,3})));
        assertTrue(vec.isSmallerOrEqualThan(new Vector(new double[] {1,2+1e-10,3})));
        assertFalse(vec.isSmallerOrEqualThan(new Vector(new double[] {1,2,3-1e-10})));
        assertTrue(vec.isSmallerOrEqualThan(new Vector(new double[] {1,2,3+1e-10})));
    }

    @Test
    public void testIsSmallerThanDouble(){
        assertFalse(vec.isSmallerThan(0));
        assertFalse(vec.isSmallerThan(1-1e-10));
        assertFalse(vec.isSmallerThan(1));
        assertFalse(vec.isSmallerThan(1+1e-10));
        assertFalse(vec.isSmallerThan(3-1e-10));
        assertFalse(vec.isSmallerThan(3));
        assertTrue(vec.isSmallerThan(4));
    }

    @Test
    public void testIsSmallerThanVector(){
        assertFalse(vec.isSmallerThan(new Vector(new double[] {1,2,3})));
        assertFalse(vec.isSmallerThan(new Vector(new double[] {1-1e-10,2-1e-10,3-1e-10})));
        assertFalse(vec.isSmallerThan(new Vector(new double[] {1-1e-10,2-1e-10,3+1e-10})));
        assertFalse(vec.isSmallerThan(new Vector(new double[] {1-1e-10,2+1e-10,3+1e-10})));
        assertTrue(vec.isSmallerThan(new Vector(new double[] {1+1e-10,2+1e-10,3+1e-10})));

    }

    @Test
    public void testIsLargerThanDouble(){
        assertTrue(vec.isLargerThan(0));
        assertTrue(vec.isLargerThan(1-1e-10));
        assertFalse(vec.isLargerThan(1));
        assertFalse(vec.isLargerThan(1+1e-10));
        assertFalse(vec.isLargerThan(3-1e-10));
        assertFalse(vec.isLargerThan(3));
        assertFalse(vec.isLargerThan(4));
    }

    @Test
    public void testIsLargerThanVector(){
        assertTrue(vec.isLargerThan(new Vector(new double[] {1-1e-10, 2-1e-10, 3-1e-10})));
        assertFalse(vec.isLargerThan(new Vector(new double[] {1-1e-10, 2-1e-10, 3})));
        assertFalse(vec.isLargerThan(new Vector(new double[] {1-1e-10, 2, 3-1e-10})));
        assertFalse(vec.isLargerThan(new Vector(new double[] {1, 2-1e-10, 3-1e-10})));
        assertFalse(vec.isLargerThan(new Vector(new double[] {1, 2, 3})));
    }
    
    @Test
    public void testIsLargerOrEqualThanDouble(){
        assertTrue(vec.isLargerOrEqualThan(0));
        assertTrue(vec.isLargerOrEqualThan(1-1e-10));
        assertTrue(vec.isLargerOrEqualThan(1));
        assertFalse(vec.isLargerOrEqualThan(1+1e-10));
        assertFalse(vec.isLargerOrEqualThan(3-1e-10));
        assertFalse(vec.isLargerOrEqualThan(3));
        assertFalse(vec.isLargerOrEqualThan(4));
    }

    @Test
    public void testIsLargerOrEqualThanVector(){
        assertTrue(vec.isLargerOrEqualThan(new Vector(new double[] {1-1e-10,2,3})));
        assertFalse(vec.isLargerOrEqualThan(new Vector(new double[] {1+1e-10,2,3})));
        assertTrue(vec.isLargerOrEqualThan(new Vector(new double[] {1,2-1e-10,3})));
        assertFalse(vec.isLargerOrEqualThan(new Vector(new double[] {1,2+1e-10,3})));
        assertTrue(vec.isLargerOrEqualThan(new Vector(new double[] {1,2,3-1e-10})));
        assertFalse(vec.isLargerOrEqualThan(new Vector(new double[] {1,2,3+1e-10})));
    }

    @Test
    public void testToString(){ assertEquals("{1.0, 2.0, 3.0}", vec.toString());}
}
