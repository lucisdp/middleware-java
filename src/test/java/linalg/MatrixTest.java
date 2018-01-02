package linalg;

import exceptions.IncompatibleDimensionsException;
import exceptions.NegativeDimensionException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public abstract class MatrixTest {
    private Matrix mat, mat2;

    protected abstract LinearAlgebraLibrary getLibrary();

    @Before
    public void setUp(){
        LinearAlgebraConfiguration.setLibrary(getLibrary());
        mat = Matrix.FACTORY.make(new double[][] {{1,2,3},{4,5,6}});
        mat2 = Matrix.FACTORY.make(new double[][] {{-1,0,1},{2,5,-8}});
    }

    @Test
    public void testLibraryName() throws Exception {
        assertEquals(getLibrary(), LinearAlgebraConfiguration.getLibraryName());
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
    public void testGetColumn() throws Exception {
        assertArrayEquals(new double[] {1,4}, mat.getColumn(0).asArray(), 1e-10);
        assertArrayEquals(new double[] {2,5}, mat.getColumn(1).asArray(), 1e-10);
        assertArrayEquals(new double[] {3,6}, mat.getColumn(2).asArray(), 1e-10);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetColumnNegativeIndex() throws Exception {
        mat.getColumn(-1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetColumnLargerThanSizeIndex() throws Exception {
        mat.getColumn(3);
    }

    @Test
    public void testGetColumnSliceDimension() throws Exception {
        Matrix slice = mat.sliceColumns(0,1);
        assertEquals(2, slice.getNumRows());
        assertEquals(1, slice.getNumCols());
    }

    @Test
    public void testGetColumnSliceValues() throws Exception {
        double[][] slice = mat.sliceColumns(0,2).asArray();
        assertArrayEquals(new double[] {1,2}, slice[0], 1e-10);
        assertArrayEquals(new double[] {4,5}, slice[1], 1e-10);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetColumnSliceNegativeIndex() throws Exception {
        mat.sliceColumns(-1,1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetColumnSliceLargerThanSizeIndex() throws Exception {
        mat.sliceColumns(0,3);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetColumnSliceStartGreaterThanEnd() throws Exception {
        mat.sliceColumns(2,1);
    }

    @Test
    public void testGetRow() throws Exception {
        assertArrayEquals(new double[] {1,2,3}, mat.getRow(0).asArray(), 1e-10);
        assertArrayEquals(new double[] {4,5,6}, mat.getRow(1).asArray(), 1e-10);
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
        Matrix mat = Matrix.FACTORY.makeEye(3);
        assertArrayEquals(new double[] {1,0,0}, mat.asArray()[0], 1e-10);
        assertArrayEquals(new double[] {0,1,0}, mat.asArray()[1], 1e-10);
        assertArrayEquals(new double[] {0,0,1}, mat.asArray()[2], 1e-10);
    }

    @Test(expected = NegativeDimensionException.class)
    public void testGetIdentityWithNegativeDim() throws Exception {
        Matrix.FACTORY.makeEye(0);
    }

    @Test
    public void testFillConstructor(){
        mat = Matrix.FACTORY.makeFilled(2, 3, 1);
        assertArrayEquals(new double[] {1,1,1}, mat.asArray()[0], 1e-10);
        assertArrayEquals(new double[] {1,1,1}, mat.asArray()[1], 1e-10);
    }

    @Test
    public void testDimConstructor(){
        mat = Matrix.FACTORY.makeZero(2, 3);
        assertArrayEquals(new double[] {0,0,0}, mat.asArray()[0], 1e-10);
        assertArrayEquals(new double[] {0,0,0}, mat.asArray()[1], 1e-10);
    }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testAddMatrixOfWrongDimension(){
        mat.add(Matrix.FACTORY.make(new double[][] {{-1,1,2,3}, {1,2,3,4}}));
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
    public void testSubtractMatrixOfWrongDimension(){ mat.subtract(Matrix.FACTORY.make(new double[][] {{-1,1,2,3}, {1,2,3,4}})); }

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
    public void testMultiplyElementOfWrongDimension(){ mat.multiplyElement(Matrix.FACTORY.make(new double[][] {{-1,1,2,3}, {1,2,3,4}})); }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testMultiplyByVectorOfWrongDimension(){ mat.multiply(Vector.FACTORY.make(new double[] {-1,1,2,3})); }

    @Test(expected = IncompatibleDimensionsException.class)
    public void testMultiplyByMatrixOfWrongDimension(){ mat.multiply(Matrix.FACTORY.make(new double[][] {{-1,1}, {1,2}})); }

    @Test
    public void testMultiplyByValue(){
        Matrix res = mat.multiply(2);
        assertArrayEquals(new double[] {2,4,6}, res.asArray()[0], 1e-10);
        assertArrayEquals(new double[] {8,10,12}, res.asArray()[1], 1e-10);
    }

    @Test
    public void testMultiplyByVector(){
        Vector res = mat.multiply(Vector.FACTORY.make(new double[] {-1,0,1}));
        assertArrayEquals(new double[] {2, 2}, res.asArray(), 1e-10);
    }

    @Test
    public void testMultiplyByMatrix(){
        Matrix res = mat.multiply(Matrix.FACTORY.make(new double[][] {{-1,1}, {1,2}, {4,5}}));
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
    public void testDivideByMatrixOfWrongDimension(){ mat.divide(Matrix.FACTORY.make(new double[][] {{-1,1,2,3}, {1,2,3,4}})); }


    @Test
    public void testDivideByValue(){
        Matrix res = mat.divide(2);
        assertArrayEquals(new double[] {0.5,1,1.5}, res.asArray()[0], 1e-10);
        assertArrayEquals(new double[] {2,2.5,3}, res.asArray()[1], 1e-10);
    }

    @Test
    public void testDivideByMatrix(){
        mat2 = Matrix.FACTORY.make(new double[][] {{-1,2,4},{4,6,10}});
        Matrix res = mat.divide(mat2);
        assertArrayEquals(new double[] {-1,1,0.75}, res.asArray()[0], 1e-10);
        assertArrayEquals(new double[] {1,0.833333333333,0.6}, res.asArray()[1], 1e-10);
    }

    @Test
    public void testTransposeDimensions() throws Exception {
        Matrix transpose = mat.transpose();
        assertEquals(3, transpose.getNumRows());
        assertEquals(2, transpose.getNumCols());
    }

    @Test
    public void testTransposeValues() throws Exception {
        Matrix transpose = mat.transpose();
        assertArrayEquals(new double[] {1,4}, transpose.getRow(0).asArray(), 1e-10);
        assertArrayEquals(new double[] {2,5}, transpose.getRow(1).asArray(), 1e-10);
        assertArrayEquals(new double[] {3,6}, transpose.getRow(2).asArray(), 1e-10);
    }

    @Test
    public void testToString(){ assertEquals("{1.0, 2.0, 3.0}\n{4.0, 5.0, 6.0}", mat.toString());}
}
