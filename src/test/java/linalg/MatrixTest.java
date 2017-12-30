package linalg;

import exceptions.IncompatibleDimensionsException;
import exceptions.NegativeDimensionException;
import org.junit.Before;
import org.junit.Test;
import utils.LinearAlgebraConfiguration;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public abstract class MatrixTest {
    Matrix mat, mat2;

    protected abstract String getLibraryName();

    @Before
    public void setUp(){
        LinearAlgebraConfiguration.setLibraryName(getLibraryName());
        mat = new Matrix(new double[][] {{1,2,3},{4,5,6}});
        mat2 = new Matrix(new double[][] {{-1,0,1},{2,5,-8}});
    }

    @Test
    public void testLibraryName() throws Exception {
        assertEquals(getLibraryName(), LinearAlgebraConfiguration.getLibraryName());
    }

    @Test
    public void testGetNumRows(){
        assertEquals(2, mat.getNumRows());
    }

    @Test
    public void testGetNumCols(){
        assertEquals(3, mat.getNumCols());
    }

    @Test
    public void testGetRow() throws Exception {
        assertArrayEquals(new double[] {1,2,3}, mat.getRow(0).getValues(), 1e-10);
        assertArrayEquals(new double[] {4,5,6}, mat.getRow(1).getValues(), 1e-10);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetRowNegativeIndex() throws Exception {
        mat.getRow(-1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetRowLargerThanSizeIndex() throws Exception {
        mat.getRow(2);
    }

    @Test
    public void testGetValues(){
        assertArrayEquals(new double[] {1,2,3}, mat.asArray()[0], 1e-10);
        assertArrayEquals(new double[] {4,5,6}, mat.asArray()[1], 1e-10);
    }

    @Test
    public void testGetIdentity() throws Exception {
        Matrix mat = Matrix.getIdentity(3);
        assertArrayEquals(new double[] {1,0,0}, mat.asArray()[0], 1e-10);
        assertArrayEquals(new double[] {0,1,0}, mat.asArray()[1], 1e-10);
        assertArrayEquals(new double[] {0,0,1}, mat.asArray()[2], 1e-10);
    }

    @Test(expected = NegativeDimensionException.class)
    public void testGetIdentityWithNegativeDim() throws Exception {
        Matrix.getIdentity(0);
    }

    @Test
    public void testFillConstructor(){
        mat = new Matrix(2, 3, 1);
        assertArrayEquals(new double[] {1,1,1}, mat.asArray()[0], 1e-10);
        assertArrayEquals(new double[] {1,1,1}, mat.asArray()[1], 1e-10);
    }

    @Test
    public void testDimConstructor(){
        mat = new Matrix(2, 3);
        assertArrayEquals(new double[] {0,0,0}, mat.asArray()[0], 1e-10);
        assertArrayEquals(new double[] {0,0,0}, mat.asArray()[1], 1e-10);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testAddMatrixOfWrongDimension(){
        mat.add(new Matrix(new double[][] {{-1,1,2,3}, {1,2,3,4}}));
    }

    @Test
    public void testAddValue(){
        Matrix res = mat.add(5);
        assertArrayEquals(new double[] {6,7,8}, res.asArray()[0], 1e-10);
        assertArrayEquals(new double[] {9,10,11}, res.asArray()[1], 1e-10);
    }

    @Test
    public void testAddMatrix(){
        Matrix res = mat2.add(mat);
        assertArrayEquals(new double[] {0,2,4}, res.asArray()[0], 1e-10);
        assertArrayEquals(new double[] {6,10,-2}, res.asArray()[1], 1e-10);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testSubtractMatrixOfWrongDimension(){ mat.subtract(new Matrix(new double[][] {{-1,1,2,3}, {1,2,3,4}})); }

    @Test
    public void testSubtractValue(){
        Matrix res = mat.subtract(5);
        assertArrayEquals(new double[] {-4,-3,-2}, res.asArray()[0], 1e-10);
        assertArrayEquals(new double[] {-1,0,1}, res.asArray()[1], 1e-10);
    }

    @Test
    public void testSubtractMatrix(){
        Matrix res = mat.subtract(mat2);
        assertArrayEquals(new double[] {2,2,2}, res.asArray()[0], 1e-10);
        assertArrayEquals(new double[] {2,0,14}, res.asArray()[1], 1e-10);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testMultiplyElementOfWrongDimension(){ mat.multiplyElement(new Matrix(new double[][] {{-1,1,2,3}, {1,2,3,4}})); }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testMultiplyByVectorOfWrongDimension(){ mat.multiply(new Vector(new double[] {-1,1,2,3})); }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testMultiplyByMatrixOfWrongDimension(){ mat.multiply(new Matrix(new double[][] {{-1,1}, {1,2}})); }

    @Test
    public void testMultiplyByValue(){
        Matrix res = mat.multiply(2);
        assertArrayEquals(new double[] {2,4,6}, res.asArray()[0], 1e-10);
        assertArrayEquals(new double[] {8,10,12}, res.asArray()[1], 1e-10);
    }

    @Test
    public void testMultiplyByVector(){
        Vector res = mat.multiply(new Vector(new double[] {-1,0,1}));
        assertArrayEquals(new double[] {2, 2}, res.getValues(), 1e-10);
    }

    @Test
    public void testMultiplyByMatrix(){
        Matrix res = mat.multiply(new Matrix(new double[][] {{-1,1}, {1,2}, {4,5}}));
        assertArrayEquals(new double[] {13,20}, res.asArray()[0], 1e-10);
        assertArrayEquals(new double[] {25,44}, res.asArray()[1], 1e-10);
    }

    @Test
    public void testMultiplyElement(){
        Matrix res = mat.multiplyElement(mat2);
        assertArrayEquals(new double[] {-1,0,3}, res.asArray()[0], 1e-10);
        assertArrayEquals(new double[] {8,25,-48}, res.asArray()[1], 1e-10);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testDivideByMatrixOfWrongDimension(){ mat.divide(new Matrix(new double[][] {{-1,1,2,3}, {1,2,3,4}})); }


    @Test
    public void testDivideByValue(){
        Matrix res = mat.divide(2);
        assertArrayEquals(new double[] {0.5,1,1.5}, res.asArray()[0], 1e-10);
        assertArrayEquals(new double[] {2,2.5,3}, res.asArray()[1], 1e-10);
    }

    @Test
    public void testDivideByMatrix(){
        mat2 = new Matrix(new double[][] {{-1,2,4},{4,6,10}});
        Matrix res = mat.divide(mat2);
        assertArrayEquals(new double[] {-1,1,0.75}, res.asArray()[0], 1e-10);
        assertArrayEquals(new double[] {1,0.833333333333,0.6}, res.asArray()[1], 1e-10);
    }

    @Test
    public void testToString(){ assertEquals("{1.0, 2.0, 3.0}\n{4.0, 5.0, 6.0}", mat.toString());}
}
