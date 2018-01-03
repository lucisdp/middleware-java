package linalg;

import exceptions.EmptyVectorException;
import exceptions.IncompatibleDimensionsException;
import exceptions.NegativeDimensionException;
import exceptions.NormalizingZeroVectorException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class VectorTest {
    private Vector vec, vec2;

    @Before
    public void setUp(){
        vec = Vector.FACTORY.make(new double[] {1,2,3});
        vec2 = Vector.FACTORY.make(new double[] {-1,0,1});
    }


    @Test
    public void testGetDim(){
        assertEquals(3, vec.getDim());
    }

    @Test
    public void testAsArray(){
        assertArrayEquals(new double[] {1,2,3}, vec.asArray(), 1e-10);
    }

    @Test
    public void testIfAsArrayReturnsCopy(){
        double[] values = vec.asArray();
        values[0] += 100;
        assertEquals(vec.get(0), 1, 1e-10);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetValueNegativeIndex() throws Exception {
        vec.set(-1,10);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSetValueWithTooLargeIndex() throws Exception {
        vec.set(3, 10);
    }

    @Test
    public void testSetValues() throws Exception {
        vec.set(0,10);
        vec.set(1,20);
        vec.set(2,30);
        assertArrayEquals(new double[] {10,20,30}, vec.asArray(), 1e-10);
    }

    @Test
    public void testAppendLeft() throws Exception {
        assertArrayEquals(new double[] {-3, 1, 2, 3}, vec.appendLeft(-3).asArray(), 1e-10);
    }

    @Test
    public void testDropLeft() throws Exception {
        assertArrayEquals(new double[] {2, 3}, vec.dropLeft().asArray(), 1e-10);
    }

    @Test(expected = EmptyVectorException.class)
    public void testDropLeftUntilEmpty() throws Exception {
        Vector.FACTORY.makeZero(1).dropLeft();
    }

    @Test(expected = NegativeDimensionException.class)
    public void testFillConstructorWithNegativeDim(){
        Vector.FACTORY.makeFilled(-1, 2);
    }

    @Test
    public void testFillConstructor(){
        assertArrayEquals(new double[] {1,1,1}, Vector.FACTORY.makeFilled(3, 1).asArray(), 1e-10);
    }

    @Test
    public void testZeroConstructor(){
        assertArrayEquals(new double[] {0,0,0}, Vector.FACTORY.makeZero(3).asArray(), 1e-10);
    }

    @Test(expected = NegativeDimensionException.class)
    public void testZeroConstructorWithNegativeDim(){
        Vector.FACTORY.makeZero(-1);
    }

    @Test
    public void testAddValue(){
        Vector res = vec.add(5);
        assertArrayEquals(new double[] {6,7,8}, res.asArray(), 1e-10);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testAddVectorOfWrongDimension(){
        vec.add(Vector.FACTORY.make(new double[] {-1,1,2,3}));
    }

    @Test
    public void testAddVector(){
        Vector res = vec2.add(vec);
        assertArrayEquals(new double[] {0,2,4}, res.asArray(), 1e-10);
    }

    @Test
    public void testSubtractValue(){
        Vector res = vec.subtract(5);
        assertArrayEquals(new double[] {-4,-3,-2}, res.asArray(), 1e-10);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testSubtractVectorOfWrongDimension(){ vec.subtract(Vector.FACTORY.make((new double[] {-1,1,2,3}))); }

    @Test
    public void testSubtractVector(){
        Vector res = vec.subtract(vec2);
        assertArrayEquals(new double[] {2,2,2}, res.asArray(), 1e-10);
    }

    @Test
    public void testMultiplyByValue(){
        Vector res = vec.multiply(2);
        assertArrayEquals(new double[] {2,4,6}, res.asArray(), 1e-10);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testMultiplyByVectorOfWrongDimension(){ vec.multiply(Vector.FACTORY.make((new double[] {1,2,3,4}))); }

    @Test
    public void testMultiplyByVector(){
        Vector res = vec.multiply(vec2);
        assertArrayEquals(new double[] {-1,0,3}, res.asArray(), 1e-10);
    }

    @Test
    public void testDivideByValue(){
        Vector res = vec.divide(2);
        assertArrayEquals(new double[] {0.5,1.0,1.5}, res.asArray(), 1e-10);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testDivideByVectorOfWrongDimension(){ vec.divide(Vector.FACTORY.make((new double[] {1,2,3,4}))); }

    @Test
    public void testDivideByVector(){
        vec2 = Vector.FACTORY.make(new double[] {-1,2,4});
        Vector res = vec.divide(vec2);
        assertArrayEquals(new double[] {-1,1,0.75}, res.asArray(), 1e-10);
    }

    @Test
    public void testNorm(){ assertEquals( Math.sqrt(1+4+9), vec.norm(), 1e-10); }

    @Test(expected = NormalizingZeroVectorException.class)
    public void testNormalizeZeroVector() throws Exception {
        Vector.FACTORY.makeZero(3).normalize();
    }

    @Test
    public void testNormalize() throws Exception {
        assertArrayEquals(new double[] {1.0/Math.sqrt(14), 2.0/Math.sqrt(14), 3.0/Math.sqrt(14)}, vec.normalize().asArray(), 1e-10);
    }

    @Test
    public void testSqNorm(){ assertEquals( 14, vec.sqNorm(), 1e-10); }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testDotWronDimension(){
        vec.dot(Vector.FACTORY.makeZero(4));
    }

    @Test
    public void testDot(){ assertEquals(2, vec.dot(vec2), 1e-10); }

    @Test
    public void testEqualsToValue(){
        Vector vec = Vector.FACTORY.makeZero(3);
        assertTrue(vec.equals(0));
        assertTrue(vec.equals(1e-11));
        assertFalse(vec.equals(1e-9));
    }

    @Test
    public void testEqualsToVector(){
        assertTrue(vec.equals(Vector.FACTORY.make(new double[] {1,2,3})));
        assertTrue(vec.equals(Vector.FACTORY.make(new double[] {1+1e-18,2,3})));
        assertFalse(vec.equals(Vector.FACTORY.make(new double[] {1+1e-9,2,3})));
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
        assertFalse(vec.isSmallerOrEqualThan(Vector.FACTORY.make(new double[] {1-1e-10,2,3})));
        assertTrue(vec.isSmallerOrEqualThan(Vector.FACTORY.make(new double[] {1+1e-10,2,3})));
        assertFalse(vec.isSmallerOrEqualThan(Vector.FACTORY.make(new double[] {1,2-1e-10,3})));
        assertTrue(vec.isSmallerOrEqualThan(Vector.FACTORY.make(new double[] {1,2+1e-10,3})));
        assertFalse(vec.isSmallerOrEqualThan(Vector.FACTORY.make(new double[] {1,2,3-1e-10})));
        assertTrue(vec.isSmallerOrEqualThan(Vector.FACTORY.make(new double[] {1,2,3+1e-10})));
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
        assertFalse(vec.isSmallerThan(Vector.FACTORY.make(new double[] {1,2,3})));
        assertFalse(vec.isSmallerThan(Vector.FACTORY.make(new double[] {1-1e-10,2-1e-10,3-1e-10})));
        assertFalse(vec.isSmallerThan(Vector.FACTORY.make(new double[] {1-1e-10,2-1e-10,3+1e-10})));
        assertFalse(vec.isSmallerThan(Vector.FACTORY.make(new double[] {1-1e-10,2+1e-10,3+1e-10})));
        assertTrue(vec.isSmallerThan(Vector.FACTORY.make(new double[] {1+1e-10,2+1e-10,3+1e-10})));

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
        assertTrue(vec.isLargerThan(Vector.FACTORY.make(new double[] {1-1e-10, 2-1e-10, 3-1e-10})));
        assertFalse(vec.isLargerThan(Vector.FACTORY.make(new double[] {1-1e-10, 2-1e-10, 3})));
        assertFalse(vec.isLargerThan(Vector.FACTORY.make(new double[] {1-1e-10, 2, 3-1e-10})));
        assertFalse(vec.isLargerThan(Vector.FACTORY.make(new double[] {1, 2-1e-10, 3-1e-10})));
        assertFalse(vec.isLargerThan(Vector.FACTORY.make(new double[] {1, 2, 3})));
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
        assertTrue(vec.isLargerOrEqualThan(Vector.FACTORY.make(new double[] {1-1e-10,2,3})));
        assertFalse(vec.isLargerOrEqualThan(Vector.FACTORY.make(new double[] {1+1e-10,2,3})));
        assertTrue(vec.isLargerOrEqualThan(Vector.FACTORY.make(new double[] {1,2-1e-10,3})));
        assertFalse(vec.isLargerOrEqualThan(Vector.FACTORY.make(new double[] {1,2+1e-10,3})));
        assertTrue(vec.isLargerOrEqualThan(Vector.FACTORY.make(new double[] {1,2,3-1e-10})));
        assertFalse(vec.isLargerOrEqualThan(Vector.FACTORY.make(new double[] {1,2,3+1e-10})));
    }

    @Test
    public void testToString(){ assertEquals("{1.0, 2.0, 3.0}", vec.toString());}
}
